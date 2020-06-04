package com.wzs.service;

import java.util.List;
import java.util.Map;

public interface LikeService {

    Map<String, Integer> checkLikeState(int noticeId, int id);

    int addLike(int noticeId,int id);

    int delLike(int noticeId,int id);

    int getLikeNumByNoticeId(int noticeId);

    boolean checkLike(int noticeId, int id);

    List<Integer> getLikeNoticeIdByUserId(int userId);

}
