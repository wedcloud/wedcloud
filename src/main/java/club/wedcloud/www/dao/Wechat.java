package club.wedcloud.www.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
public class Wechat {
  private String appid;
  private String secret;
  private String redirectUri;
  private String authrizationUrl;
  private String accessTokenUrl;
  private String getUnionIdUrl;
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public String getAuthrizationUrl() {
    return authrizationUrl;
  }

  public void setAuthrizationUrl(String authrizationUrl) {
    this.authrizationUrl = authrizationUrl;
  }

  public String getAccessTokenUrl() {
    return accessTokenUrl;
  }

  public void setAccessTokenUrl(String accessTokenUrl) {
    this.accessTokenUrl = accessTokenUrl;
  }

  public String getGetUnionIdUrl() {
    return getUnionIdUrl;
  }

  public void setGetUnionIdUrl(String getUnionIdUrl) {
    this.getUnionIdUrl = getUnionIdUrl;
  }
}
