package club.wedcloud.www.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
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
import club.wedcloud.www.dao.WechatQRCode;
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
  private static String appid = "wx38ae61aa3cf77bb8";
  private static String secret = "3c4ee046743c6ccf438cc26f6734c2d7";

  private static String token =
      "28_dvNXdINnpL_Wt2LKcYCfjqNRHPCc_BC_quJwfELfbmHnhncgdjWQyZQW1dVv4t1CitLJHfERm85eIFqhgT6kDp287LmEASPf0BWo4GHSidCLYYCTvrFSKg1EX9g7HBd7Up-7Y5Cj4UNxkNDRKNRdAJAFIM";

  @GetMapping("/flushToken")
  public ResponseEntity<ResponseBean> flushToken() {
    token = getAccessToken(appid, secret);
    return ResponseEntity.ok(ResponseBean.ok(token));
  }

  @GetMapping("/createForeverTicket")
  public ResponseEntity<ResponseBean> createForeverTicket() {
    // 拼接请求地址
    String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    requestUrl = requestUrl.replace("TOKEN", token);

    // 需要提交json数据
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
      log.error("获取access_toke失败：{}", JSON.toJSONString(result));
      return null;
    }
  }

  public static WechatQRCode createForeverTicket(String accessToken, int expireSeconds,
      int sceneId) {
    WechatQRCode wechatQRCode = null;

    // 拼接请求地址
    String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    requestUrl = requestUrl.replace("TOKEN", accessToken);

    // 需要提交json数据
    String jsonmsg =
        "{\"expire_seconds\":%d,\"action_name\": \"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%d}}}";

    // 创建永久带参二维码
    JSONObject jsonObject =
        httpRequest(requestUrl, "POST", String.format(jsonmsg, expireSeconds, sceneId));
    if (null != jsonObject) {
      System.out.println(jsonObject);

      try {
        wechatQRCode = new WechatQRCode();
        System.out.println(1);
        wechatQRCode.setTicket(jsonObject.getString("ticket"));
        wechatQRCode.setExpire_seconds(jsonObject.getInteger("expire_seconds"));
        System.out.println(2);
        System.out.println("永久带参二维码ticket成功=" + wechatQRCode.getTicket() + "Expire_seconds="
            + wechatQRCode.getExpire_seconds());
      } catch (Exception e) {
        wechatQRCode = null;
        int err = jsonObject.getInteger("errcode");
        String errormsg = jsonObject.getString("errmsg");
        System.out.println("永久带参二维码ticket失败失败errcode=" + err + "errmsg=" + errormsg);
      }
    }
    return wechatQRCode;
  }

  public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
    JSONObject jsonObject = null;
    StringBuffer buffer = new StringBuffer();
    try {
      URL url = new URL(requestUrl);
      HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
      // 将返回的输入流转换成字符串  
      InputStream inputStream = httpUrlConn.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {
        buffer.append(str);
      }
      bufferedReader.close();
      inputStreamReader.close();
      // 释放资源  
      inputStream.close();
      inputStream = null;
      httpUrlConn.disconnect();
      jsonObject = JSON.parseObject(buffer.toString());
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }
}
