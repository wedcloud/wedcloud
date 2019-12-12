package club.wedcloud.www.controller;

import club.wedcloud.www.dao.WxAccessTokenResult;
import club.wedcloud.www.utils.ResponseBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuhb
 * @Description QRCodeController 二维码生成
 * @time 2019年12月9日
 */
@Api(tags = "临时二维码")
@Slf4j
@RestController
@RequestMapping("/v1")
public class TemporaryQRcode {

  private static String WX_ACCESS_TOKEN =
      "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
  private static final RestTemplate restTemplate = new RestTemplate();

  @Value("${appid}")
  private String appid;
  @Value("${secret}")
  private String secret;

  public static volatile String token = null;

  @GetMapping("/createForeverTicket")
  public ResponseEntity<ResponseBean> createForeverTicket() {

    String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    requestUrl = requestUrl.replace("TOKEN", token);

    String jsonmsg =
        "{\"expire_seconds\":%d,\"action_name\": \"QR_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"%s\"}}}";
    JSONObject json = JSON.parseObject(String.format(jsonmsg, 7200, UUID.randomUUID()));
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    headers.setContentType(type);
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
    // 创建永久带参二维码
    ResponseEntity<JSONObject> jsonObject = restTemplate
        .postForEntity(requestUrl, formEntity, JSONObject.class, "");
    return ResponseEntity.ok(
        ResponseBean.ok("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + jsonObject.getBody()
            .get("ticket")));
  }

  /**
   * @return
   */
  @Scheduled(fixedDelay = 7150)
  public void flushToken() {
    String url = String.format(WX_ACCESS_TOKEN, appid, secret);
    WxAccessTokenResult result = restTemplate.getForObject(url, WxAccessTokenResult.class);
    log.debug("getAccessToken --> result -- >{}", result);
    if (result.getErrcode() == null || result.getErrcode() == 0) {
      token = result.getAccess_token();
    } else {
      log.error("获取access_toke失败：{}", result);
    }
  }
}
