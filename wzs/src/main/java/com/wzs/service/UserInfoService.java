package com.wzs.service;

import com.wzs.bean.UserInfo;

import java.util.ArrayList;


public interface UserInfoService {

    UserInfo getUserInfo(int Id);

    boolean editUserInfo(UserInfo userInfo);

    long addUserInfo(UserInfo userInfo);

    ArrayList<UserInfo> getAllInfos();
}
