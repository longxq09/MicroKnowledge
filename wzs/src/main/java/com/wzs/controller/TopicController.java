package com.wzs.controller;

import com.alibaba.fastjson.JSONObject;
import com.wzs.bean.Topic;
import com.wzs.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 15:47
 */
@Controller
public class TopicController {
    @Autowired
    private TopicService topicService;

    public List<Topic> getALLValidTopic() {
        return topicService.getALLValidTopic();        //只返回了flag=1即有效的标签
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/topic/getTopicList", method = RequestMethod.GET)
    public List<Topic> queryTopic(HttpServletRequest request){
        Map<String,Object> queryMap = new HashMap();
        queryMap.put("flag",1);
        return topicService.queryTopic(queryMap);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/topic/addTopic", method = RequestMethod.POST)
    public int addTopic(HttpServletRequest request) {
        Topic topic = new Topic();
        topic.setTopicName(request.getParameter("topicName"));
        topic.setFlag(1);
        topicService.insertTopic(topic);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/topic/updateTopic", method = RequestMethod.POST)
    public int updateTopic(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Topic topic = topicService.getTopicById(id);
        topic.setTopicName(request.getParameter("topicName"));
        topicService.updateTopic(topic);
        return 0;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/topic/deleteTopic", method = RequestMethod.POST)
    public boolean deleteTopic(int id) {
        return topicService.deleteTopic(id);
    }
}
