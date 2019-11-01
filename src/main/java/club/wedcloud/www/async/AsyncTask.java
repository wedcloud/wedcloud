package club.wedcloud.www.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
  private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

  @Async
  public void asyncOne(String messge) {
    logger.info(messge);
  }

  @Async
  public void asyncTwo(String messge) {
    logger.info(messge);
  }
}
