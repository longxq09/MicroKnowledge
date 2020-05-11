package com.wzs.service;

import com.wzs.bean.UserInfo;

import java.util.ArrayList;


public interface UserInfoService {

    UserInfo getUserInfo(int Id);

    boolean editUserInfo(UserInfo userInfo);

    int addUserInfo(UserInfo userInfo);

    ArrayList<UserInfo> getAllInfos();
}
