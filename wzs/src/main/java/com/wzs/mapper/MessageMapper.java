package com.wzs.mapper;

import com.wzs.bean.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 16:40
 */
@Mapper
public interface MessageMapper {
    boolean insertMessage(Message message);
    List<Message> selectMessageByUser(int userId);
    List<Message> selectMessage(Map<String,Object> queryMap);
    boolean setFlagById(int id);
    boolean setFlagByUser(int userId);

    @Update("update `message` set `fromUserName` = #{fromUserName} where `fromUserId` = #{fromUserId}")
    boolean updateFromUserName(int fromUserId, String fromUserName);
}
