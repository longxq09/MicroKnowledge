package com.wzs.service.Imp;

import com.wzs.bean.Follow;
import com.wzs.service.FollowService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FollowServiceTest {
    @Resource
    private FollowService followService;

    @Test
    void insertFollow() {
        Follow follow = new Follow();
        follow.setFollowerID(3);
        follow.setFollowingID(2);
        followService.insertFollow(follow);

    }

    @Test
    void queryFollow() {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("followerID",3);
        List<Follow> followList=followService.selectFollow(queryMap);
        for(Follow i:followList){
            System.out.println(i.getFollowerID()+"关注"+i.getFollowingID());
        }
    }


    @Test
    void deleteFollow() {
        Follow follow = new Follow();
        follow.setFollowerID(3);
        follow.setFollowingID(2);
        followService.deleteFollow(follow);
    }
}
