package com.wzs.controller;

import com.wzs.bean.Comment;
import com.wzs.bean.Message;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 15:14
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    public List<Message> getMessages(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Message> messageList = messageService.selectMessageByUser(userId);
        messageList.sort((m1, m2) -> {
            if(m1.getFlag()==m1.getFlag()){
                return m1.getTime().compareTo(m2.getTime());
            } else {
                return m1.getFlag() - m2.getFlag();
            }
        });
        messageService.setFlagByUser(userId);   //改为已阅
        return messageList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/selectMessages", method = RequestMethod.POST)
    public List<Message> selectMessages(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int type =  Integer.parseInt(request.getParameter("type"));
        int flag =  Integer.parseInt(request.getParameter("flag"));
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("userId",userId);
        if(type != -1){
            queryMap.put("userId",type);
        }
        if(flag != -1){
            queryMap.put("flag",flag);
        }
        List<Message> messageList = messageService.selectMessage(queryMap);
        messageList.sort((m1, m2) -> {
            if(m1.getFlag()==m1.getFlag()){
                return m1.getTime().compareTo(m2.getTime());
            } else {
                return m1.getFlag() - m2.getFlag();
            }
        });
        messageService.setFlagByUser(userId);   //改为已阅
        return messageList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public int[] getCount(HttpServletRequest request){
        int[ ] array = new int[10];
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Message> messages = messageService.selectMessageByUser(userId);
        for(Message m : messages){
            array[m.getType()]++;
            if(m.getFlag()==1){
                array[0]++;
            }
        }
        return array;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/followMessage", method = RequestMethod.POST)
    public int followMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.FOLLOW.getIndex());

        message.setUserId(Integer.parseInt(request.getParameter("userId")));
        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
//        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
//        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
//        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
//        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/likeMessage", method = RequestMethod.POST)
    public int likeMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.LIKE.getIndex());

        message.setUserId(Integer.parseInt(request.getParameter("userId")));
        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/favoriteMessage", method = RequestMethod.POST)
    public int favoriteMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.FAVORITE.getIndex());

        message.setUserId(Integer.parseInt(request.getParameter("userId")));
        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
//        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
//        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
//        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
//        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/commentMessage", method = RequestMethod.POST)
    public int commentMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.COMMENT.getIndex());

        message.setUserId(Integer.parseInt(request.getParameter("userId")));
        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/replyMessage", method = RequestMethod.POST)
    public int replyMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.REPLY.getIndex());

        message.setUserId(Integer.parseInt(request.getParameter("userId")));
        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/passMessage", method = RequestMethod.POST)
    public int passMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.PASS.getIndex());

//        message.setUserId(Integer.parseInt(request.getParameter("userId")));
//        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
//        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/failMessage", method = RequestMethod.POST)
    public int failMessage(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.FAIL.getIndex());

//        message.setUserId(Integer.parseInt(request.getParameter("userId")));
//        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
//        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    public int broadcast(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.ADMIN.getIndex());

//        message.setUserId(Integer.parseInt(request.getParameter("userId")));
//        message.setUserId(Integer.parseInt(request.getParameter("fromUserId"))); //TODO maybe session
//        message.setFromUserName(request.getParameter("fromUserName")); //TODO maybe session
//        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("relatedNoticeId")));
//        message.setRelatedNoticeType(Integer.parseInt(request.getParameter("relatedNoticeType")));
//        message.setRelatedNoticeTitle(request.getParameter("relatedNoticeTitle"));
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());

        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/haveReadALL", method = RequestMethod.POST)
    public int haveReadALL(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        messageService.setFlagByUser(userId);
        return 0;
    }

}
