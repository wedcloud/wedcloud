package club.wedcloud.www.config;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.ReflectionUtils;
import club.wedcloud.www.aspect.annotation.CacheDuration;

public class SpringRedisCacheManager extends RedisCacheManager
    implements ApplicationContextAware, InitializingBean {

  private static final Logger logger = LoggerFactory.getLogger(SpringRedisCacheManager.class);

  private ApplicationContext applicationContext;
  private Map<String, RedisCacheConfiguration> initialCacheConfiguration = new LinkedHashMap<>();

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  protected Collection<RedisCache> loadCaches() {
    return initialCacheConfiguration.entrySet().stream()
        .map(entry -> super.createRedisCache(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public void afterPropertiesSet() {
    Stream.of(applicationContext.getBeanNamesForType(Object.class))
        .forEach(beanName -> add(applicationContext.getType(beanName)));
    super.afterPropertiesSet();
  }

  private void add(String[] cacheNames, CacheDuration cacheDuration) {
    for (String cacheName : cacheNames) {
      if (isBlank(cacheName)) {
        continue;
      }
      long expire = cacheDuration.duration();
      logger.info("cacheName: {}, expire: {}", cacheName, expire);
      if (expire >= 0) {
        // 缓存配置
        RedisCacheConfiguration config =
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(expire));
        initialCacheConfiguration.put(cacheName, config);
      } else {
        logger.warn("{} use default expiration.", cacheName);
      }
    }
  }

  /**
   * 查询所有@CacheDuration的方法 获取过期时间
   * 
   * @param clazz
   */
  private void add(final Class clazz) {
    ReflectionUtils.doWithMethods(clazz, method -> {
      ReflectionUtils.makeAccessible(method);
      method.getAnnotation(CacheDuration.class);
      CacheDuration cacheExpire = AnnotationUtils.findAnnotation(method, CacheDuration.class);
      if (cacheExpire == null) {
        return;
      }
      Cacheable cacheable = AnnotationUtils.findAnnotation(method, Cacheable.class);
      if (cacheable != null) {
        add(cacheable.cacheNames(), cacheExpire);
        return;
      }
    }, method -> null != AnnotationUtils.findAnnotation(method, CacheDuration.class));
  }

  public SpringRedisCacheManager(RedisCacheWriter cacheWriter,
      RedisCacheConfiguration defaultCacheConfiguration) {
    super(cacheWriter, defaultCacheConfiguration);
  }


  public static boolean isBlank(String str) {
    int strLen;
    if (str != null && (strLen = str.length()) != 0) {
      for (int i = 0; i < strLen; ++i) {
        // 判断字符是否为空格、制表符、tab
        if (!Character.isWhitespace(str.charAt(i))) {
          return false;
        }
      }
      return true;
    } else {
      return true;
    }
  }
}
