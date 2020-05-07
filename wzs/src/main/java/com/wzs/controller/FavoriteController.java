package com.wzs.controller;

import com.wzs.bean.Account;
import com.wzs.bean.Favorite;

import com.wzs.bean.MicroNotice;
import com.wzs.service.FavoriteService;
import com.wzs.service.MNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private MNoticeService mNoticeService;

    public List<Favorite> getFavorite(int id) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userID", id);
        List<Favorite> favoriteList = favoriteService.selectFavorite(queryMap);
        return favoriteList;
    }

    public List<MicroNotice> getFavoriteNoticeList(List<Favorite> favoriteList) { //返回的notice按创建时间排序，暂未使用
        return mNoticeService.selectMNoticeByFavorite(favoriteList);
    }

    //查看是否收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkFavorite", method = RequestMethod.GET)
    public int checkFavorite(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        int noticeID = Integer.parseInt(request.getParameter("noticeID"));
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userID", userID);
        queryMap.put("noticeID", noticeID);
        List<Favorite> favoriteList = favoriteService.selectFavorite(queryMap);
        if (favoriteList.size() > 0) {
            return 0;
        } else {
            return -1;
        }
    }


    //获得所有个人收藏的微知识
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFavoriteList", method = RequestMethod.GET)
    public List<MicroNotice> getFavoriteList(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Favorite> favoriteList = getFavorite(id);
        List<MicroNotice> noticeList = new ArrayList<>();
        for (Favorite i : favoriteList) {
            Map<String, Object> queryMap = new HashMap();
            queryMap.put("id", i.getNoticeID());
            MicroNotice microNotice = mNoticeService.queryMNotice(queryMap).get(0);
            noticeList.add(microNotice);
        }
        return noticeList;
    }

    //增加收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public int addFavorite(HttpServletRequest request) {
        Favorite favorite = new Favorite();
        favorite.setUserID(Integer.parseInt(request.getParameter("id")));
        favorite.setNoticeID(Integer.parseInt(request.getParameter("noticeID")));
        favorite.setTime(new Date());
        favoriteService.insertFavorite(favorite);
        return 0;
    }

    //取消收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.POST)
    public int deleteFavorite(HttpServletRequest request) {
        Favorite favorite = new Favorite();
        favorite.setUserID(Integer.parseInt(request.getParameter("id")));
        favorite.setNoticeID(Integer.parseInt(request.getParameter("noticeID")));
        favoriteService.deleteFavorite(favorite);
        return 0;
    }


}
