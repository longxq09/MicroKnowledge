package com.wzs.controller;

import com.wzs.bean.*;
import com.wzs.bean.selfEnum.NoticeType;
import com.wzs.service.*;
import com.wzs.util.Similarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HEAD;
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
    @Resource
    private UserInfoService userInfoService;

    private int oldFavSize = 0;
    private int oldLikeSize = 0;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNotices", method = RequestMethod.GET)
    public List<MicroNotice> getNotices(HttpServletRequest request) {
        // 标识用户
        int userId = Integer.parseInt(request.getParameter("accountId"));
        // 最后输出
        Set<MicroNotice> resultRecommend = new HashSet<>();
        // 获取当前用户的信息
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        // 获取专业兴趣
        String expertiseStr = null;
        String interest = null;
        try {
            expertiseStr = userInfo.getExpertise();
            interest = userInfo.getInterest();
        } catch (Exception ignore) {

        }
        // 用户个人的兴趣领域推荐
        Set<String> newTopic = new HashSet<>();
        if (interest != null) {
            newTopic.addAll(Arrays.asList(interest.split("-")));
        }
        if (expertiseStr != null) {
            newTopic.addAll(Arrays.asList(expertiseStr.split("-")));
        }
        if (newTopic.size() != 0) {
            for (String id: newTopic) {
                List<MicroNotice> mnTemp = noticeService.selectNoticeByTopic(id);
                if (mnTemp.size() > 4) {
                    resultRecommend.addAll(mnTemp.subList(0,4));
                } else {
                    resultRecommend.addAll(mnTemp);
                }
            }

        }
        // 获取当前数据库topic的数量
        Similarity.dim = topicService.getTopicCount() + 1;
        // 收藏推荐
        List<Integer> favNoticeIds = favoriteService.selectFavoriteByUserId(userId);

        MicroNotice microSrc = null;
        if (favNoticeIds.size() != 0) {
            Random random = new Random();
            microSrc = noticeService.getMNoticeById(favNoticeIds.get(random.nextInt(favNoticeIds.size())));
        }
        if (microSrc != null) {
            String[] srcTopic = microSrc.getTopic().split("-");
            int[] srcArr = new int[Similarity.dim];
            for (String str : srcTopic) {
                try {
                    srcArr[Integer.parseInt(str)] = 1;
                } catch (Exception ignored) {
                }
            }
            List<MicroNotice> allNotice = noticeService.selectAllNotice();
            for (MicroNotice microNotice : allNotice) {
                String[] dstTopic = microNotice.getTopic().split("-");
                int[] dstArr = new int[Similarity.dim];
                for (String str : dstTopic) {
                    try {
                        dstArr[Integer.parseInt(str)] = 1;
                    } catch (Exception ignored) {

                    }
                }
                if (Similarity.calCosineSimilarity(srcArr,dstArr) > 0.5 && Similarity.calCosineSimilarity(srcArr,dstArr) < 0.90) {
                    resultRecommend.add(microNotice);
                }
            }
        }
        microSrc = null;
        List<Integer> likeNoticeIds = likeService.getLikeNoticeIdByUserId(userId);
        if (likeNoticeIds.size() > 0) {
            Random random = new Random();
            microSrc = noticeService.getMNoticeById(likeNoticeIds.get(random.nextInt(likeNoticeIds.size())));
        }
        if (microSrc != null) {
            String[] srcTopic = microSrc.getTopic().split("-");
            int[] srcArr = new int[Similarity.dim];
            for (String str : srcTopic) {
                try {
                    srcArr[Integer.parseInt(str)] = 1;
                } catch (Exception ignored) {

                }
            }
            List<MicroNotice> allNotice = noticeService.selectAllNotice();
            for (MicroNotice microNotice : allNotice) {
                String[] dstTopic = microNotice.getTopic().split("-");
                int[] dstArr = new int[Similarity.dim];
                for (String str : dstTopic) {
                    try {
                        dstArr[Integer.parseInt(str)] = 1;
                    } catch (Exception ignored) {

                    }
                }
                if (Similarity.calCosineSimilarity(srcArr,dstArr) > 0.5 && Similarity.calCosineSimilarity(srcArr,dstArr) < 0.90) {
                    resultRecommend.add(microNotice);
                }
            }

        }
        if (resultRecommend.size() > 10) {
            List<MicroNotice> resList = new ArrayList<>(resultRecommend);
            Collections.shuffle(resList);
            return resList;
        } else {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("judge",1);
            List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
            Collections.shuffle(noticeList);
            if (noticeList.size() > 20) {
                return noticeList.subList(0,20);
            } else {
                return noticeList;
            }
        }
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
//            String referStr = notice.getReference();
//            String[] referList = referStr.split("-");
//            String referTitleList = "";
//            for(String r : referList){
//                if(r.isEmpty()){
//                    continue;
//                }
//                MicroNotice temp = noticeService.getMNoticeById(Integer.parseInt(r));
//                referTitleList = referTitleList.concat(temp.getTitle());
//                referTitleList = referTitleList.concat("-");
//            }
//            retMap.put("reference",referTitleList.substring(0,referTitleList.length()-1));
            retMap.put("reference",notice.getReference());
        } else {
            List<String> refList = new LinkedList<>();
            String referStr = notice.getReference();
            String[] referList = referStr.split("-");
            for(String r : referList){
                if(r.isEmpty()){
                    continue;
                }
                refList.add(r);
            }
            retMap.put("reference",refList);
        }

        List<Topic> topicList = new LinkedList<>();
        String topicStr = notice.getTopic();
        String[] topicIdList = topicStr.split("-");
        StringBuilder topicNewList = new StringBuilder();
        for(String t : topicIdList){
            if(t.isEmpty()){
                continue;
            }
            topicNewList.append(t);
            topicNewList.append("-");
//            Topic topic = topicService.getTopicById(Integer.parseInt(t));
//            topicList.add(topic);
        }
        retMap.put("topic",topicNewList.substring(0,topicNewList.length()-1));
//        retMap.put("topic",topicList);

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
        if (noticeList.size() > 20) {
            noticeList = noticeList.subList(0,20);
        }
        return noticeList;
    }

}
