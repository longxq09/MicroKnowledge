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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private MNoticeService mNoticeService;

    //查看是否收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkFavorite", method = RequestMethod.GET)
    public int checkFavorite(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");

        int userID = (int) account.getId();
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

    public List<Favorite> getFavorite(int id) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userID", id);
        List<Favorite> favoriteList = favoriteService.selectFavorite(queryMap);
        return favoriteList;
    }

    //获得所有个人收藏的微知识
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getFavoriteList", method = RequestMethod.GET)
    public List<MicroNotice> getFavoriteList(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        List<Favorite> favoriteList = getFavorite((int) account.getId());
        List<MicroNotice> noticeList=mNoticeService.selectMNoticeByFavorite(favoriteList);
        return noticeList;
    }

    //增加收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public int addFavorite(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Favorite favorite = new Favorite();
        favorite.setUserID((int) account.getId());
        favorite.setNoticeID(Integer.parseInt(request.getParameter("noticeID")));
        favoriteService.insertFavorite(favorite);
        return 0;
    }

    //取消收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.POST)
    public int deleteFavorite(HttpServletRequest request) {
        Favorite favorite = new Favorite();
        Account account = (Account) request.getSession().getAttribute("account");
        favorite.setUserID((int) account.getId());
        favorite.setNoticeID(Integer.parseInt(request.getParameter("noticeID")));
        favoriteService.deleteFavorite(favorite);
        return 0;
    }


}
