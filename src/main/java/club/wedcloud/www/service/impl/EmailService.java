package club.wedcloud.www.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
  @Autowired
  private JavaMailSender sender;

  public void sendMail(String from, String to, String cc, String subject, String content) {
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom(from);
    msg.setTo(to);
    msg.setCc(cc);
    msg.setSubject(subject);
    msg.setText(content);
    log.debug("JavaMailSender:{}", msg);
    sender.send(msg);
  }

  public void sendMail(String from, String to, String cc, String subject, String content,
      FileSystemResource file) throws MessagingException {
    MimeMessage msg = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    helper.setFrom(from);
    helper.setTo(to);
    helper.setCc(cc);
    helper.setSubject(subject);
    helper.setText(content);
    helper.addAttachment(file.getFilename(), file);
    log.debug("JavaMailSender:{}", helper);
    sender.send(msg);
  }
}
