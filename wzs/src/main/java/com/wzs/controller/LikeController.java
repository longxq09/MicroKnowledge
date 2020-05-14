package com.wzs.controller;

import com.wzs.bean.Message;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.LikeService;
import com.wzs.service.MNoticeService;
import com.wzs.service.MessageService;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Resource
    private MNoticeService noticeService;
    @Resource
    private MessageService messageService;
    @Resource
    private UserInfoService userInfoService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkLikeState", method = RequestMethod.GET)
    public Map<String, Integer> checkLikeState(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        return likeService.checkLikeState(noticeId,id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/likeThis", method = RequestMethod.POST)
    public int likeThis(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        sendLikeMessage(id, noticeId);
        return likeService.addLike(noticeId,id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/cancelLike", method = RequestMethod.POST)
    public int cancelLike(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        return likeService.delLike(noticeId,id);
    }

    public void sendLikeMessage(int id,int noticeId){
        Message message = new Message();
        MicroNotice notice =  noticeService.getMNoticeById(noticeId);
        message.setUserId(notice.getAuthorID());
        message.setType(MessageType.LIKE.getIndex());
        message.setFromUserId(id);
        message.setFromUserName(userInfoService.getUserInfo(id).getName());
        message.setRelatedNoticeId(noticeId);
        message.setRelatedNoticeTitle(notice.getTitle());
        message.setRelatedNoticeType(notice.getType());
        message.setTime(new Date());

        messageService.addMessage(message);
    }

}
