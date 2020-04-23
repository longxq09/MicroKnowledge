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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            request.getSession().setAttribute("account", account);
            res.put("code", "1");
            res.put("message", "登陆成功！");
        } else {
            res.put("code", "0");
            res.put("message", "账号或密码错误！");
        }
        return res;
    }

    /*
    @RequestMapping("/user_main.html")
    public ModelAndView toUserMain(HttpServletResponse response) {
        return new ModelAndView("user_main");
    }

    @RequestMapping("/repasswd.html")
    public ModelAndView reFreshPasswd() {
        return new ModelAndView("repasswd");
    }

    @RequestMapping("/repasswd_do")
    public String reUserPasswdDo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Account user = (Account) request.getSession().getAttribute("account");
        String oldPasswd = request.getParameter("oldPasswd");
        String newPasswd = request.getParameter("newPasswd");
        long id = user.getId();
        String email = user.getEmail();
        PasswordHelper helper = new PasswordHelper();
        oldPasswd = helper.encryptByName(email, oldPasswd);
        String password = loginService.getUserPassword(id);

        if (password.equals(oldPasswd)) {
            newPasswd = helper.encryptByName(email, newPasswd);

            if (loginService.reUserPassword(id, newPasswd)) {
                redirectAttributes.addFlashAttribute("success", "密码修改成功！");
                return "redirect:/repasswd.html";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/repasswd.html";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/repasswd.html";
        }
    }

    @RequestMapping(value = {"/", "/main.html"})
    public String toIndex(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("/out.html")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/main.html";
    }

    @RequestMapping("*")
    public String notFind() {
        return "404";
    }*/

}