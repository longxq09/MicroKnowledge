package com.wzs.service.Imp;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;
import com.wzs.mapper.AccountMap;
import com.wzs.service.LoginService;
import com.wzs.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AccountMap accountMap;

    @Autowired
    private MailService mailService;

    @Override
    public boolean hasMatchUserById(int userId, String password) {
        return accountMap.getIdMatchCount(userId, password) > 0;
    }

    @Override
    public boolean hasMatchUserByEmail(String email, String password) {
        return accountMap.getEmailMatchCount(email, password) > 0;
    }

    @Override
    public Account findAccountById(int userId) {
        return accountMap.findAccountById(userId);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountMap.findAccountByEmail(email);
    }

    @Override
    public boolean reUserPassword(int readerId, String newPassword) {
        return accountMap.resetPassword(readerId, newPassword) > 0;
    }

    @Override
    public String getUserPassword(int userId) {
        return accountMap.getPassword(userId);
    }

    @Override
    public boolean addAccount(Account account) {
        int res = accountMap.addAccount(account);
        if (res > 0) {
            String code = account.getActiveCode();
            //主题
            String subject = "MicroKnowledge微知识平台注册用户验证信息";
            //上面的激活码发送到用户注册邮箱
            //本地运行可以使用 http://localhost:8088/
            String context = "亲爱的用户,您好！<br><br>" +
                    "欢迎注册微知识账号，请访问该链接，完成用户验证：<br> " +
                    "<a href=\"http://114.115.151.39:18088/user/checkCode?code="+code+"\">账号注册链接"+"</a>"
                    +"<br>若不是您本人的操作，请忽略该邮件，感谢您的配合~<br><br>"+"祝好，<br>微知识团队";
            //发送激活邮件
            mailService.sendMimeMail (account.getEmail(),subject,context);
        }
        return res > 0;
    }

    @Override
    public Account findAccountByActiveCode(String activeCode) {
        return accountMap.findAccountByActiveCode(activeCode);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMap.updateAccount(account);
    }
}

