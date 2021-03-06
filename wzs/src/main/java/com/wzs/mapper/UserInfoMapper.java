package com.wzs.mapper;

import com.wzs.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface UserInfoMapper {

    UserInfo findInfoById(final int id);

    UserInfo findInfoByEmail(final String email);

    int getEmailCount(final String email);

    int editUserInfo(final UserInfo userInfo);

    boolean addUserInfo(final UserInfo userInfo);

    ArrayList<UserInfo> getAllInfos();
}
