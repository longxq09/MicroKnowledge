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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private LoginService loginService;


    @CrossOrigin
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public @ResponseBody
    Object registerCheck(HttpServletRequest request) {
        String name;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        UserInfo userInfo = new UserInfo();
        if ((name = request.getParameter("name")) != null) {
            userInfo.setName(name);
        }
        userInfo.setEmail(email);
        PasswordHelper helper = new PasswordHelper();
        password = helper.encryptByName(email, password);   //加密
        long ok = userInfoService.addUserInfo(userInfo);
        System.out.println("ok: " + ok);
        HashMap<String, String> res = new HashMap<>();
        if (ok > 0 && loginService.addAccount(userInfo, password)) {
            res.put("code", "0");
            res.put("message", "注册成功！");
        } else {
            res.put("code", "1");
            res.put("message", "注册失败！");
        }
        System.out.println(res);
        return res;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public @ResponseBody
    Object userInfoGet(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        HashMap<String, String> res = new HashMap<>();
        if (account == null) {
            res.put("message", "信息获取失败！");
            return res;
        }
        UserInfo info = userInfoService.getUserInfo(account.getId());
        res.put("sex", info.getSex());
        res.put("name", info.getName());
        res.put("address", info.getAddress());
        res.put("signature", info.getSignature());
        res.put("education", info.getEducation());
        res.put("works", info.getWorks());
        res.put("introduction", info.getIntroduction());
        res.put("contribution", info.getContribution());
        res.put("expertise", info.getExpertise());  //专业领域, id-id-id
        res.put("interest", info.getInterest());     //偏好领域, id-id-id
        res.put("picture", info.getPicture());       //头像的url
        return res;
    }

    @CrossOrigin
    @RequestMapping(value = "/user/info", method = RequestMethod.POST)
    public @ResponseBody
    Object userInfoEdit(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        String name, sex, address, signature, education, works, introduction, contribution;
        String expertise, interest, picture;
        UserInfo userInfo = userInfoService.getUserInfo(account.getId());
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
        if ((works = request.getParameter("works")) != null) {
            userInfo.setWorks(works);
        }
        if ((introduction = request.getParameter("introduction")) != null) {
            userInfo.setIntroduction(introduction);
        }
        if ((contribution = request.getParameter("contribution")) != null) {
            userInfo.setContribution(contribution);
        }
        if ((expertise = request.getParameter("expertise")) != null) {
            userInfo.setExpertise(expertise);
        }
        if ((interest = request.getParameter("interest")) != null) {
            userInfo.setInterest(interest);
        }
        if ((picture = request.getParameter("picture")) != null) {
            userInfo.setPicture(picture);
        }
        System.out.println("contribution: " + contribution + "  interest: " + interest);
        HashMap<String, String> res = new HashMap<>();
        if (userInfoService.editUserInfo(userInfo)) {
            Account accountNew = loginService.findAccountById(account.getId());
            request.getSession().setAttribute("account", accountNew);
            res.put("code", "0");
            res.put("message", "信息修改成功！");
        } else {
            res.put("code", "500");
            res.put("message", "信息修改失败！");
        }
        return res;
    }

}
