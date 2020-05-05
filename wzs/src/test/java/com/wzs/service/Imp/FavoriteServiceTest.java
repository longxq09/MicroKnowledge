package com.wzs.service.Imp;

import com.wzs.bean.Favorite;
import com.wzs.bean.MicroNotice;
import com.wzs.service.FavoriteService;
import com.wzs.service.MNoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FavoriteServiceTest {
    @Resource
    private FavoriteService favoriteService;
    @Resource
    private MNoticeService mNoticeService;

    @Test
    void getFavorite() {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userID", 1);
        List<Favorite> favoriteList = favoriteService.selectFavorite(queryMap);
        List<MicroNotice> noticeList=mNoticeService.selectMNoticeByFavorite(favoriteList);
        for(MicroNotice i:noticeList){
            System.out.println(i.getTitle());
        }
    }

    @Test
    void insertFavorite() {
        Favorite favorite = new Favorite();
        favorite.setUserID(3);
        favorite.setNoticeID(1);
        favoriteService.insertFavorite(favorite);

    }

    @Test
    void queryFavorite() {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("userID",3);
        List<Favorite> favoriteList=favoriteService.selectFavorite(queryMap);
        for(Favorite i:favoriteList){
            System.out.println(i.getUserID()+","+i.getNoticeID());
        }
    }


    @Test
    void deleteFavorite() {
        Favorite favorite = new Favorite();
        favorite.setUserID(3);
        favorite.setNoticeID(1);
        favoriteService.deleteFavorite(favorite);
    }
}
