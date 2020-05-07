package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.PasswordHelper;
import com.wzs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class LoginController {

    private LoginService loginService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PasswordHelper helper = new PasswordHelper();
        password = helper.encryptByName(email, password);   //加密
        boolean isUser = loginService.hasMatchUserByEmail(email, password);
        // boolean isAdmin = loginService.hasMatchAdmin(email, password);
        HashMap<String, String> res = new HashMap<>();
        if (isUser) {
            Account account = loginService.findAccountByEmail(email);
            //request.getSession().setAttribute("account", account);
            res.put("id", "" + account.getId());
            res.put("code", "0");
            res.put("message", "登陆成功！");
        } else {
            res.put("code", "1");
            res.put("message", "账号或密码错误！");
        }
        return res;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/repassword", method = RequestMethod.POST)
    public @ResponseBody
    Object reUserPassword(HttpServletRequest request) {
        String oldPasswd = request.getParameter("oldPasswd");
        String newPasswd = request.getParameter("newPasswd");
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        PasswordHelper helper = new PasswordHelper();
        oldPasswd = helper.encryptByName(email, oldPasswd);
        String password = loginService.getUserPassword(id);
        HashMap<String, String> res = new HashMap<>();
        if (password.equals(oldPasswd)) {
            newPasswd = helper.encryptByName(email, newPasswd);
            if (loginService.reUserPassword(id, newPasswd)) {
                res.put("code", "0");
                res.put("message", "密码修改成功！");
            } else {
                res.put("code", "1");
                res.put("message", "密码修改失败！");
            }
        } else {
            res.put("code", "1");
            res.put("message", "旧密码错误！");
        }
        return res;
    }

}

}