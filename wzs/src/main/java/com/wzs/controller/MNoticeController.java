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

    private int round = 0;
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNotices", method = RequestMethod.GET)
    public List<MicroNotice> getNotices(HttpServletRequest request) {

        round = (round + 1) % 3;

        int userId = Integer.parseInt(request.getParameter("accountId"));
        System.out.println("userId is: " + userId);
        List<MicroNotice> resList = new ArrayList<>();
        if (round == 0) { // 专业领域topic, round == 0
            UserInfo userInfo = userInfoService.getUserInfo(userId);
            String expertiseStr = userInfo.getExpertise();
            String interest = userInfo.getInterest();
            List<String> res = new ArrayList<>();
            if (expertiseStr != null) {
                res.addAll(Arrays.asList(expertiseStr.split("-")));
            }
            if (interest != null) {
                res.addAll(Arrays.asList(interest.split("-")));
            }
            String id;
            System.out.println("res.size()" + res.size());
            if (res.size() != 0) {
                int count = 2;
                while (resList.size() < 10 && count > 0) {
                    Random rand = new Random();
                    id = res.get(rand.nextInt(Math.max((res.size() - 1), 0)));
                    count-=1;
                    resList.addAll(noticeService.selectNoticeByTopic(id));
                }
                if (resList.size() >= 10) {
//                    resList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
                    return resList;
                }
            }
        } else if (round == 1) { // favorite, round == 1
            Similarity.dim = 72;
            List<Integer> noticeIdList =  favoriteService.selectFavoriteByUserId(userId);
//            List<Integer> noticeIdList =  favoriteService.selectFavoriteByUserId(1);

            List<MicroNotice> allNotice = noticeService.selectAllNotice();
            // 分解topic
            int[] srcArr = new int[Similarity.dim];
            MicroNotice microSrc = noticeService.getMNoticeById(noticeIdList.get(noticeIdList.size() - 1));
            if (microSrc != null) {
                String[] srcTopic = microSrc.getTopic().split("-");
                for (String str : srcTopic) {
                    srcArr[Integer.valueOf(str)] = 1;
                }
                for (MicroNotice microNotice : allNotice) {
                    String[] dstTopic = microNotice.getTopic().split("-");
                    int[] dstArr = new int[Similarity.dim];
                    for (String str : dstTopic) {
                        dstArr[Integer.valueOf(str)] = 1;
                    }
                    if (Similarity.calCosineSimilarity(srcArr,dstArr) > 0.5) {
                        resList.add(microNotice);
                    }
                }
                if (resList.size() > 5) {
//                    resList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
                    return resList;
                }
            }
        } else if (round == 2) { // like, round == 2
            List<Integer> likeNoticeIds = likeService.getLikeNoticeIdByUserId(userId);
//            List<Integer> likeNoticeIds = likeService.getLikeNoticeIdByUserId(1);
            if (likeNoticeIds.size() != 0) {
                Random rand = new Random();
                int noticeId = likeNoticeIds.get(rand.nextInt(likeNoticeIds.size()));
                Similarity.dim = 72;
                int[] srcArr = new int[Similarity.dim];
                MicroNotice microSrc = noticeService.getMNoticeById(noticeId);
                if (microSrc != null) {
                    String[] srcTopic = microSrc.getTopic().split("-");
                    for (String str : srcTopic) {
                        srcArr[Integer.valueOf(str)] = 1;
                    }
                    List<MicroNotice> allNotice = noticeService.selectAllNotice();
                    for (MicroNotice microNotice : allNotice) {
                        String[] dstTopic = microNotice.getTopic().split("-");
                        int[] dstArr = new int[Similarity.dim];
                        for (String str : dstTopic) {
                            dstArr[Integer.valueOf(str)] = 1;
                        }
                        if (Similarity.calCosineSimilarity(srcArr,dstArr) > 0.5) {
                            resList.add(microNotice);
                        }
                    }
                    if (resList.size() > 5) {
//                        resList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
                        return resList;
                    }
                }
            }
        }
        System.out.println("default");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("judge",1);
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
//        noticeList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
        return noticeList.subList(0,20);

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

        System.out.println("get Hot Temp");
        return noticeList.subList(0,20);
    }

}
