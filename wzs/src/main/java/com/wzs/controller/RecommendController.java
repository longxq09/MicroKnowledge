package com.wzs.controller;

import com.wzs.bean.MicroNotice;
import com.wzs.bean.Topic;
import com.wzs.bean.UserInfo;
import com.wzs.bean.UserRating;
import com.wzs.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazae 2020/5/20 23:15
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private UserRatingService userRatingService;
    @Resource
    private MNoticeService noticeService;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private TopicService topicService;
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;
    @Resource
    private FavoriteService favoriteService;

    public int refreshUserRating(int userId){
        Map<String, Object> queryMap = new HashMap<>();
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        for(MicroNotice n :noticeList){
            int noticeId = n.getId();
            int likeCount = likeService.checkLike(noticeId,userId)? 2:0;
            int CommentCount = commentService.getCommentNumByfromIdAndNoticeId(userId,noticeId);
            Map<String, Object> fmap = new HashMap<>();
            fmap.put("userID",userId);
            fmap.put("noticeID",noticeId);
            int favouriteCount = favoriteService.selectFavorite(fmap).isEmpty()? 0:5;

            int score = likeCount + CommentCount + favouriteCount;
            if(userRatingService.checkByKey(userId, noticeId)){
                userRatingService.updateScoreByKey(userId,noticeId,score);
            } else {
                UserRating userRating = new UserRating();
                userRating.setUserId(userId);
                userRating.setNoticeId(noticeId);
                userRating.setScore(score);
                userRatingService.insertUserRating(userRating);
            }
        }
        return 0;
    }

    public int[] getTopicUserVec(int userId){
        List<Topic> allValidTopic = topicService.getALLValidTopic();
        int[] vec = new int[allValidTopic.size()+1];
        UserInfo userInfo =  userInfoService.getUserInfo(userId);
        String interests = userInfo.getInterest();
        String[] interestList = interests.split("-");
        for(String s : interestList){
            if(s.equals("")){
                continue;
            }
            int interestId = Integer.parseInt(s);
            vec[interestId] = 1;
        }
        return vec;
    }

    public int[] getTopicNoticeVec(int noticeId){
        List<Topic> allValidTopic = topicService.getALLValidTopic();
        int[] vec = new int[allValidTopic.size()+1];
        MicroNotice notice = noticeService.getMNoticeById(noticeId);
        String topicStr = notice.getTopic();
        String[] topicList = topicStr.split("-");
        for(String t : topicList){
            if(t.isEmpty()){
                continue;
            }
            int topicId = Integer.parseInt(t);
            vec[topicId] = 1;
        }
        return vec;
    }

}
