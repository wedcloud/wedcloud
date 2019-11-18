package club.wedcloud.www.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.qcloudsms.httpclient.HTTPException;
import club.wedcloud.www.dao.Tengxun;
import club.wedcloud.www.utils.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1")
@Api(tags = "短信管理")
public class NoteCodeController {
  @Autowired
  private Tengxun tengxun;

  @Autowired
  private RedisTemplate redisTemplate;

  @GetMapping("/noteCode")
  @ApiOperation("发送短信")
  public ResponseEntity<ResponseBean> getSmsNoteCode(String mobile)
      throws HTTPException, IOException {
    String[] code = {getCode(mobile), "1"};
    // 将验证码放入redis缓存，并设置过期时间60s
    redisTemplate.opsForValue().set(mobile, code[0], 60, TimeUnit.SECONDS);
    // SmsSingleSender smsSingleSender = new SmsSingleSender(tengxun.getAppid(),
    // tengxun.getAppkey());
    // SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", mobile,
    // tengxun.getTemplateid(), code, tengxun.getSmsSign(), "", "");
    // return ResponseEntity.ok(ResponseBean.ok(result.errMsg));
    return ResponseEntity.ok(ResponseBean.ok(code[0]));
  }

  @GetMapping("/checkCode")
  @ApiOperation("校验验证码")
  public ResponseEntity<ResponseBean> checkSmsNoteCode(String code, String mobile) {
    if (code.equals(redisTemplate.opsForValue().get(mobile))) {
      redisTemplate.delete(mobile);
      return ResponseEntity.ok(ResponseBean.ok(true));
    }
    return ResponseEntity.ok(ResponseBean.fall(false));
  }

  private String getCode(String mobile) {
    return String.valueOf(new Random().nextInt(8999) + 1000);
  }
}
