package com.wzs.service;

import com.wzs.bean.Message;

import java.util.List;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 17:09
 */
public interface MessageService {
    boolean addMessage(Message message);
    List<Message> selectMessageByUser(int userId);
    boolean setFlagById(int id);
    boolean setFlagByUser(int userId);
}
