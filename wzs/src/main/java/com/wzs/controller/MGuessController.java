package com.wzs.controller;

import com.wzs.bean.*;
import com.wzs.service.FavoriteService;
import com.wzs.service.MNoticeService;
import com.wzs.service.ReviewService;
import com.wzs.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.wzs.bean.selfEnum.NoticeType.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 16:41
 */
@Controller
@RequestMapping("/mGuess")
public class MGuessController {

    @Autowired
    private MNoticeService noticeService;
    @Resource
    private TopicService topicService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private FavoriteService favoriteService;

    //通过map查询
    public List<MicroNotice> queryMGuess(Map<String, Object> queryMap) {
        return noticeService.queryMNotice(queryMap);
    }

    //添加微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addMGuess", method = RequestMethod.POST)
    public int insertMGuess(HttpServletRequest request) {
        MicroGuess guess = new MicroGuess();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");

        guess.setType(GUESS.getIndex());
//        mEvid.setAuthorID((Integer) session.getAttribute("authorId"));
        guess.setAuthorID(userInfo.getId());
        guess.setAuthorName(userInfo.getName());
        String topic = request.getParameter("topic");
        guess.setTopic("-"+topic+"-");
        guess.setReference(request.getParameter("reference"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());

        noticeService.insertMNotice(guess);
        return 0;
    }

    //修改微猜想
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/modifyMGuess", method = RequestMethod.POST)
    public int updateMGuess(HttpServletRequest request, HttpSession session) {
        MicroGuess guess = new MicroGuess();
        int noticeId = Integer.parseInt(request.getParameter("id"));
        guess.setId(noticeId);

        String topic = request.getParameter("topic");
        guess.setTopic("-"+topic+"-");
        guess.setReference(request.getParameter("reference"));
        guess.setKeywords(request.getParameter("keywords"));
        guess.setTitle(request.getParameter("title"));
        guess.setSummary(request.getParameter("summary"));
        guess.setTime(new Date());

        noticeService.updateMNotice(guess);
        reviewService.delReviewsByNotice(noticeId);
        return 0;
    }

    //删除微猜想
    public boolean deleteMGuess(int id) {
        return noticeService.deleteMNotice(id);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getMEvid", method = RequestMethod.GET)
    private List<MicroNotice> getMEvidList(HttpServletRequest request) {
//        Map<String, Object> queryMap = new HashMap();
//        queryMap.put("type",EVIDENCE.getIndex());
//        queryMap.put("judge",1);
//        List<MicroNotice> allValidEvidList = noticeService.queryMNotice(queryMap);
        List<MicroNotice> retList = new LinkedList<>();
        Map<String, Object> fMap = new HashMap();
        int userId = Integer.parseInt(request.getParameter("userId"));
        fMap.put("userID",userId);
        List<Favorite> favoriteList = favoriteService.selectFavorite(fMap);
        for(Favorite f : favoriteList){
            MicroNotice m = noticeService.getMNoticeById(f.getNoticeID());
            retList.add(m);
        }
        return retList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/toModifyMGuess", method = RequestMethod.POST)
    private Map<String, Object> toModifyMGuess(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("id", id);
        List<MicroNotice> list = noticeService.queryMNotice(queryMap);
        MicroNotice guess = list.get(0);

        Map<String, Object> retMap = new HashMap();
        retMap.put("reference", guess.getReference());
        retMap.put("keywords", guess.getKeywords());
        retMap.put("title", guess.getTitle());
        retMap.put("summary", guess.getSummary());

//        String topicStr = guess.getTopic();
//        String[] topicList = topicStr.split("-");
//        String topicNameStr = "";
//        for(String t : topicList){
//            if(t.isEmpty()){
//                continue;
//            }
//            Topic topic = topicService.getTopicById(Integer.parseInt(t));
//            topicNameStr = topicNameStr.concat(topic.getTopicName());
//            topicNameStr = topicNameStr.concat("-");
//        }
//        retMap.put("topic",topicNameStr.substring(0,topicNameStr.length()-1));
        retMap.put("topic",guess.getTopic());

        return retMap;
    }


}
