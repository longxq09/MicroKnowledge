package com.wzs.controller;


import com.wzs.bean.UserInfo;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.io.*;
import java.util.UUID;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    //前端通过控件传给后端的是经过base64编码的字符串
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/user/picUpload", method = RequestMethod.POST)
    public Object picUpload(String imgStr, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String rootPath = "/root";   //图片存放根路径
        String sonPath = "/user_pic/";  //图片存放根目录下的子目录
        String fileName = UUID.randomUUID().toString().replace("-", ""); // 获取文件名
        String filePath = rootPath + sonPath;   // 设置文件上传后的路径
        String imgPath = filePath + fileName;
        HashMap<String, String> res = new HashMap<>();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) { //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgPath);
            out.write(b);
            out.flush();
            out.close();
            //url保存到数据库中
            UserInfo userInfo = userInfoService.getUserInfo(id);
            userInfo.setPicture(imgPath);
            userInfoService.editUserInfo(userInfo);
            res.put("picture", imgPath);
            res.put("message", "上传成功！");
            return res;
        } catch (Exception e) {
            res.put("message", "上传失败！");
            return res;
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public @ResponseBody
    Object userInfoGet(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        UserInfo info = userInfoService.getUserInfo(id);
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
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.parseInt(request.getParameter("id")));
        userInfo.setName(request.getParameter("name"));
        userInfo.setSex(request.getParameter("sex"));
        userInfo.setAddress(request.getParameter("address"));
        userInfo.setSignature(request.getParameter("signature"));
        userInfo.setEducation(request.getParameter("education"));
        userInfo.setWorks(request.getParameter("works"));
        userInfo.setIntroduction(request.getParameter("introduction"));
        userInfo.setContribution(request.getParameter("contribution"));
        userInfo.setExpertise(request.getParameter("expertise"));
        userInfo.setInterest(request.getParameter("interest"));
        userInfo.setPicture(request.getParameter("picture"));

        HashMap<String, String> res = new HashMap<>();
        if (userInfoService.editUserInfo(userInfo)) {
//            Account accountNew = loginService.findAccountById(account.getId());
            request.getSession().setAttribute("userInfo", userInfo);
            res.put("code", "0");
            res.put("message", "信息修改成功！");
        } else {
            res.put("code", "500");
            res.put("message", "信息修改失败！");
        }
        return res;
    }

}
