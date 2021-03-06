package club.wedcloud.www.async;

import club.wedcloud.www.controller.TemporaryQRcode;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AsyncTask {

  private static final RestTemplate restTemplate = new RestTemplate();

  /**
   * 扫描二维码客户消息回复URL
   */
  private static String REPLY_WX =
      "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
  private static String link =
      "欢迎到来";

  @Autowired
  private RedissonClient redissonClient;

  @Async("taskExecutor")
  public void eventMsg(Map<String, String> map) {
    RLock lock = redissonClient.getLock(map.get("EventKey"));
    log.debug("eventMsg-->{}", map);
    try {
      lock.tryLock(30,10, TimeUnit.SECONDS);
      String tmp = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
      headers.setContentType(type);
      headers.add("Accept", MediaType.APPLICATION_JSON.toString());
      org.springframework.http.HttpEntity<String> formEntity =
          new HttpEntity<String>(String.format(tmp, map.get("FromUserName"), link), headers);
      restTemplate.postForEntity(REPLY_WX.replace("ACCESS_TOKEN", TemporaryQRcode.token),
          formEntity, JSONObject.class, "");
      log.debug("eventMsg --> 完成");
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }
}
