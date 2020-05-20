package com.wzs.service;

import com.wzs.bean.Comment;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/1 15:21
 */
public interface CommentService {
    List<Comment> queryComment(Map<String, Object> map);
    boolean insertComment(Comment comment);
    List<Comment> selectCommentOfANotice(int noticeId);
    int getCommentNumByNoticeId(int noticeId);
    int getCommentNumByfromIdAndNoticeId(int fromId, int noticeId);
}
