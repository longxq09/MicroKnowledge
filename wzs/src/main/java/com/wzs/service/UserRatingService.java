package com.wzs.service;

import com.wzs.bean.Comment;
import com.wzs.bean.UserRating;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/20 23:41
 */
public interface UserRatingService {
    List<UserRating> queryUserRating(Map<String, Object> map);
    boolean insertUserRating(UserRating userRating);
    boolean checkByKey(int userId, int noticeId);
    boolean updateScoreByKey(int userId, int noticeId, int score);
    boolean deleteUserRatingByNoticeId(int noticeId);
}
