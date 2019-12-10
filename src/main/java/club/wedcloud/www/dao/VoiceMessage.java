package club.wedcloud.www.dao;

import java.util.Map;
import lombok.Data;

@Data
public class VoiceMessage extends BaseMessage {
  private MediaId mediaId;

  public VoiceMessage(Map<String, String> map, MediaId mediaId) {
    super(map);
    this.setMsgType("voice");
    this.mediaId = mediaId;
  }

  @Data
  class MediaId {
    private String mediaId;

    public MediaId(String mediaId) {
      super();
      this.mediaId = mediaId;
    }
  }
}
