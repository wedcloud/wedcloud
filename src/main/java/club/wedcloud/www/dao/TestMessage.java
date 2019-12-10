package club.wedcloud.www.dao;

import java.util.Map;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("xml")
public class TestMessage extends BaseMessage {
  @XStreamAlias("Content")
  private String content;

  public TestMessage(Map<String, String> map, String content) {
    super(map);
    this.setMsgType("text");
    this.content = content;
  }
}
