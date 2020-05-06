package com.wzs.mapper;

import com.wzs.bean.Comment;
import com.wzs.bean.Topic;
import org.apache.ibatis.annotations.Mapper;

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
}
