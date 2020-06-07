package com.wzs.service.Imp;

import com.wzs.bean.Topic;
import com.wzs.mapper.TopicMapper;
import com.wzs.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 15:38
 */
@Service
public class TopicServiceImp implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public int getTopicCount() {
        return topicMapper.getTopicCount();
    }

    @Override
    public List<Topic>getALLValidTopic(){
        return topicMapper.getALLValidTopic();
    }

    @Override
    public Topic getTopicById(int id) {
        return topicMapper.getTopicById(id);
    }

    @Override
    public List<Topic> queryTopic(Map<String, Object> map) {
        return topicMapper.selectTopic(map);
    }

    @Override
    public boolean insertTopic(Topic topic) {
        return topicMapper.insertTopic(topic);
    }

    @Override
    public int updateTopic(Topic topic) {
        return topicMapper.updateTopic(topic);
    }

    @Override
    public boolean deleteTopic(int id) {
        return topicMapper.deleteTopicById(id);
    }

    @Override
    public boolean actTopic(int id) {
        return topicMapper.actTopicById(id);
    }
}
