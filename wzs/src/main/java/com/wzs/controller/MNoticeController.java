package com.wzs.controller;

import com.wzs.bean.Comment;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.Topic;
import com.wzs.service.MNoticeService;
import com.wzs.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNotices", method = RequestMethod.GET)
    public List<MicroNotice> getNotices() {
        Map<String, Object> queryMap = new HashMap<>();
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        noticeList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
        return noticeList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getSelfNotices", method = RequestMethod.GET)
    public List<MicroNotice> getSelfNotices(HttpServletRequest request) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("authorId",request.getParameter("id"));
        List<MicroNotice> noticeList = noticeService.queryMNotice(queryMap);
        noticeList.sort(Comparator.comparing(MicroNotice::getTime).reversed());
        return noticeList;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
    public int deleteMEvid(int id){
        noticeService.deleteMNotice(id);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getNoticeById", method = RequestMethod.POST)
    private Map<String,Object> toModifyMEvid(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Map<String,Object> queryMap = new HashMap();
        queryMap.put("id",id);
        List<MicroNotice> list = noticeService.queryMNotice(queryMap);
        MicroNotice evid = list.get(0);

        Map<String,Object> retMap = new HashMap();
        retMap.put("noticeId",evid.getId());
        retMap.put("type",evid.getType());
        retMap.put("authorId",evid.getAuthorID());
        retMap.put("authorName",evid.getAuthorName());
        retMap.put("reference",evid.getReference());
        retMap.put("keywords",evid.getKeywords());
        retMap.put("title",evid.getTitle());
        retMap.put("summary",evid.getSummary());
        retMap.put("judge",evid.getJudge());
        retMap.put("time",evid.getTime());

        String topicStr = evid.getTopic();
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

}
