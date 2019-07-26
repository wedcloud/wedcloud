package club.wedcloud.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 配置 因为自动配置的RedisTemplate的泛型是<Object,Object>写代码不方便，需要写好多类型转换的代码；
 * 我们需要一个泛型为<String,Object>形式的RedisTemplate。并且，这个RedisTemplate没有设置数 据存在Redis时，key及value的序列化方式
 * 
 * @author xuhb
 * @Description RedisConfig
 * @time 2019年7月26日
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(factory);
    // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
    // new Jackson2JsonRedisSerializer(Object.class);
    return null;
  }
}
