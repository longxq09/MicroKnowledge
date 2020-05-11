package com.wzs.service;

import com.wzs.bean.Message;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 17:09
 */
public interface MessageService {
    boolean addMessage(Message message);
    List<Message> selectMessage(Map<String,Object> queryMap);
    List<Message> selectMessageByUser(int userId);
    boolean setFlagById(int id);
    boolean setFlagByUser(int userId);
}
