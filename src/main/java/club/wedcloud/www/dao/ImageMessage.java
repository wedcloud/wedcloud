package club.wedcloud.www.dao;

import java.util.Map;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {

  @XStreamAlias("Image")
  private Image image;

  public ImageMessage(Map<String, String> map, String mediaId) {
    super(map);
    this.setMsgType("image");
    this.image = new Image(mediaId);
  }

  @Data
  public class Image {
    @XStreamAlias("MediaId")
    private String mediaId;

    public Image(String mediaId) {
      this.mediaId = mediaId;
    }
  }
}
