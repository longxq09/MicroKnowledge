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


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserInfoService userInfoService;

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Object registerCheck(HttpServletRequest request) {
        String name;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserInfo userInfo = new UserInfo();
        if ((name = request.getParameter("name")) != null) {
            userInfo.setName(name);
        }
        userInfo.setEmail(email);
        PasswordHelper helper = new PasswordHelper();
        password = helper.encryptByName(email, password);   //加密
        int ok = userInfoService.addUserInfo(userInfo);
        Account account = new Account();
        account.setActiveCode(getUUID());
        account.setActiveStatus(0);
        account.setEmail(email);
        account.setPassword(password);
        HashMap<String, String> res = new HashMap<>();
        if (ok > 0 && loginService.addAccount(account)) {
            res.put("code", "0");
            res.put("message", "注册成功！");
        } else {
            res.put("code", "1");
            res.put("message", "注册失败！");
        }
        return res;
    }

    //  校验激活码
    @CrossOrigin
    @RequestMapping(value = "/checkCode")
    public @ResponseBody
    String checkCode(String code) {
        Account user = loginService.findAccountByActiveCode(code);
        //code正确，把用户状态修改status=1
        if (user != null) {
            user.setActiveStatus(1);
            //把code验证码清空，已经不需要了
            user.setActiveCode("");
            loginService.updateAccount(user);
            return "校验成功";
        }

        return "校验失效";
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public @ResponseBody
    String logOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "退出成功";
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
            if (account.getActiveStatus() == 0) {
                res.put("code", "2");
                res.put("message", "账号尚未激活！");
                return res;
            }
            UserInfo userInfo = userInfoService.getUserInfo(account.getId());
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
    @RequestMapping(value = "/rePassword", method = RequestMethod.POST)
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

    //  忘记密码时，发送邮件验证
    @CrossOrigin
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public @ResponseBody
    Object forgetPassword(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        String email = request.getParameter("email");
        Account account = loginService.findAccountByEmail(email);
        if (account == null) {
            res.put("code", "1");
            res.put("message", "邮箱不存在！");
            return res;
        }

        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);       //四位随机字母数字
        account.setActiveCode(result);
        account.setActiveStatus(0);
        loginService.forgetPassword(account);
        res.put("code", "0");
        res.put("message", "ok");

        return res;
    }

    //  校验激活码（忘记密码时）
    @CrossOrigin
    @RequestMapping(value = "/checkForgetCode", method = RequestMethod.POST)
    public @ResponseBody
    Object checkForgetCode(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        Account user = loginService.findAccountByEmail(email);
        if (user.getActiveCode().equals(code)) {
            user.setActiveStatus(1);    //code正确，把用户状态修改status=1
            user.setActiveCode("");
            loginService.updateAccount(user);
            res.put("code", "0");
        } else {
            res.put("code", "1");
        }
        return res;
    }

    //  校验激活码后，重设密码（忘记密码）
    @CrossOrigin
    @RequestMapping(value = "/resetForget", method = RequestMethod.POST)
    public @ResponseBody
    Object resetForget(HttpServletRequest request) {
        String newPassword = request.getParameter("newPassword");
        String email = request.getParameter("email");
        PasswordHelper helper = new PasswordHelper();
        int id = loginService.findAccountByEmail(email).getId();
        HashMap<String, String> res = new HashMap<>();
        newPassword = helper.encryptByName(email, newPassword);
        if (loginService.reUserPassword(id, newPassword)) {
            res.put("code", "0");
            res.put("message", "密码修改成功！");
        } else {
            res.put("code", "1");
            res.put("message", "密码修改失败！");
        }
        return res;
    }
}

