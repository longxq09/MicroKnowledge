package com.wzs.service;

import com.wzs.bean.Review;

import java.util.List;

public interface ReviewService {

    boolean addReview(int userId, int noticeId, int type);
    Review getUserReviewToNotice(int userId, int noticeId);
    List<Review> getReviewsByNotice(int noticeId);
    List<Review> updateReview(int userId,int noticeId,int type);
    boolean delUserReview(int userId,int noticeId);
    boolean delReviewsByNotice(int noticeId);

}
