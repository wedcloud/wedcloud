package club.wedcloud.www;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("club.wedcloud.www.mapper")
public class WwwApplication {

  public static void main(String[] args) {
    SpringApplication.run(WwwApplication.class, args);
  }

}
