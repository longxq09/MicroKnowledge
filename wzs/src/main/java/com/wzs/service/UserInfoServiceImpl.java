package com.wzs.service;

import com.wzs.bean.UserInfo;
import com.wzs.mapper.UserInfoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMap userInfoMap;

    @Override
    public UserInfo getUserInfo(long Id) {
        return userInfoMap.findInfoById(Id);
    }

    @Override
    public boolean editUserInfo(UserInfo userInfo) {
        return userInfoMap.editUserInfo(userInfo) > 0;
    }

    @Override
    public long addUserInfo(UserInfo userInfo) {
        if (userInfoMap.findInfoByEmail(userInfo.getEmail()) != null) {
            return -1;
        }
        return userInfoMap.addUserInfo(userInfo);
    }

    @Override
    public ArrayList<UserInfo> getAllInfos() {
        return userInfoMap.getAllInfos();
    }
}
