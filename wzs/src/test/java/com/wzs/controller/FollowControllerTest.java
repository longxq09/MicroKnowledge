package com.wzs.controller;

import com.wzs.bean.Follow;
import com.wzs.bean.UserInfo;
import com.wzs.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FollowControllerTest {
    @Autowired
    private FollowController followController;
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void getFollowingTest(){
        List<Follow> followList = followController.getFollowing(1);
        for (Follow i : followList) {
            UserInfo userInfo = userInfoService.getUserInfo(i.getFollowingID());
            System.out.println(userInfo.getEmail());
        }
    }

    @Test
    public void getFollowerTest(){
        List<Follow> followList = followController.getFollower(1);
        for (Follow i : followList) {
            UserInfo userInfo = userInfoService.getUserInfo(i.getFollowerID());
            System.out.println(userInfo.getEmail());
        }
    }
    @Test
    public void getUserInfoTest(){
        UserInfo userInfo = userInfoService.getUserInfo(10);
        System.out.println(userInfo.getName());
        userInfo.setAddress("aaa");
        userInfoService.editUserInfo(userInfo);
    }
}
