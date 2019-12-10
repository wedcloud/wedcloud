package club.wedcloud.www.dao;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class NewsMessage extends BaseMessage {
  private Integer articleCount;
  private List<Item> articles;

  public NewsMessage(Map<String, String> map, Integer articleCount, List<Item> articles) {
    super(map);
    this.articleCount = articleCount;
    this.articles = articles;
  }

  @Data
  class Item {
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public Item(String title, String description, String picUrl, String url) {
      super();
      this.title = title;
      this.description = description;
      this.picUrl = picUrl;
      this.url = url;
    }
  }
}
