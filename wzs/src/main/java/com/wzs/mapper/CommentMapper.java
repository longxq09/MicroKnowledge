package com.wzs.mapper;

import com.wzs.bean.Comment;
import com.wzs.bean.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/1 15:04
 */
@Mapper
public interface CommentMapper {
    List<Comment> selectComment(Map<String, Object> map);
    boolean insertComment(Comment comment);
    List<Comment> selectCommentByNoticeId(int noticeId);
    @Select("SELECT COUNT(*) from comment where noticeId = #{noticeId}")
    int getCommentNumByNoticeId(int noticeId);

    @Update("update `comment` set `fromName` = #{fromName} where `fromId` = #{fromId}")
    boolean updateFromName(int fromId, String fromName);

    @Update("update `comment` set `toName` = #{toName} where `toId` = #{toId}")
    boolean updateToName(int toId, String toName);
}
