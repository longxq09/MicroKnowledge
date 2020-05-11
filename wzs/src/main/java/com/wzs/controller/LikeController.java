package com.wzs.controller;

import com.wzs.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikeService likeService;


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
    @RequestMapping(value = "/likeThis", method = RequestMethod.GET)
    public int likeThis(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        return likeService.addLike(noticeId,id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/cancelLike", method = RequestMethod.GET)
    public int cancelLike(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        return likeService.delLike(noticeId,id);
    }
}
