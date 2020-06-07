package com.wzs.service;

import com.wzs.bean.Topic;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 15:38
 */
public interface TopicService {

    int getTopicCount();

    List<Topic> getALLValidTopic();

    Topic getTopicById(int id);

    List<Topic> queryTopic(Map<String, Object> map);

    boolean insertTopic(Topic topic);

    int updateTopic(Topic topic);

    boolean deleteTopic(int id);

    boolean actTopic(int id);
}
