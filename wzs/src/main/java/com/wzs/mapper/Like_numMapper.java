package com.wzs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Like_numMapper {

    @Select("select `num` from like_num where `noticeId` = #{noticeId}")
    int getLikeNum(int noticeId);

    @Update("update `like_num` set `num` = `num` + 1 where `noticeId` = #{noticeId}")
    boolean addLikeNum(int noticeId);

    @Update("update `like_num` set `num` = `num` - 1 where `noticeId` = #{noticeId}")
    boolean subLikeNum(int noticeId);
}
