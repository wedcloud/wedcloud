package club.wedcloud.www.dao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tengxun")
@Data
public class Tengxun {
    private Integer appid;
    private String appkey;
    private Integer templateid;
    private String smsSign;
}
