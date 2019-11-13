package club.wedcloud.www.utils;

public class ResponseBean {
  private String code;
  private String message;
  private Object data;

  public static ResponseBean ok(Object data) {
    return new ResponseBean("0000", "成功", data);
  }

  public static ResponseBean fall(Object data) {
    return new ResponseBean("2001", "失败", data);
  }

  public static ResponseBean body(String code, String message, Object data) {
    return new ResponseBean(code, message, data);
  }

  private ResponseBean(String code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  private ResponseBean() {
    // TODO Auto-generated constructor stub
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
