package club.wedcloud.www.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 
 * @author xuhb
 * @Description DataSourceConfigurer 自定义数据源配置类
 * @time 2019年11月12日
 */
@Configuration
public class DataSourceConfigurer {

  // @Qualifier注解并且设置内容，是为了防止注入时冲突问题
  // @Primary配置了数据源为主数据源，当没有配置自动切换的package时默认使用该数据源进行数据处理操作

  // weduser 数据源
  @Bean(name = "weduserDataSource") // 装配该方法返回值为weduserDataSource管理bean
  @Qualifier("weduserDataSource") // spring装配bean唯一标识
  @ConfigurationProperties(prefix = "spring.datasource.weduser")
  public DataSource weduserDataSource() {
    return DataSourceBuilder.create().build();
  }

  // wedcloud 数据源
  @Bean(name = "wedcloudDataSource")
  @Primary // 定义该数据源为主数据源
  @Qualifier("wedcloudDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.wedcloud")
  public DataSource wedcloudDataSource() {
    return DataSourceBuilder.create().build();
  }
}
