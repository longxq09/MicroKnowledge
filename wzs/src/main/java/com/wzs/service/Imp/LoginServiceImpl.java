package com.wzs.service.Imp;

import com.wzs.bean.Account;
import com.wzs.bean.Admin;
import com.wzs.mapper.AccountMapper;
import com.wzs.mapper.AdminMapper;
import com.wzs.service.LoginService;
import com.wzs.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AccountMapper accountMapper;


    @Resource
    private AdminMapper adminMapper;


    @Autowired
    private MailService mailService;

    @Override
    public boolean hasMatchUserById(int userId, String password) {
        return accountMapper.getIdMatchCount(userId, password) > 0;
    }

    @Override
    public boolean hasMatchUserByEmail(String email, String password) {
        return accountMapper.getEmailMatchCount(email, password) > 0;
    }

    @Override
    public Account findAccountById(int userId) {
        return accountMapper.findAccountById(userId);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountMapper.findAccountByEmail(email);
    }

    @Override
    public boolean reUserPassword(int id, String newPassword) {
        return accountMapper.resetPassword(id, newPassword) > 0;
    }

    @Override
    public String getUserPassword(int userId) {
        return accountMapper.getPassword(userId);
    }

    @Override
    public boolean addAccount(Account account) {
        int res = accountMapper.addAccount(account);
        if (res > 0) {
            String code = account.getActiveCode();
            //主题
            String subject = "MicroKnowledge微知识平台注册用户验证信息";
            //上面的激活码发送到用户注册邮箱
            //本地运行可以使用 http://localhost:18088/
            String context = "亲爱的用户,您好！<br><br>" +
                    "欢迎注册微知识账号，请访问该链接，完成用户验证：<br> " +
//                    "<a href=\"http://114.115.151.39:18088/user/checkCode?code=" + code + "\">账号注册链接" + "</a>"
                    "<a href=\"http://localhost:18088/user/checkCode?code="+code+"\">账号注册链接"+"</a>"
                    + "<br>若不是您本人的操作，请忽略该邮件，感谢您的配合~<br><br>" + "祝好，<br>微知识团队";
            //发送激活邮件
            mailService.sendMimeMail(account.getEmail(), subject, context);
        }
        return res > 0;
    }

    @Override
    public Account findAccountByActiveCode(String activeCode) {
        return accountMapper.findAccountByActiveCode(activeCode);
    }

    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    @Override
    public boolean forgetPassword(Account account) {
        updateAccount(account);
        String code = account.getActiveCode();
        String subject = "MicroKnowledge微知识平台用户忘记密码服务";

        String context = "亲爱的用户,您好！<br><br>" +
                "欢迎使用忘记密码服务，验证码如下：<br>" + code +
                "<br>若不是您本人的操作，请忽略该邮件，感谢您的配合~<br><br>" + "祝好，<br>微知识团队";
        //发送激活邮件
        mailService.sendMimeMail(account.getEmail(), subject, context);
        return true;
    }

    @Override
    public boolean hasMatchAdminByEmail(String email, String password) {
        return adminMapper.getAdminMatch(email, password) > 0;
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminMapper.findAdminByEmail(email);
    }

    @Override
    public boolean resetAdminPassword(int id, String newPassword) {
        return adminMapper.resetAdminPassword(id, newPassword) > 0;
    }

    @Override
    public String getAdminPassword(int id) {
        return adminMapper.getAdminPassword(id);
    }
}

