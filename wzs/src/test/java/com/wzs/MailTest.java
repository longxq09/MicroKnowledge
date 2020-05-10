package com.wzs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;


@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("今晚开会");
        message.setText("大家，好！\n今晚7:30在教学楼201开班委会，请各位班委准时参加！ \n谢谢！");
        message.setTo("longxq09@163.com");
        message.setFrom("microknowledge@126.com");
        mailSender.send(message);
    }


    @Test
    public void test2() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("今晚开会");
        helper.setText("大家，好！<br> &nbsp;&nbsp;<b style='color:red'>今晚7:30在教学楼201开班委会，请各位班委准时参加！</b> <br>谢谢！", true);

        helper.setTo("longxq09@163.com");
        helper.setFrom("microknowledge@126.com");

        mailSender.send(mimeMessage);
    }


}
