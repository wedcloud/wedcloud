package club.wedcloud.www.async;

public class AsyncException extends RuntimeException {

  private String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public AsyncException() {
    super();
  }

  public AsyncException(String msg) {
    super(msg);
  }

  public AsyncException(String code, String msg) {
    super(msg);
    this.code = code;
  }

}
