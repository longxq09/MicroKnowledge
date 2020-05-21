package com.wzs.controller;

import com.wzs.bean.*;
import com.wzs.bean.selfEnum.NoticeType;
import com.wzs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 13:37
 */
@Controller
@RequestMapping("/mNotice")
public class MNoticeController {
    @Autowired
    private MNoticeService noticeService;
    @Resource
    private TopicService topicService;
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;
    @Resource
    private FavoriteService favoriteService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private UserRatingService userRatingService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNotices", method = RequestMethod.GET)
    public List<MicroNotice> getNotices() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("judge",1);
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        noticeList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
        return noticeList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getSelfNotices", method = RequestMethod.GET)
    public List<MicroNotice> getSelfNotices(HttpServletRequest request) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("authorID",request.getParameter("id"));
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        noticeList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
        return noticeList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public int deleteMEvid(int id){
        noticeService.deleteMNotice(id);
        reviewService.delReviewsByNotice(id);
        userRatingService.deleteUserRatingByNoticeId(id);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getReference", method = RequestMethod.POST)
    public List<MicroNotice> getReference(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        MicroNotice notice = noticeService.getMNoticeById(id);
        List<MicroNotice> evidList = new LinkedList<>();
        String referStr = notice.getReference();
        String[] referList = referStr.split("-");
        for(String r : referList){
            if(r.isEmpty()){
                continue;
            }
            MicroNotice evid = noticeService.getMNoticeById(Integer.parseInt(r));
            evidList.add(evid);
        }
        return evidList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNoticeById", method = RequestMethod.POST)
    private Map<String,Object> toModifyMEvid(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String,Object> queryMap = new HashMap();
        queryMap.put("id",id);
        List<MicroNotice> list = noticeService.queryMNotice(queryMap);
        MicroNotice notice = list.get(0);

        Map<String,Object> retMap = new HashMap();
        retMap.put("noticeId",notice.getId());
        retMap.put("type",notice.getType());
        retMap.put("authorId",notice.getAuthorID());
        retMap.put("authorName",notice.getAuthorName());
        retMap.put("keywords",notice.getKeywords());
        retMap.put("title",notice.getTitle());
        retMap.put("summary",notice.getSummary());
        retMap.put("judge",notice.getJudge());
        retMap.put("time",notice.getShowTime());

        if(notice.getType() == NoticeType.GUESS.getIndex()){
            String referStr = notice.getReference();
            String[] referList = referStr.split("-");
            String referTitleList = "";
            for(String r : referList){
                if(r.isEmpty()){
                    continue;
                }
                MicroNotice temp = noticeService.getMNoticeById(Integer.parseInt(r));
                referTitleList = referTitleList.concat(temp.getTitle());
                referTitleList = referTitleList.concat("-");
            }
            retMap.put("reference",referTitleList.substring(0,referTitleList.length()-1));
        } else {
            retMap.put("reference",notice.getReference());
        }

        String topicStr = notice.getTopic();
        String[] topicList = topicStr.split("-");
        String topicNameStr = "";
        for(String t : topicList){
            if(t.isEmpty()){
                continue;
            }
            Topic topic = topicService.getTopicById(Integer.parseInt(t));
            topicNameStr = topicNameStr.concat(topic.getTopicName());
            topicNameStr = topicNameStr.concat("-");
        }
        retMap.put("topic",topicNameStr.substring(0,topicNameStr.length()-1));

        return retMap;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getHotTemp", method = RequestMethod.GET)
    public List<MicroNotice> getHotTemp() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("judge",1);
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        Date nowTime = new Date();
        for(MicroNotice n :noticeList){
            Date ntime = n.getTime();
            int disHour = (int) (nowTime.getTime() - ntime.getTime())/ (1000 * 60 * 60 * 24);
            int likeCount = likeService.getLikeNumByNoticeId(n.getId());
            int CommentCount = commentService.getCommentNumByNoticeId(n.getId());
            int favouriteCount = favoriteService.getFavorNumByNoticeId(n.getId());
            double hot = (likeCount * 0.7 + CommentCount*0.3 + favouriteCount)*1000 / Math.pow(disHour+2,1.2);
            n.setHot(hot);
        }
        noticeList.sort(Comparator.comparing(MicroNotice::getHot).reversed());
        return noticeList;
    }

}
