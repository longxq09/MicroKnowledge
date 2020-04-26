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
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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


    @CrossOrigin
    @RequestMapping(value = "/user/topic", method = RequestMethod.GET)
    public @ResponseBody
    Object userAllTopicGet(HttpServletRequest request) {

        JSONObject object = new JSONObject();
        List<Topic> topics = getALLTopic();
        List<Integer> id=new LinkedList<>();
        List<String> content=new LinkedList<>();
        List<Integer> flag=new LinkedList<>();
        for (int i = 0; i < topics.size(); i++) {
            Topic topic=topics.get(i);
            id.add(topic.getId());
            content.add(topic.getTopicName());
            flag.add(topic.getFlag());
        }
        object.put("id", id);
        object.put("content", content);
        object.put("flag", flag);  //标签有效或无效
        object.toJSONString();
        System.out.println(object);
        return object;
    }

    public List<Topic> getALLTopic() {
        return topicService.getALLTopic();      //返回了所有的标签
    }

    public List<Topic>getALLValidTopic(){
        return topicService.getALLValidTopic();        //只返回了flag=1即有效的标签
    }

    public List<Topic> queryTopic(Map<String, Object> queryMap) {
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
