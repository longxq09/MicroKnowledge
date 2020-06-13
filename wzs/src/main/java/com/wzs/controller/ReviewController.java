package com.wzs.controller;

import com.wzs.bean.Message;
import com.wzs.bean.MicroEvidence;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.Review;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.bean.selfEnum.ReviewType;
import com.wzs.service.MNoticeService;
import com.wzs.service.MessageService;
import com.wzs.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Resource
    private MNoticeService noticeService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private MessageService messageService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getReviewList", method = RequestMethod.GET)
    public List<MicroNotice> getReviewList(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("u"));
        List<MicroNotice> retList = new LinkedList<>();
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("judge",0);
        List<MicroNotice> reviewList = noticeService.queryMNotice(queryMap);
        for(MicroNotice n : reviewList){
            if(n.getAuthorID()!= userId){
                retList.add(n);
            }
        }
        return retList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/userToReview", method = RequestMethod.POST)
    public Map<String,Object> userToReview(HttpServletRequest request){

        int userId = Integer.parseInt(request.getParameter("userId"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        return  getResult(userId,noticeId);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/doReview", method = RequestMethod.POST)
    public Map<String,Object> doReview(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int noticeId = Integer.parseInt(request.getParameter("noticeId"));
        int type = Integer.parseInt(request.getParameter("type"));
        Review userReview = reviewService.getUserReviewToNotice(userId,noticeId);
        if(userReview == null){
            if(type != 0){
                reviewService.addReview(userId,noticeId,type);
            }
        } else {
            if(type != 0){
                reviewService.updateReview(userId,noticeId,type);
            } else {
                reviewService.delUserReview(userId,noticeId);
            }
        }
        return getResult(userId,noticeId);
    }

    public Map<String,Object> getResult(int userId,int noticeId){
        Review userReview = reviewService.getUserReviewToNotice(userId,noticeId);
        List<Review> allReviews = reviewService.getReviewsByNotice(noticeId);
        Map<String,Object> retMap = new HashMap<>();
        if(userReview == null){
            retMap.put("type",0);
        } else {
            retMap.put("type",userReview.getType());
        }
        int pass_num = 0;
        int unpass_num = 0;
        for(Review r : allReviews){
            if(r.getType()== ReviewType.YES.getIndex()){
                pass_num++;
                if(pass_num==3){
                    handlePass(userId,noticeId);
                }
            }
            if(r.getType()== ReviewType.NO.getIndex()){
                unpass_num++;
                if(unpass_num==3){
                    handleFail(userId,noticeId);
                }
            }
        }
        retMap.put("pass_num",pass_num);
        retMap.put("unpass_num",unpass_num);
        return retMap;
    }

    public void handlePass(int userId,int noticeId){
        reviewService.delReviewsByNotice(noticeId);
        MicroNotice notice = noticeService.getMNoticeById(noticeId);
        notice.setJudge(1);
        noticeService.updateMNotice(notice);

        Message message = new Message();
        message.setUserId(notice.getAuthorID());
        message.setType(MessageType.PASS.getIndex());
        message.setRelatedNoticeId(noticeId);
        message.setRelatedNoticeTitle(notice.getTitle());
        message.setRelatedNoticeType(notice.getType());
        message.setTime(new Date());

        messageService.addMessage(message);
    }

    public void handleFail(int userId,int noticeId){
        reviewService.delReviewsByNotice(noticeId);
        MicroNotice notice = noticeService.getMNoticeById(noticeId);
        notice.setJudge(-1);
        noticeService.updateMNotice(notice);

        Message message = new Message();
        message.setUserId(notice.getAuthorID());
        message.setType(MessageType.FAIL.getIndex());
        message.setRelatedNoticeId(noticeId);
        message.setRelatedNoticeTitle(notice.getTitle());
        message.setRelatedNoticeType(notice.getType());
        message.setTime(new Date());

        messageService.addMessage(message);

    }

}
