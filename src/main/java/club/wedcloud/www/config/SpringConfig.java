package club.wedcloud.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baidu.aip.bodyanalysis.AipBodyAnalysis;

@Configuration
public class SpringConfig {

  public static final String APP_ID = "17556437";
  public static final String API_KEY = "YYx5W1bgjlfIWfn0UKiQ6rj6";
  public static final String SECRET_KEY = "EP7F3ra1HMGEOZk840IzOvtHyKnhEqwX";


  @Bean
  public AipBodyAnalysis sss() {
    AipBodyAnalysis client = new AipBodyAnalysis(APP_ID, API_KEY, SECRET_KEY);
    return client;
  }

}
