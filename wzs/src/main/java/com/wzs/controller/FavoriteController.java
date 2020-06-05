package com.wzs.controller;

import com.wzs.bean.*;

import com.wzs.bean.selfEnum.MessageType;
import com.wzs.service.FavoriteService;
import com.wzs.service.MNoticeService;
import com.wzs.service.MessageService;
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
    @Autowired
    private MessageService messageService;


    public List<Favorite> getFavorite(int id) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userID", id);
        List<Favorite> favoriteList = favoriteService.selectFavorite(queryMap);
        return favoriteList;
    }

    public List<MicroNotice> getFavoriteNoticeList(List<Favorite> favoriteList) { //返回的notice按创建时间排序，暂未使用
        return mNoticeService.selectMNoticeByFavorite(favoriteList);
    }

    private int findFavorite(int userID, int noticeID) {
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

    //查看是否收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/checkFavorite", method = RequestMethod.GET)
    public int checkFavorite(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        int noticeID = Integer.parseInt(request.getParameter("noticeID"));
        return findFavorite(userID, noticeID);
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
            List<MicroNotice> tmp = mNoticeService.queryMNotice(queryMap);
            if (tmp.size() != 0) {
                MicroNotice microNotice = tmp.get(0);
                noticeList.add(microNotice);
            }
        }
        return noticeList;
    }

    private void addFavoriteMessage(UserInfo userInfo, int noticeID) {
        Message message = new Message();
        message.setType(MessageType.FAVORITE.getIndex());
        message.setFromUserId(userInfo.getId());
        message.setFromUserName(userInfo.getName());
        message.setRelatedNoticeId(noticeID);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("id", noticeID);
        MicroNotice notice = mNoticeService.queryMNotice(queryMap).get(0);
        message.setRelatedNoticeTitle(notice.getTitle());
        message.setRelatedNoticeType(notice.getType());
        message.setUserId(notice.getAuthorID());
        message.setTime(new Date());
        messageService.addMessage(message);
    }

    //增加收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public int addFavorite(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        int noticeID = Integer.parseInt(request.getParameter("noticeID"));
        UserInfo token = (UserInfo) request.getSession().getAttribute("userInfo");
        if (token == null || token.getId() != userID || findFavorite(userID, noticeID) == 0) {  //用户不对或已经收藏
            return -1;
        }
        Favorite favorite = new Favorite();
        favorite.setUserID(userID);
        favorite.setNoticeID(noticeID);
        favorite.setTime(new Date());
        favoriteService.insertFavorite(favorite);

        //message
        addFavoriteMessage((UserInfo) request.getSession().getAttribute("userInfo"), noticeID);
        return 0;
    }

    //取消收藏
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteFavorite", method = RequestMethod.POST)
    public int deleteFavorite(HttpServletRequest request) {
        int userID = Integer.parseInt(request.getParameter("id"));
        UserInfo token = (UserInfo) request.getSession().getAttribute("userInfo");
        if (token == null || token.getId() != userID) {  //用户不对
            return -1;
        }
        int noticeID = Integer.parseInt(request.getParameter("noticeID"));
        if (findFavorite(userID, noticeID) == -1) { //未收藏
            return -1;
        }
        Favorite favorite = new Favorite();
        favorite.setUserID(userID);
        favorite.setNoticeID(noticeID);

        favoriteService.deleteFavorite(favorite);
        return 0;
    }


}
