package com.wzs.mapper;

import com.wzs.bean.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/21 15:39
 */
@Mapper
public interface TopicMapper {

    List<Topic> getALLValidTopic();

    @Select("select count(*) from topic")
    int getTopicCount();

    @Select("select * from topic where `id` = #{id}")
    Topic getTopicById(int id);

    List<Topic> selectTopic(Map<String, Object> map);

    boolean insertTopic(Topic topic);

    int updateTopic(Topic topic);

    boolean deleteTopicById(int id);

    boolean actTopicById(int id);
}
