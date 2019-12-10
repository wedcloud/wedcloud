package club.wedcloud.www.dao;

import java.util.Map;
import lombok.Data;

@Data
public class MusicMessage extends BaseMessage {

  private Music music;

  public MusicMessage(Map<String, String> map, Music music) {
    super(map);
    this.setMsgType("music");
    this.music = music;
  }

  @Data
  class Music {
    private String title;
    private String description;
    private String musicURL;
    private String hQMusicUrl;
    private String thumbMediaId;

    public Music(String title, String description, String musicURL, String hQMusicUrl,
        String thumbMediaId) {
      super();
      this.title = title;
      this.description = description;
      this.musicURL = musicURL;
      this.hQMusicUrl = hQMusicUrl;
      this.thumbMediaId = thumbMediaId;
    }
  }
}
