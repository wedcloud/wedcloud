package club.wedcloud.www.dao;

import lombok.Data;

@Data
public class WechatQRCode {
  private String ticket;
  // 二维码的有效时间,单位为秒,最大不超过2592000（即30天）
  private int expire_seconds;
  private String url;
}
