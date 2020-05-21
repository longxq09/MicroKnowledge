package com.wzs.service.Imp;

import com.wzs.bean.UserRating;
import com.wzs.mapper.UserRatingMapper;
import com.wzs.service.UserRatingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/20 23:41
 */
@Service
public class UserRatingServiceImp implements UserRatingService {

    @Resource
    private UserRatingMapper userRatingMapper;

    @Override
    public List<UserRating> queryUserRating(Map<String, Object> map) {
        return userRatingMapper.selectUserRating(map);
    }

    @Override
    public boolean insertUserRating(UserRating userRating) {
        return userRatingMapper.insertUserRating(userRating);
    }

    @Override
    public boolean checkByKey(int userId, int noticeId) {
        return userRatingMapper.checkByKey(userId,noticeId)!=null;
    }

    @Override
    public boolean updateScoreByKey(int userId, int noticeId, int score) {
        return userRatingMapper.updateScore(userId, noticeId, score);
    }

    @Override
    public boolean deleteUserRatingByNoticeId(int noticeId) {
        return userRatingMapper.deleteUserRatingByNoticeId(noticeId);
    }
}
