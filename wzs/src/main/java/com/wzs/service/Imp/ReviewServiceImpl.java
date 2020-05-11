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
    public boolean addPassNum(int id, int type) {
        return false;
    }

    @Override
    public boolean subPassNum(int id, int type) {
        return false;
    }

    @Override
    public boolean addAgainstNum(int id, int type) {
        return false;
    }

    @Override
    public boolean subAgainstNum(int id, int type) {
        return false;
    }

    @Override
    public boolean delReviewingState(int id, int type) {
        return false;
    }

    @Override
    public boolean insertNewMicroPubs(Review review) {
        return false;
    }

    @Override
    public List<Review> getReviewingMicroPubs() {
        return null;
    }
}
