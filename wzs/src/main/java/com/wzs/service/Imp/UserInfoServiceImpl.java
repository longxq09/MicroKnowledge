package com.wzs.service.Imp;

import com.wzs.bean.UserInfo;
import com.wzs.mapper.UserInfoMap;
import com.wzs.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMap userInfoMap;

    @Override
    public UserInfo getUserInfo(int Id) {
        return userInfoMap.findInfoById(Id);
    }

    @Override
    public boolean editUserInfo(UserInfo userInfo) {
        return userInfoMap.editUserInfo(userInfo) > 0;
    }

    @Override
    public long addUserInfo(UserInfo userInfo) {
        if (userInfoMap.getEmailCount(userInfo.getEmail()) != 0) {
            return -1;
        }
        userInfoMap.addUserInfo(userInfo);
        return 1;
    }

    @Override
    public ArrayList<UserInfo> getAllInfos() {
        return userInfoMap.getAllInfos();
    }
}
