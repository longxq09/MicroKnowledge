package com.wzs.service.Imp;

import com.wzs.bean.UserInfo;
import com.wzs.mapper.CommentMapper;
import com.wzs.mapper.MNoticeMapper;
import com.wzs.mapper.MessageMapper;
import com.wzs.mapper.UserInfoMapper;
import com.wzs.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private MNoticeMapper mNoticeMapper;

    @Resource
    private CommentMapper commentMapper;

    @Override
    public UserInfo getUserInfo(int Id) {
        return userInfoMapper.findInfoById(Id);
    }

    @Override
    public boolean editUserInfo(UserInfo userInfo) {

        int id = userInfo.getId();
        String userName = userInfo.getName();
        messageMapper.updateFromUserName(id,userName);
        mNoticeMapper.updateAuthorName(id,userName);
        commentMapper.updateFromName(id,userName);
        commentMapper.updateToName(id,userName);

        return userInfoMapper.editUserInfo(userInfo) > 0;
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        if (userInfoMapper.getEmailCount(userInfo.getEmail()) != 0) {
            return -1;
        }
        userInfoMapper.addUserInfo(userInfo);
        return 1;
    }

    @Override
    public ArrayList<UserInfo> getAllInfos() {
        return userInfoMapper.getAllInfos();
    }
}
