package com.wzs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 15:14
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/followMessage", method = RequestMethod.POST)
    public int followMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/likeMessage", method = RequestMethod.POST)
    public int likeMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/favoriteMessage", method = RequestMethod.POST)
    public int favoriteMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/commentMessage", method = RequestMethod.POST)
    public int commentMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/replyMessage", method = RequestMethod.POST)
    public int replyMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/passMessage", method = RequestMethod.POST)
    public int passMessage(HttpServletRequest request){
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/failMessage", method = RequestMethod.POST)
    public int failMessage(HttpServletRequest request){
        return 0;
    }

}
