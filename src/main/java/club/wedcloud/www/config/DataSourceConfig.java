package club.wedcloud.www.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

  @Bean(name = "wedcloudDataSource")
  @Primary
  @Qualifier("wedcloudDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.wedcloud")
  public DataSource wedcloudDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "weduserDataSource")
  @Qualifier("weduserDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.weduser")
  public DataSource weduserDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "wedcloudJdbcTemplate")
  public JdbcTemplate wedcloudJdbcTemplate(@Qualifier("wedcloudDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean(name = "weduserJdbcTemplate")
  public JdbcTemplate weduserJdbcTemplate(@Qualifier("weduserDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
