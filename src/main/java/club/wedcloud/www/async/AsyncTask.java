package club.wedcloud.www.async;

import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import club.wedcloud.www.controller.TemporaryQRcode;
import lombok.extern.slf4j.Slf4j;

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
      "<a href='ttp://www.qq.com' data-miniprogram-appid='wx8d7e2d56baca4b38' data-miniprogram-path='pages/login/welcome/welcome'>点此查看检测报告</a>\\n国家权威机构认证，检测结果可用于医疗判断标准";

  private static volatile StringBuffer str = new StringBuffer();

  @Async("taskExecutor")
  public void eventMsg(Map<String, String> map) {
    if (!str.toString().equals(map.get("EventKey"))) {
      log.debug("eventMsg-->{}", map);
      try {
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
      }
    }
  }
}
