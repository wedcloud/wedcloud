package club.wedcloud.www.utils;

import java.util.Map;
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
   * 聊天机器人
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
}
