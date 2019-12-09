package club.wedcloud.www.dao;

import lombok.Data;

/**
 * @author alex
 */
@Data
public class WxAccessTokenResult {

  private String access_token;

  private Integer expires_in;

  private Integer errcode;

  private String msg;
}
