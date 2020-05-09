package com.wzs.controller;


import com.wzs.bean.PasswordHelper;
import com.wzs.bean.UserInfo;
import com.wzs.service.LoginService;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.io.*;
import java.util.UUID;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @CrossOrigin
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
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
        long ok = userInfoService.addUserInfo(userInfo);

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

    public static boolean generateImage(String imgStr, String path) {
        //前端通过控件传给后端的是经过base64编码的字符串
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) { //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/user/picUpload", method = RequestMethod.POST)
    public String picUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (file.isEmpty()) {
            return "文件为空";
        }

        String rootPath = "/root";   //图片存放根路径
        String sonPath = "/user_pic/";  //图片存放根目录下的子目录
        String fileName = UUID.randomUUID() + file.getOriginalFilename(); // 获取文件名

        String filePath = rootPath + sonPath;   // 设置文件上传后的路径
        File dest = new File( filePath + fileName);  //创建文件路径
        String imgPath = (filePath + fileName);

        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //方法一
            file.transferTo(dest);  //将上传文件写到服务器上指定的文件


            //方法二， 如果上一个不行，可以试试这个
            /*
            OutputStream out = new FileOutputStream(filePath + fileName);
            byte[] b = file.getBytes();
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) { //调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            out.close();
            */

            //url保存到数据库中
            UserInfo userInfo = userInfoService.getUserInfo(id);
            userInfo.setPicture(imgPath);
            userInfoService.editUserInfo(userInfo);
            return "上传成功";
        } catch (Exception e) {
            return "上传失败";
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public @ResponseBody
    Object userInfoGet(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        int id = Integer.parseInt(request.getParameter("id"));
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name, sex, address, signature, education, works, introduction, contribution;
        String expertise, interest, picture;
        UserInfo userInfo = userInfoService.getUserInfo(id);
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
