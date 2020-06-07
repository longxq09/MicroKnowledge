package com.wzs.controller;

import com.wzs.bean.Message;
import com.wzs.bean.UserInfo;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.MessageService;
import com.wzs.service.UserInfoService;
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
    @Resource
    private UserInfoService userInfoService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMessages", method = RequestMethod.POST)
    public List<Message> getMessages(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Message> messageList = messageService.selectMessageByUser(userId);
        messageList.sort(Comparator.comparing(Message::getTime).reversed());
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
    @RequestMapping(value = "/getUnReadNum", method = RequestMethod.POST)
    public int getUnReadNum(HttpServletRequest request){
        int unReadNum = 0;
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<Message> messages = messageService.selectMessageByUser(userId);
        for(Message m : messages){
            if(m.getFlag()==1){
                unReadNum++;
            }
        }
        return unReadNum;
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
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public int feedback(HttpServletRequest request){
        Message message = new Message();
        message.setType(MessageType.FEEDBACK.getIndex());
        message.setUserId(0);
        int fromId = Integer.parseInt(request.getParameter("userId"));
        UserInfo u = userInfoService.getUserInfo(fromId);
        String fromName = u.getName();
        message.setFromUserId(fromId);
        message.setFromUserName(fromName);
        message.setDetail(request.getParameter("detail"));
        message.setTime(new Date());
        messageService.addMessage(message);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    public int broadcast(HttpServletRequest request){
        String detail = request.getParameter("detail");
        List<UserInfo> userList = userInfoService.getAllInfos();
        for(UserInfo u : userList){
            int userId = u.getId();
            Message message = new Message();
            message.setType(MessageType.BROAD.getIndex());
            message.setUserId(userId);
            message.setFromUserId(0);
            message.setDetail(detail);
            message.setTime(new Date());
            messageService.addMessage(message);
        }
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/adminReply", method = RequestMethod.POST)
    public int adminReply(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String detail = request.getParameter("detail");
        Message message = new Message();
        message.setType(MessageType.ADMIN.getIndex());
        message.setUserId(userId);
        message.setFromUserId(0);
        message.setFromUserName("管理君");
        message.setDetail(detail);
        message.setTime(new Date());
        messageService.addMessage(message);
        messageService.setFlagById(Integer.parseInt(request.getParameter("id")));
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getAdminMessage", method = RequestMethod.GET)
    public List<Message> getAdminMessage(HttpServletRequest request){
        List<Message> messageList = messageService.selectMessageByUser(0);
        messageList.sort(Comparator.comparing(Message::getTime).reversed());
        messageList.sort((m1, m2) -> {
            if(m1.getFlag()==m1.getFlag()){
                return m1.getTime().compareTo(m2.getTime());
            } else {
                return m1.getFlag() - m2.getFlag();
            }
        });
        return messageList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/setFinish", method = RequestMethod.POST)
    public int setFinish(HttpServletRequest request){
        messageService.setFlagById(Integer.parseInt(request.getParameter("id")));
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
