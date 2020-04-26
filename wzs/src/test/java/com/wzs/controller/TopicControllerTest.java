package com.wzs.controller;

import com.wzs.bean.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 16:57
 */
@SpringBootTest
class TopicControllerTest {
    @Autowired
    private TopicController controller;

    @Test
    void insertTopic() {
        Topic topic = new Topic();
        topic.setTopicName("物理");
        controller.insertTopic(topic);
        topic.setTopicName("数学");
        controller.insertTopic(topic);
        topic.setTopicName("计算机科学与技术");
        controller.insertTopic(topic);
    }

    @Test
    void queryTopic() {
        Map<String, Object> map = new HashMap<>();
//        List<Topic> list =  controller.queryTopic(map);
//        for(Topic t : list){
//            System.out.println(t.getTopicName());
//        }
    }

    @Test
    void updateTopic() {
        Topic topic = new Topic();
        topic.setId(1);
        topic.setTopicName("环境科学");
        controller.updateTopic(topic);
    }

    @Test
    void deleteTopic() {
        controller.deleteTopic(2);
    }
}