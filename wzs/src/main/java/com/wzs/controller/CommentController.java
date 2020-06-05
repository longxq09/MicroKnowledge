package com.wzs.controller;

import com.wzs.bean.Comment;
import com.wzs.bean.Message;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.UserInfo;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.CommentService;
import com.wzs.service.MNoticeService;
import com.wzs.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigestSpi;
import java.util.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/1 15:27
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private MNoticeService noticeService;
    @Resource
    private MessageService messageService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getCommentsOfNotice", method = RequestMethod.POST)
    public List<Comment> getCommentsOfNotice(HttpServletRequest request) {
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        List<Comment> commentList = commentService.selectCommentOfANotice(noticeId);
        commentList.sort(Comparator.comparing(Comment::getTime).reversed());
        return commentList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public int addComment(HttpServletRequest request) {
        Comment comment = new Comment();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");

        comment.setNoticeId(Integer.parseInt(request.getParameter("noticeId")));
        comment.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
        comment.setFromId(userInfo.getId());
        comment.setFromName(userInfo.getName());
        comment.setToId(-1);
        comment.setContent(request.getParameter("content"));
        comment.setTime(new Date());
        commentService.insertComment(comment);

        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/replyComment", method = RequestMethod.POST)
    public int replyComment(HttpServletRequest request) {
        Comment comment = new Comment();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");

        comment.setNoticeId(Integer.parseInt(request.getParameter("noticeId")));
        comment.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
        comment.setFromId(userInfo.getId());
        comment.setFromName(userInfo.getName());
        comment.setToId(Integer.parseInt(request.getParameter("toId")));
        comment.setToName(request.getParameter("toName"));
        comment.setContent(request.getParameter("content"));
        comment.setTime(new Date());
        commentService.insertComment(comment);

        Message message = new Message();
        if(Integer.parseInt(request.getParameter("toId"))==-1){ //评论
            message.setUserId(Integer.parseInt(request.getParameter("authorId")));
            message.setType(MessageType.COMMENT.getIndex());
        } else{ //回复评论
            message.setUserId(Integer.parseInt(request.getParameter("toId")));
            message.setType(MessageType.REPLY.getIndex());
        }
        message.setFromUserId(userInfo.getId());
        message.setFromUserName(userInfo.getName());
        message.setRelatedNoticeId(Integer.parseInt(request.getParameter("noticeId")));
        Map<String,Object> querMap = new HashMap<>();
        querMap.put("id",message.getRelatedNoticeId());
        MicroNotice notice =  noticeService.queryMNotice(querMap).get(0);
        message.setRelatedNoticeTitle(notice.getTitle());
        message.setRelatedNoticeType(notice.getType());
        message.setDetail(request.getParameter("content"));
        message.setTime(new Date());

        messageService.addMessage(message);

        return 0;
    }

}
