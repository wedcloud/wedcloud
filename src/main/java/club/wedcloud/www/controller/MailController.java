package club.wedcloud.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件服务
 * 
 * @author xuhb
 * @Description MailController
 * @time 2019年7月23日
 */
@RestController
@RequestMapping("/v1")
public class MailController {

  // @Autowired
  // private MailService mailService;
  //
  // @PostMapping("/send-mail")
  // public ResponseEntity<String> sendMail(@RequestBody MailEntity mail) {
  // return mailService.sendMail(mail);
  // }
}
