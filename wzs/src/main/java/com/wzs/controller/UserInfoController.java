package com.wzs.controller;

import com.wzs.bean.Account;

import com.wzs.bean.UserInfo;
import com.wzs.service.LoginService;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private LoginService loginService;


    @RequestMapping("/user_info.html")
    public ModelAndView toUserInfo(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            return new ModelAndView("user_main");
        }
        UserInfo personInfo = userInfoService.getUserInfo(account.getId());
        ModelAndView modelAndView = new ModelAndView("userInfo");
        modelAndView.addObject("userInfo", personInfo);
        return modelAndView;
    }


    @RequestMapping("user_info_edit.html")
    public ModelAndView userInfoEdit(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        UserInfo personInfo = userInfoService.getUserInfo(account.getId());
        ModelAndView modelAndView = new ModelAndView("user_info_edit");
        modelAndView.addObject("userInfo", personInfo);
        return modelAndView;
    }

    @RequestMapping("user_edit_do_r.html")
    public String userInfoEditDo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Account account = (Account) request.getSession().getAttribute("account");
        String name, sex, address, signature, education, work, introduction, contribution;
        ArrayList<String> expertise;    //专业领域, not yet
        ArrayList<String> interest;    //偏好领域, not yet
        UserInfo userInfo = new UserInfo();
        if ((name = request.getParameter("name")) != null) {
            userInfo.setName(name);
        }
        if ((sex = request.getParameter("sex")) != null) {
            userInfo.setSex(sex);
        }
        if ((address = request.getParameter("address")) != null) {
            userInfo.setAddress(address);
        }
        if ((signature = request.getParameter("signature")) != null) {
            userInfo.setSignature(signature);
        }
        if ((education = request.getParameter("education")) != null) {
            userInfo.setEducation(education);
        }
        if ((work = request.getParameter("work")) != null) {
            userInfo.setWork(work);
        }
        if ((introduction = request.getParameter("introduction")) != null) {
            userInfo.setIntroduction(introduction);
        }
        if ((contribution = request.getParameter("contribution")) != null) {
            userInfo.setContribution(contribution);
        }
        if (userInfoService.editUserInfo(userInfo)) {
            Account accountNew = loginService.findAccountById(account.getId());
            request.getSession().setAttribute("account", accountNew);
            redirectAttributes.addFlashAttribute("success", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/user_info.html";
    }

    @RequestMapping("account_add.html")
    public ModelAndView uerInfoAdd(HttpServletResponse response) {
        return new ModelAndView("add_account");
    }

    @RequestMapping("/account_add_do.html")
    public String userInfoAddDo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setEmail(email);
        long ok = userInfoService.addUserInfo(userInfo);
        if (ok > 0 && loginService.addAccount(userInfo, password)) {
            redirectAttributes.addFlashAttribute("success", "添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "已存在，添加失败！");
        }
        return "redirect:/account_add.html";
    }

}
