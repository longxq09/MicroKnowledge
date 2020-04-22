package com.wzs.service;

import com.wzs.bean.Topic;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 15:38
 */
public interface TopicService {
    List<Topic> queryTopic(Map<String, Object> map);
    boolean insertTopic(Topic topic);
    int updateTopic(Topic topic);
    boolean deleteTopic(int id);
}
