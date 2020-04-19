package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.PasswordHelper;
import com.wzs.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String password = request.getParameter("password");
        PasswordHelper helper = new PasswordHelper();
        password = helper.encrypt(id, password);

        boolean isUser = loginService.hasMatchUser(id, password);
        HashMap<String, String> res = new HashMap<>();
        if (isUser) {
            Account account = loginService.findAccountById(id);
            request.getSession().setAttribute("account", account);
            res.put("typeNum", "1");
            res.put("msg", "登陆成功！");
        } else {
            res.put("typeNum", "0");
            res.put("msg", "账号或密码错误！");
        }
        return res;
    }


    @RequestMapping("/log_in.html")
    public ModelAndView toLogIn(HttpServletResponse response) {
        return new ModelAndView("log_in");
    }


    @RequestMapping("/user_main.html")
    public ModelAndView toUserMain(HttpServletResponse response) {
        return new ModelAndView("user_main");
    }

    @RequestMapping("/repasswd.html")
    public ModelAndView reFreshPasswd() {
        return new ModelAndView("repasswd");
    }

    @RequestMapping("/repasswd_do")
    public String reUserPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        Account user = (Account) request.getSession().getAttribute("account");
        long id = user.getId();
        PasswordHelper helper = new PasswordHelper();
        oldPasswd = helper.encrypt(id, oldPasswd);
        String password = loginService.getUserPassword(id);

        if (password.equals(oldPasswd)) {
            newPasswd = helper.encrypt(id, newPasswd);

            if (loginService.reUserPassword(id, newPasswd)) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
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

    @RequestMapping("*")
    public String notFind() {
        return "404";
    }

}