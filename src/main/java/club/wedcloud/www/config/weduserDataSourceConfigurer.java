package club.wedcloud.www.config;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class weduserDataSourceConfigurer {

  @Autowired
  @Qualifier("weduserDataSource")
  private DataSource weduserDataSource;

  @Autowired
  private JpaProperties jpaProperties;


  // 配置EntityManager实体
  @Primary
  @Bean(name = "entityManagerWeduser")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return null;
  }

  // // 配置EntityManager工厂实体
  // @Primary
  // @Bean(name = "entityManagerFactoryWeduser")
  // public LocalContainerEntityManagerFactoryBean entityManagerFactoryWeduser(
  // EntityManagerFactoryBuilder builder) {
  // return builder.dataSource(weduserDataSource).packages(packagesToScan)
  // }
  //
  // // 配置jpa配置信息
  // private Map<String, String> getVendorProperties(DataSource dataSource) {
  // return jpaProperties
  // }
}
