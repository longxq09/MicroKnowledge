package com.wzs.mapper;

import com.wzs.bean.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReviewMapper {

    @Update("update table review passNum = passNum + 1 where `id` = #{id} and `type` = #{type}")
    boolean addPassNum(int id, int type);

    @Update("update table review passNum = passNum - 1 where `id` = #{id} and `type` = #{type}")
    boolean subPaasNum(int id, int type);

    @Update("update table review againstNum = againstNum + 1 where `id` = #{id} and `type` = #{type}")
    boolean addAgainstNum(int id, int type);

    @Update("update table review againstNum = againstNum - 1 where `id` = #{id} and `type` = #{type}")
    boolean subAgainstNum(int id, int type);

    @Delete("delete from review where `id` = #{id} and `type` = #{type}")
    boolean delReviewingState(int id, int type);

    @Insert("insert into review (`id`, `authorId`, `type`, `passNum`, `againstNum`, `topic`, `citation`, `keywords`, `summary`, `time`, `title`)" +
            " values (#{id}, #{authorId}, #{type}, #{passNum}, #{againstNum}, #{topic}, #{citation}, #{keywords}, #{summary}, #{time}, #{title})")
    boolean insertNewMicroPubs(Review review);

    List<Review> getReviewingMicroPubs();

}
