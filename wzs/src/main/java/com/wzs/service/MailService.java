package com.wzs.service;

public interface MailService {

    void sendMimeMail(String to, String subject, String content);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
