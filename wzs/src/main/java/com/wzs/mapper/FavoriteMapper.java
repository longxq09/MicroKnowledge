package com.wzs.mapper;

import com.wzs.bean.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface FavoriteMapper {

    List<Favorite> selectFavorite(Map<String, Object> map);

    boolean insertFavorite(Favorite favorite);

    boolean deleteFavorite(Favorite favorite);

    @Select("SELECT COUNT(*) from favorite_info where noticeID = #{noticeId}")
    int getFavorNumByNoticeId(int noticeId);
}
