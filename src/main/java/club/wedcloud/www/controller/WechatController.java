package club.wedcloud.www.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.async.AsyncTask;
import club.wedcloud.www.utils.WxMsg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "微信")
@Slf4j
@RestController
@RequestMapping("/v1")
public class WechatController {

  @Autowired
  private AsyncTask asyncTask;

  private static final String token = "HRGSmartSport";// 自己在微信测试平台设置的token

  @PostMapping("/wx")
  public String msg(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> map = analysisXml(request.getInputStream());
    StringBuffer sb = new StringBuffer();
    // 处理消息
    switch (map.get("MsgType")) {
      case "text":// 普通文本
        sb.append(WxMsg.testMsg(map));
        break;
      case "image":// 图片
        sb.append(WxMsg.imageMsg(map));
        break;
      // case "voice":// 语音
      //
      // break;
      // case "video":// 视频
      //
      // break;
      // case "shortvideo":// 小视频
      //
      // break;
      // case "location":// 地理位置
      //
      // break;
      // case "link":// 连接
      //
      // break;
      case "event":// 微信推送的消息
        sb.append("");
        asyncTask.eventMsg(map);
        break;
      default:
        sb.append("success");
        break;
    }
    return sb.toString();
  }

  @GetMapping("/wx")
  public void flushToken(HttpServletRequest request, HttpServletResponse response)
      throws UnsupportedEncodingException {
    // 设置编码，不然接收到的消息乱码
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String signature = request.getParameter("signature");// 微信加密签名
    String timestamp = request.getParameter("timestamp");// 时间戳
    String nonce = request.getParameter("nonce");// 随机数
    String echostr = request.getParameter("echostr");// 随机字符串
    PrintWriter out = null;
    // 接入验证
    if (checkSignature(signature, timestamp, nonce)) {
      if (echostr != null) {
        System.out.println(echostr);
        try {
          out = response.getWriter();
        } catch (IOException e) {
          e.printStackTrace();
        }
        out.write(echostr);// 验证成功返回的值
        return;
      }
    }
  }

  public static Map<String, String> analysisXml(InputStream is) throws DocumentException {
    Map<String, String> map = new HashMap<String, String>();
    SAXReader read = new SAXReader();
    Element e = read.read(is).getRootElement();
    List<Element> list = e.elements();
    for (Element element : list) {
      map.put(element.getName(), element.getStringValue());
    }
    return map;
  }

  public static boolean checkSignature(String signature, String timestamp, String nonce) {
    String[] str = new String[] {token, timestamp, nonce};
    // 排序
    Arrays.sort(str);
    // 拼接字符串
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < str.length; i++) {
      buffer.append(str[i]);
    }
    // 进行sha1加密
    String temp = encode(buffer.toString());
    // 与微信提供的signature进行匹对
    return signature.equals(temp);
  }

  private static final char[] HEX_DIGITS =
      {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

  /**
   * Takes the raw bytes from the digest and formats them correct.
   *
   * @param bytes the raw bytes from the digest.
   * @return the formatted bytes.
   */
  private static String getFormattedText(byte[] bytes) {
    int len = bytes.length;
    StringBuilder buf = new StringBuilder(len * 2);
    // 把密文转换成十六进制的字符串形式
    for (int j = 0; j < len; j++) {
      buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
      buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
    }
    return buf.toString();
  }

  public static String encode(String str) {
    if (str == null) {
      return null;
    }
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(str.getBytes());
      return getFormattedText(messageDigest.digest());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
