package com.wzs.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Select("select `id` from like where `noticeId` = #{noticeId} and `id` = #{id}")
    int isContain(int noticeId, int id);

    @Insert("insert into like(noticeId, id) value (#{noticeId}, #{id})")
    boolean addLike(int noticeId, int id);

    @Delete("delete from like where noticeId = #{noticeId} and id = #{id}")
    boolean delLike(int noticeId, int id);

}
