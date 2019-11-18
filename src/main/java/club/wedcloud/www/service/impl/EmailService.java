package club.wedcloud.www.service.impl;

import com.sun.xml.internal.bind.v2.model.annotation.AnnotationReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    public void sendMail(String from, String to, String cc, String subject , String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setCc(cc);
        msg.setSubject(subject);
        msg.setText(content);
        log.debug("JavaMailSender:{}",msg);
        sender.send(msg);
    }

    public void sendMail(String from, String to, String cc, String subject , String content, MultipartFile file) throws MessagingException {
        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(content);
        helper.addAttachment(file.getOriginalFilename(),file);
        log.debug("JavaMailSender:{}",helper);
        sender.send(msg);
    }
}
