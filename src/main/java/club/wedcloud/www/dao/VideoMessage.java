package club.wedcloud.www.dao;

import java.util.Map;
import lombok.Data;

@Data
public class VideoMessage extends BaseMessage {

  private Video video;

  public VideoMessage(Map<String, String> map, Video video) {
    super(map);
    this.setMsgType("video");
    this.video = video;
  }

  @Data
  class Video {
    private String mediaId;
    private String title;
    private String description;

    public Video(String mediaId, String title, String description) {
      super();
      this.mediaId = mediaId;
      this.title = title;
      this.description = description;
    }
  }
}
