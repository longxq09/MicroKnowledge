package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.PasswordHelper;
import com.wzs.bean.UserInfo;
import com.wzs.service.LoginService;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class LoginController {

    private LoginService loginService;
    @Resource
    private UserInfoService userInfoService;


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
            UserInfo userInfo =  userInfoService.getUserInfo(account.getId());
            request.getSession().setAttribute("userInfo", userInfo);
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
    @RequestMapping(value = "/user/rePassword", method = RequestMethod.POST)
    public @ResponseBody
    Object reUserPassword(HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        PasswordHelper helper = new PasswordHelper();
        oldPassword = helper.encryptByName(email, oldPassword);
        String password = loginService.getUserPassword(id);
        HashMap<String, String> res = new HashMap<>();
        if (password.equals(oldPassword)) {
            newPassword = helper.encryptByName(email, newPassword);
            if (loginService.reUserPassword(id, newPassword)) {
                res.put("code", "0");
                res.put("message", "密码修改成功！");
            } else {
                res.put("code", "1");
                res.put("message", "密码修改失败！");
            }
        } else {
            res.put("code", "2");
            res.put("message", "旧密码错误！");
        }
        return res;
    }

}

