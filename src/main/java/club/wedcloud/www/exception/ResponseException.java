package club.wedcloud.www.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ResponseException {

  /**
   * 
   * @Description 定义全局异常处理
   * @param e
   * @return
   */
  @ExceptionHandler
  public ResponseEntity<String> runtimeExceptionHandler(Exception e) {
    return ResponseEntity.ok("{\"code\":\"9999\",\"message\":\"" + e + "\"}");
  }
}
