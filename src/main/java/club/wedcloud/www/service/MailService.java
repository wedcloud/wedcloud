package club.wedcloud.www.service;

import org.springframework.http.ResponseEntity;
import club.wedcloud.www.dao.MailEntity;

/**
 * 邮件服务接口声明
 * 
 * @author xuhb
 * @Description MailService
 * @time 2019年7月23日
 */
public interface MailService {
  /**
   * 发送邮件
   * 
   * @Description
   * @param to
   * @param subject
   * @param text
   * @return
   */
  ResponseEntity<String> sendMail(MailEntity mail);
}
