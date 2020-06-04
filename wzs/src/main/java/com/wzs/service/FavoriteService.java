package com.wzs.service;

import com.wzs.bean.Favorite;

import java.util.List;
import java.util.Map;

public interface FavoriteService {

    List<Favorite> selectFavorite(Map<String, Object> map);

    boolean insertFavorite(Favorite favorite);

    boolean deleteFavorite(Favorite favorite);

    int getFavorNumByNoticeId(int noticeId);

    List<Integer> selectFavoriteByUserId(int userId);
}
