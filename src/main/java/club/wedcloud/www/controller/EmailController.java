package club.wedcloud.www.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import club.wedcloud.www.service.impl.EmailService;
import club.wedcloud.www.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class EmailController {
  @Autowired
  private EmailService service;

  @GetMapping("/sendMail")
  public ResponseEntity<ResponseBean> sendMail(String from, String to, String cc, String subject,
      String content) {
    service.sendMail(from, to, cc, subject, content);
    return ResponseEntity.ok(ResponseBean.ok(from));
  }

  @GetMapping("/sendMailFile")
  public ResponseEntity<ResponseBean> sendMailFile(String from, String to, String cc,
      String subject, String content, String file) throws MessagingException {
    service.sendMail(from, to, cc, subject, content, new FileSystemResource(file));
    return ResponseEntity.ok(ResponseBean.ok(from));
  }
}
