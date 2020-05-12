package com.wzs.mapper;

import com.wzs.bean.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Insert("insert into review (`userId`,`noticeId`,`type`) values (#{userId},#{noticeId},#{type})")
    boolean addReview(int userId, int noticeId, int type);

    @Select("select * from review where `userId` = #{userId} and `noticeId` = #{noticeId} ")
    Review getUserReviewToNotice(int userId, int noticeId);

    @Select("select * from review where `noticeId` = #{noticeId}")
    List<Review> getReviewsByNotice(int noticeId);

    @Update("update review set `type` = #{type} where `userId` = #{userId} and `noticeId` = #{noticeId}")
    List<Review> updateReview(int userId,int noticeId,int type);

    @Delete("delete from review where `userId` = #{userId} and `noticeId` = #{noticeId}")
    boolean delUserReview(int userId,int noticeId);

    @Delete("delete from review where `noticeId` = #{noticeId}")
    boolean delReviewsByNotice(int noticeId);

}
