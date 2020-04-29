package com.wzs.service;

import com.wzs.bean.Review;

import java.util.List;

public interface ReviewService {

    boolean addPassNum(int id, int type);

    boolean subPassNum(int id, int type);

    boolean addAgainstNum(int id, int type);

    boolean subAgainstNum(int id, int type);

    boolean delReviewingState(int id, int type);

    boolean insertNewMicroPubs(Review review);

    List<Review> getReviewingMicroPubs();

}
