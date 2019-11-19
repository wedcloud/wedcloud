package club.wedcloud.www.exception;

import club.wedcloud.www.utils.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ResponseException {

    /**
     * @param e
     * @return
     * @Description 定义全局异常处理
     */
    @ExceptionHandler
    public ResponseEntity<ResponseBean> runtimeExceptionHandler(Exception e) {
        return ResponseEntity.ok(ResponseBean.body("9999", "操作异常", e));
    }
}
