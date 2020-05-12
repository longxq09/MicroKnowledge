package com.wzs.service.Imp;

import com.wzs.bean.Message;
import com.wzs.mapper.MessageMapper;
import com.wzs.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 17:10
 */
@Service
public class MessageServiceImp implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    @Override
    public boolean addMessage(Message message) {
        return messageMapper.insertMessage(message);
    }

    @Override
    public List<Message> selectMessage(Map<String, Object> queryMap) {
        return messageMapper.selectMessage(queryMap);
    }

    @Override
    public List<Message> selectMessageByUser(int userId) {
        return messageMapper.selectMessageByUser(userId);
    }

    @Override
    public boolean setFlagById(int id) {
        return messageMapper.setFlagById(id);
    }

    @Override
    public boolean setFlagByUser(int userId) {
        return messageMapper.setFlagByUser(userId);
    }

}
