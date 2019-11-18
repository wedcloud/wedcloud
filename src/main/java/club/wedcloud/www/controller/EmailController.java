package club.wedcloud.www.controller;

import club.wedcloud.www.service.impl.EmailService;
import club.wedcloud.www.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/v1")
@Slf4j
public class EmailController {
    @Autowired
    private EmailService service;

    @GetMapping("/sendMail")
    public ResponseEntity<ResponseBean> sendMail(String from,String to,String cc,String subject ,String content){
        service.sendMail(from,to,cc,subject,content);
        return ResponseEntity.ok(ResponseBean.ok(from));
    }

    @GetMapping("/sendMailFile")
    public ResponseEntity<ResponseBean> sendMailFile(String from, String to, String cc, String subject , String content, MultipartFile file) throws MessagingException {
        service.sendMail(from,to,cc,subject,content,file);
        return ResponseEntity.ok(ResponseBean.ok(from));
    }
}
