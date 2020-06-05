package com.wzs.mapper;

import com.wzs.bean.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeMapper {

    @Select("select `id` from `like` where `noticeId` = #{noticeId} and `id` = #{id}")
    Integer isContain(int noticeId, int id);

    @Insert("insert into `like`(noticeId, id) values (#{noticeId}, #{id})")
    boolean addLike(int noticeId, int id);

    @Delete("delete from `like` where noticeId = #{noticeId} and id = #{id}")
    boolean delLike(int noticeId, int id);

    @Delete("delete from `like` where noticeId = #{noticeId}")
    boolean delLikeByNoticeId(int noticeId);

    @Select("SELECT COUNT(*) from `like` where  noticeId = #{noticeId}")
    int getLikeNumByNoticeId(int noticeId);

    @Select("select * from `like` where `noticeId` = #{noticeId} and `id` = #{id}")
    Like checkLike(int noticeId, int id);

    @Select("select noticeId from `like` where `id` = #{userId}")
    List<Integer> getLikeNoticeIdByUserId(int userId);

}
