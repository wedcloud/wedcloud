package club.wedcloud.www.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import club.wedcloud.www.dao.Wechat;
import club.wedcloud.www.utils.AccessToken;
import club.wedcloud.www.utils.AesUtil;
import club.wedcloud.www.utils.Constanst;
import club.wedcloud.www.utils.DateUtils;
import club.wedcloud.www.utils.HttpClientUtil;
import club.wedcloud.www.utils.WechatUserUnionID;

@Controller
@RequestMapping("/v1/pillow")
public class WechatLoginController {

  @Autowired
  private Wechat wechat;

  static final String PWD_MD5 = "intelligence@pillow";

  @GetMapping("/login")
  public String logn(HttpServletResponse response) {
    String content = Constanst.PWD_MD5 + DateUtils.getYYYYMMdd();
    byte[] encrypt = AesUtil.encrypt(content, AesUtil.PASSWORD_SECRET_KEY, 16);
    String parseByte2HexStr = AesUtil.parseByte2HexStr(encrypt);
    String url = wechat.getAuthrizationUrl();
    url = url.replaceAll("APPID", wechat.getAppid());
    try {
      url = url.replaceAll("REDIRECT_URI", URLEncoder.encode(wechat.getRedirectUri(), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    url = url.replaceAll("SCOPE", "snsapi_userinfo");
    url = url.replace("STATE", parseByte2HexStr); // 加密state进行验证 回调地址当天有效 防止恶意攻击
    System.out.println("redirect:" + url);
    return "redirect:" + url;
  }

  @GetMapping(value = "/callback")
  @ResponseBody
  public Object callback(@RequestParam String code, @RequestParam String state) {
    if (code != null && state != null) {
      String decrypt =
          AesUtil.decrypt(AesUtil.parseHexStr2Byte(state), AesUtil.PASSWORD_SECRET_KEY, 16);
      if (!decrypt.equals(Constanst.PWD_MD5 + DateUtils.getYYYYMMdd())) {
        // 可能被攻击了
        return "失败";
      }
      // 获取 accessToken
      AccessToken accessToken = getAccessToken(code);
      if (accessToken != null) {
        // 拿到openid获取微信用户的基本信息
        WechatUserUnionID userUnionID =
            getUserUnionID(accessToken.getOpenid(), accessToken.getAccess_token());
        return userUnionID;
      }
    }
    return "accessToken == null";
  }

  public AccessToken getAccessToken(String code) {
    String accessUrl = wechat.getAccessTokenUrl();
    accessUrl = accessUrl.replaceAll("APPID", wechat.getAppid());
    accessUrl = accessUrl.replaceAll("SECRET", wechat.getSecret());
    accessUrl = accessUrl.replaceAll("CODE", code);
    String responseContent = HttpClientUtil.getInstance().sendHttpGet(accessUrl);
    if (responseContent == null || responseContent == "") {
      return null;
    }
    JSONObject parseObject = JSONObject.parseObject(responseContent);
    AccessToken accessToken = JSONObject.toJavaObject(parseObject, AccessToken.class);
    return accessToken;
  }

  /**
   * 
   * @Description Description: 获取用户统一标识。针对一个微信开放平台帐号下的应用， 同一用户的unionid在多个应用中是唯一的。 此方法不牵扯到多个应用时候可以不用。
   *              此处用到只是为了获取微信扫码用户的省份城市(此信息获取的只是微信用户所填的城市省份， 并不是用户的实时位置信息，如果用户未填写是获取不到的。)
   * @param opendId
   * @param accessToken
   * @return
   */
  public WechatUserUnionID getUserUnionID(String opendId, String accessToken) {
    String unionIDUrl = wechat.getGetUnionIdUrl();
    unionIDUrl = unionIDUrl.replace("ACCESS_TOKEN", accessToken);
    unionIDUrl = unionIDUrl.replace("OPENID", opendId);
    String responseContent = HttpClientUtil.getInstance().sendHttpGet(unionIDUrl);
    if (responseContent == null || responseContent == "") {
      return null;
    }
    JSONObject parseObject = JSONObject.parseObject(responseContent);
    WechatUserUnionID userUnionID = JSONObject.toJavaObject(parseObject, WechatUserUnionID.class);
    return userUnionID;
  }
}
