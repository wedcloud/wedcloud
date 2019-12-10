package club.wedcloud.www.dao;

import java.util.Map;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class BaseMessage {
  @XStreamAlias("ToUserName")
  private String toUserName;
  @XStreamAlias("FromUserName")
  private String fromUserName;
  @XStreamAlias("CreateTime")
  private Long createTime;
  @XStreamAlias("MsgType")
  private String msgType;

  public BaseMessage(Map<String, String> map) {
    this.toUserName = map.get("FromUserName");
    this.fromUserName = map.get("ToUserName");
    this.createTime = System.currentTimeMillis() / 1000;
  }
}
