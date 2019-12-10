package club.wedcloud.www.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import club.wedcloud.www.dao.WxAccessTokenResult;
import club.wedcloud.www.utils.ResponseBean;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
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
  // private static String appid = "wx38ae61aa3cf77bb8";
  // private static String secret = "3c4ee046743c6ccf438cc26f6734c2d7";
  private static String appid = "wx2f4773e87434787c";
  private static String secret = "5de8ffd7d9c2af2f12c35abf03067df0";

  private static String token = null;

  @GetMapping("/flushToken")
  public ResponseEntity<ResponseBean> flushToken() {
    token = getAccessToken(appid, secret);
    return ResponseEntity.ok(ResponseBean.ok(token));
  }

  @GetMapping("/createForeverTicket")
  public ResponseEntity<ResponseBean> createForeverTicket() {

    String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    requestUrl = requestUrl.replace("TOKEN", token);

    String jsonmsg =
        "{\"expire_seconds\":%d,\"action_name\": \"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";
    JSONObject json = JSON.parseObject(String.format(jsonmsg, 7200, 1234));
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    headers.setContentType(type);
    headers.add("Accept", MediaType.APPLICATION_JSON.toString());
    HttpEntity<String> formEntity = new HttpEntity<String>(json.toString(), headers);
    // 创建永久带参二维码
    return ResponseEntity.ok(
        ResponseBean.ok(restTemplate.postForEntity(requestUrl, formEntity, JSONObject.class, "")));
  }

  /**
   * @param appid appid
   * @param secret
   * @return
   */
  public String getAccessToken(String appid, String secret) {

    String url = String.format(WX_ACCESS_TOKEN, appid, secret);
    WxAccessTokenResult result = restTemplate.getForObject(url, WxAccessTokenResult.class);
    if (result.getErrcode() == null || result.getErrcode() == 0) {
      return result.getAccess_token();
    } else {
      log.error("获取access_toke失败：{}", result);
      return null;
    }
  }
}
