package com.wzs.controller;

import com.wzs.bean.Follow;
import com.wzs.bean.Message;
import com.wzs.bean.MicroNotice;
import com.wzs.bean.UserInfo;
import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.FollowService;
import com.wzs.service.MNoticeService;
import com.wzs.service.MessageService;
import com.wzs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private MNoticeService mNoticeService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MessageService messageService;

    //查看关注的人的动态（微知识按时间排序）
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollowingState", method = RequestMethod.GET)
    public List<MicroNotice> getFollowingState(HttpServletRequest request) {
        int followerID = Integer.parseInt(request.getParameter("id"));
        ArrayList<Integer> authorList = new ArrayList<>();
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followerID", followerID);
        List<Follow> followList = followService.selectFollow(queryMap);
        for (Follow i : followList) {
            authorList.add(i.getFollowingID());
        }
        List<MicroNotice> noticeList = mNoticeService.selectMNoticeByAuthorList(authorList);
        return noticeList;
    }

    private int findFollow(int followerID, int followingID) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followerID", followerID);
        queryMap.put("followingID", followingID);
        List<Follow> followList = followService.selectFollow(queryMap);
        if (followList.size() > 0) {
            return 0;
        } else {
            return -1;
        }
    }

    //查看是否关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkFollow", method = RequestMethod.GET)
    public int checkFollow(HttpServletRequest request) {
        int followerID = Integer.parseInt(request.getParameter("id"));
        int followingID = Integer.parseInt(request.getParameter("followingID"));
        return findFollow(followerID, followingID);
    }


    private List<Follow> getFollowing(int id) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followerID", id);
        return followService.selectFollow(queryMap);
    }

    //获得个人关注名单信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollowing", method = RequestMethod.GET)
    public List<UserInfo> getFollowingInfo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Follow> followList = getFollowing(id);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Follow i : followList) {
            UserInfo userInfo = userInfoService.getUserInfo(i.getFollowingID());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    private List<Follow> getFollower(int id) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followingID", id);
        return followService.selectFollow(queryMap);
    }

    //获得个人粉丝名单信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollower", method = RequestMethod.GET)
    public List<UserInfo> getFollowerInfo(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Follow> followList = getFollower(id);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Follow i : followList) {
            UserInfo userInfo = userInfoService.getUserInfo(i.getFollowerID());
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    private void addFollowMessage(UserInfo userInfo, int followingID) {
        Message message = new Message();
        message.setUserId(followingID);
        message.setType(MessageType.FOLLOW.getIndex());
        message.setFromUserId(userInfo.getId());
        message.setFromUserName(userInfo.getName());
        message.setTime(new Date());
        messageService.addMessage(message);
    }

    //增加关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    public int addFollow(HttpServletRequest request) {
        int followerID = Integer.parseInt(request.getParameter("id"));
        int followingID = Integer.parseInt(request.getParameter("followingID"));
        if (findFollow(followerID, followingID) == 0) {     //已经关注
            return -1;
        }
        Follow follow = new Follow();
        follow.setFollowerID(followerID);
        follow.setFollowingID(followingID);
        followService.insertFollow(follow);
        //message
        addFollowMessage((UserInfo) request.getSession().getAttribute("userInfo"), followingID);
        return 0;
    }

    //取消关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteFollow", method = RequestMethod.POST)
    public int deleteFollow(HttpServletRequest request) {
        int followerID = Integer.parseInt(request.getParameter("id"));
        int followingID = Integer.parseInt(request.getParameter("followingID"));
        if (findFollow(followerID, followingID) == -1) {    //并没有关注
            return -1;
        }
        Follow follow = new Follow();
        follow.setFollowerID(followerID);
        follow.setFollowingID(followingID);
        followService.deleteFollow(follow);
        return 0;
    }


}
