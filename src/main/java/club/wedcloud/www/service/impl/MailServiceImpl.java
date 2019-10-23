package club.wedcloud.www.service.impl;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import club.wedcloud.www.dao.MailEntity;
import club.wedcloud.www.service.MailService;

@Service
public class MailServiceImpl implements MailService {
  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private MailProperties mailProperties;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public ResponseEntity<String> sendMail(MailEntity mail) {
    try {
      final MimeMessage mimeMessage = mailSender.createMimeMessage();
      final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
      message.setTo(mail.getToMail());
      message.setFrom(mailProperties.getUsername());
      message.setSubject(mail.getSubject());
      message.setText(mail.getContext(), true);

      if (mail.getAttachmentMap() != null) {
        mail.getAttachmentMap().entrySet().stream().forEach(entrySet -> {
          try {
            File file = new File(entrySet.getValue());
            if (file.exists()) {
              message.addAttachment(entrySet.getKey(), new FileSystemResource(file));
            }
          } catch (MessagingException e) {
            e.printStackTrace();
          }
        });
      }

      mailSender.send(mimeMessage);
      logger.info("send e-mail success");
      return ResponseEntity.ok("send e-mail success");
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("send e-mail fail");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("send e-mail fail");
    }
  }
}
