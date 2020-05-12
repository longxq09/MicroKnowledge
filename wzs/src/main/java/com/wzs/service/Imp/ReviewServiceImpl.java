package com.wzs.service.Imp;

import com.wzs.bean.Review;
import com.wzs.mapper.ReviewMapper;
import com.wzs.service.ReviewService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Resource
    private ReviewMapper reviewMapper;

    @Override
    public boolean addReview(int userId, int noticeId, int type) {
        return reviewMapper.addReview(userId,noticeId,type);
    }

    @Override
    public Review getUserReviewToNotice(int userId, int noticeId) {
        return reviewMapper.getUserReviewToNotice(userId,noticeId);
    }

    @Override
    public List<Review> getReviewsByNotice(int noticeId) {
        return reviewMapper.getReviewsByNotice(noticeId);
    }

    @Override
    public List<Review> updateReview(int userId, int noticeId, int type) {
        return reviewMapper.updateReview(userId,noticeId,type);
    }

    @Override
    public boolean delUserReview(int userId, int noticeId) {
        return reviewMapper.delUserReview(userId,noticeId);
    }

    @Override
    public boolean delReviewsByNotice(int noticeId) {
        return reviewMapper.delReviewsByNotice(noticeId);
    }
}
