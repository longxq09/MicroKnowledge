package com.wzs.mapper;

import com.wzs.bean.Comment;
import com.wzs.bean.UserRating;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/20 23:30
 */
@Mapper
public interface UserRatingMapper {

    List<UserRating> selectUserRating(Map<String, Object> map);//no use
    boolean insertUserRating(UserRating userRating);
    List<UserRating> getUserRatingByNoticeId(int noticeId); //no use


    @Select("SELECT * from user_rating where `userId` = #{userId} and noticeId = #{noticeId}")
    UserRating checkByKey(int userId, int noticeId);

    @Update("update `user_rating` set `score` = #{score} where `userId` = #{userId} and `noticeId` = #{noticeId}")
    boolean updateScore(int userId, int noticeId, int score);

    @Delete("delete from `user_rating` where noticeId = #{noticeId}")
    boolean deleteUserRatingByNoticeId(int noticeId);
}
