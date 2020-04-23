package com.wzs.controller;

import com.wzs.bean.Topic;
import com.wzs.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
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

    public List<Topic> queryTopic(Map<String,Object> queryMap){
        return topicService.queryTopic(queryMap);
    }

    public int insertTopic(Topic Topic){
        topicService.insertTopic(Topic);
        return 0;
    }

    public int updateTopic(Topic Topic){
        topicService.updateTopic(Topic);
        return 0;
    }

    public boolean deleteTopic(int id){
        return topicService.deleteTopic(id);
    }
}
