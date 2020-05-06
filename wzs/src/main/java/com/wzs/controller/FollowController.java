package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.Follow;
import com.wzs.bean.MicroNotice;
import com.wzs.service.FollowService;
import com.wzs.service.MNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private MNoticeService mNoticeService;

    //查看关注的人的动态（微知识按时间排序）
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollowingState", method = RequestMethod.GET)
    public List<MicroNotice> getFollowingState(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        int followerID = account.getId();
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

    //查看是否关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkFollow", method = RequestMethod.GET)
    public int checkFollow(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");

        int followerID = account.getId();
        int followingID = Integer.parseInt(request.getParameter("followingID"));
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

    //获得个人关注名单
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollowing", method = RequestMethod.GET)
    public List<Follow> getFollowing(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followerID", (int) account.getId());
        List<Follow> followList = followService.selectFollow(queryMap);
        return followList;
    }

    //获得个人粉丝名单
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFollower", method = RequestMethod.GET)
    public List<Follow> getFollower(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("followingID", (int) account.getId());
        List<Follow> followList = followService.selectFollow(queryMap);
        return followList;
    }

    //增加关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    public int addFollow(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Follow follow = new Follow();
        follow.setFollowerID(account.getId());
        follow.setFollowingID(Integer.parseInt(request.getParameter("followingID")));
        followService.insertFollow(follow);
        return 0;
    }

    //取消关注
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteFollow", method = RequestMethod.POST)
    public int deleteFollow(HttpServletRequest request) {
        Follow follow = new Follow();
        Account account = (Account) request.getSession().getAttribute("account");
        follow.setFollowerID((int) account.getId());
        follow.setFollowingID(Integer.parseInt(request.getParameter("followingID")));
        followService.deleteFollow(follow);
        return 0;
    }


}
