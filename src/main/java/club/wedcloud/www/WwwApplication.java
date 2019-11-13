package club.wedcloud.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableCaching
@EnableAsync
@MapperScan("club.wedcloud.www.mapper")
public class WwwApplication {

  public static void main(String[] args) {
    SpringApplication.run(WwwApplication.class, args);
  }

}
