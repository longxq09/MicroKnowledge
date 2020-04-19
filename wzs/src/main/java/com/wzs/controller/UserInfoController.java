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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private LoginService loginService;


    private UserInfo getPersonInfo(long id, String name, String sex, String birth, String address, String phone) {
        UserInfo personInfo = new UserInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personInfo.setInfo(id, name, sex, date, address, phone);
        return personInfo;
    }



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
    public String userInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        Account account = (Account) request.getSession().getAttribute("account");
        UserInfo personInfo = getPersonInfo(account.getId(), name, sex, birth, address, phone);
        if (userInfoService.editUserInfo(personInfo) && userInfoService.editReaderCard(personInfo)) {
            Account accountNew = loginService.findAccountById(account.getId());
            request.getSession().setAttribute("account", accountNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/user_info.html";
    }
}
