package com.wzs.controller;

import com.wzs.bean.Comment;
import com.wzs.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getCommentsOfNotice", method = RequestMethod.POST)
    public List<Comment> getCommentsOfNotice(HttpServletRequest request) {
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        List<Comment> commentList = commentService.selectCommentOfANotice(noticeId);
//        commentList.sort();
        commentList.get(0).getDisTime();
        return commentList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public int addComment(HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setNoticeId(Integer.parseInt(request.getParameter("noticeId")));
        comment.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
        comment.setFromId(0);
        comment.setFromName("from");
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
        comment.setNoticeId(Integer.parseInt(request.getParameter("noticeId")));
        comment.setAuthorId(Integer.parseInt(request.getParameter("authorId"))); //TODO
        comment.setFromId(0);               //TODO
        comment.setFromName("from");        //TODO
        comment.setToId(Integer.parseInt(request.getParameter("toId")));
        comment.setToName(request.getParameter("toName"));
        comment.setContent(request.getParameter("content"));
        comment.setTime(new Date());
        commentService.insertComment(comment);
        return 0;
    }


}
