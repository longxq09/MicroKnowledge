package com.wzs.service;

import com.wzs.bean.UserInfo;
import com.wzs.mapper.UserInfoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserInfoService {
    @Autowired
    private UserInfoMap userInfoMap;

    public UserInfo getUserInfo(long Id) {
        return userInfoMap.findInfoById(Id);
    }

    public boolean editUserInfo(UserInfo userInfo) {
        return userInfoMap.editUserInfo(userInfo) > 0;
    }

    public long addUserInfo(UserInfo userInfo) {
        if(userInfoMap.findInfoById(userInfo.getId())!=null){
            return -1;
        }
        return userInfoMap.addUserInfo(userInfo);
    }


    public ArrayList<UserInfo> getAllInfos() {
        return userInfoMap.getAllInfos();
    }
}
