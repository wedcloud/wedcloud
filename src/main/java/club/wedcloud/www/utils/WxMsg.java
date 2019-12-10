package club.wedcloud.www.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import club.wedcloud.www.dao.BaseMessage;
import club.wedcloud.www.dao.ImageMessage;
import club.wedcloud.www.dao.MusicMessage;
import club.wedcloud.www.dao.NewsMessage;
import club.wedcloud.www.dao.TestMessage;
import club.wedcloud.www.dao.VideoMessage;
import club.wedcloud.www.dao.VoiceMessage;

/**
 * 微信消息处理
 * 
 * @author xuhb
 * @Description WxMsg
 * @time 2019年12月10日
 */
@Slf4j
public class WxMsg {
  /**
   * 图像消息排重标识
   */
  private static String imageId = "";

  /**
   * 处理文本消息
   * 
   * @Description
   * @param map
   * @return
   */
  public static String imageMsg(Map<String, String> map) {
    if (!imageId.equals(map.get("MsgId"))) {
      return beanToXml(new ImageMessage(map, map.get("MediaId")));
    }
    return "success";
  }

  /**
   * 文本消息排重标识
   */
  private static String testMsgId = "";

  /**
   * 处理文本消息
   * 
   * @Description
   * @param map
   * @return
   */
  public static String testMsg(Map<String, String> map) {
    System.out.println(testMsgId + "   ==  " + map.get("MsgId"));
    if (!testMsgId.equals(map.get("MsgId"))) {
      return beanToXml(new TestMessage(map, chat(map.get("Content"))));
    }
    return "success";
  }

  /**
   * 将对象转成xml
   * 
   * @Description
   * @param b
   * @return
   */
  public static String beanToXml(BaseMessage b) {
    XStream xstream = new XStream();
    xstream.processAnnotations(TestMessage.class);
    xstream.processAnnotations(ImageMessage.class);
    xstream.processAnnotations(MusicMessage.class);
    xstream.processAnnotations(VideoMessage.class);
    xstream.processAnnotations(VoiceMessage.class);
    xstream.processAnnotations(NewsMessage.class);
    return xstream.toXML(b);
  }

  /**
   * 图灵聊天机器人
   * 
   * @Description
   * @param msg
   * @return
   */
  private static final RestTemplate restTemplate = new RestTemplate();
  private static String chatUrl =
      "http://op.juhe.cn/iRobot/index?info=%s&key=4a6019af145ed0ce28eb77ed4907f93a";

  public static String chat(String msg) {
    String url = String.format(chatUrl, msg);
    JSONObject json = restTemplate.getForObject(url, JSONObject.class);
    if ("0".equals(json.getString("error_code"))) {
      return json.getJSONObject("result").getString("text");
    }
    return "我累了，歇会~~";
  }

  /**
   * 腾讯闲聊机器人
   * @return
   */
  private static final Integer APPID=2125482175;
  private static final String APPKEY="sy2Ve6BXGBrt4FIe";
  private static String txChatUrl ="https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
  public static ResponseEntity<JSONObject> txChat(String msg) throws Exception {
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("app_id",APPID);
    map.put("time_stamp",System.currentTimeMillis()/1000);
    map.put("nonce_str", getRandomString(16));
    map.put("question",msg);
    map.put("session","10000");
    map.put("sign","");
    String sign = DigestUtils.md5DigestAsHex(mapToString(map).getBytes());
    map.put("sign",sign.toUpperCase());
    ResponseEntity<JSONObject> json = restTemplate.postForEntity(txChatUrl,order(map), JSONObject.class, "");
    log.debug("chat json,{}",JSON.toJSONString(json.getBody()));
    return json;
  }

  public static Map<String, Object> order(Map<String, Object> map) {
    HashMap<String, Object> tempMap = new LinkedHashMap<String, Object>();
    List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());

    Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
      public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
        return (o1.getKey()).toString().compareTo(o2.getKey());
      }
    });

    for (int i = 0; i < infoIds.size(); i++) {
      Map.Entry<String, Object> item = infoIds.get(i);
      tempMap.put(item.getKey(), item.getValue());
    }
    return tempMap;
  }
  public static String getRandomString(int length) {
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
  public static String mapToString(Map<String, Object> map) throws Exception {
    Map<String, Object> sortedParams = new TreeMap<>(map);
    Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
    StringBuilder baseString = new StringBuilder();
    for (Map.Entry<String, Object> param : entrys) {
      if (param.getValue() != null && !"".equals(param.getKey().trim()) &&
              !"sign".equals(param.getKey().trim()) && !"".equals(param.getValue())) {
        baseString.append(param.getKey().trim()).append("=")
                .append(URLEncoder.encode(param.getValue().toString(), "UTF-8")).append("&");
      }
    }
    if (baseString.length() > 0) {
      baseString.deleteCharAt(baseString.length() - 1).append("&app_key=")
              .append(APPKEY);
    }
    log.debug("mapToString {}",baseString.toString());
    return baseString.toString();
  }
}
