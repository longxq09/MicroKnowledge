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
        return topicService.queryTopic(queryMap);
    }

    public int insertTopic(Topic Topic) {
        topicService.insertTopic(Topic);
        return 0;
    }

    public int updateTopic(Topic Topic) {
        topicService.updateTopic(Topic);
        return 0;
    }

    public boolean deleteTopic(int id) {
        return topicService.deleteTopic(id);
    }
}
