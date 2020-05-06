package com.wzs.service.Imp;

import com.wzs.bean.Follow;
import com.wzs.bean.MicroNotice;
import com.wzs.service.FollowService;
import com.wzs.service.MNoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
public class FollowServiceTest {
    @Resource
    private FollowService followService;
    @Resource
    private MNoticeService noticeService;


    @Test
    void getFollowingState() {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("followerID",3);
        List<Follow> followList=followService.selectFollow(queryMap);
        ArrayList<Integer> authorList = new ArrayList<>();
        for (Follow i : followList) {
            authorList.add(i.getFollowingID());
        }
        List<MicroNotice> noticeList=noticeService.selectMNoticeByAuthorList(authorList);
        for(MicroNotice i:noticeList){
            System.out.println(i.getTitle());
        }
    }

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
