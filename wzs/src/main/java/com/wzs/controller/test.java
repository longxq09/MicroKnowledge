package com.wzs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/15 12:53
 */
@Controller
public class test {
    public String sayHello(){
        return "index";
    }
}
