package club.wedcloud.www.dao;

import java.util.Map;

public class MailEntity {
  private String toMail;
  private String subject;
  private String context;
  private String mailType;
  private Map<String, String> attachmentMap;

  public String getMailType() {
    return mailType;
  }

  public void setMailType(String mailType) {
    this.mailType = mailType;
  }

  public String getToMail() {
    return toMail;
  }

  public void setToMail(String toMail) {
    this.toMail = toMail;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public Map<String, String> getAttachmentMap() {
    return attachmentMap;
  }

  public void setAttachmentMap(Map<String, String> attachmentMap) {
    this.attachmentMap = attachmentMap;
  }

}
