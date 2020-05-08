package com.wzs.mapper;

import com.wzs.bean.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 16:40
 */
@Mapper
public interface MessageMapper {
    boolean insertMessage(Message message);
    List<Message> selectMessageByUser(int userId);
    boolean setFlagById(int id);
    boolean setFlagByUser(int userId);
}
