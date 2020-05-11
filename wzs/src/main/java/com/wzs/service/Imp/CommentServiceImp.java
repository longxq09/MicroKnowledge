package com.wzs.service.Imp;

import com.wzs.bean.Comment;
import com.wzs.mapper.CommentMapper;
import com.wzs.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/1 15:21
 */
@Service
public class CommentServiceImp implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> queryComment(Map<String, Object> map) {
        return commentMapper.selectComment(map);
    }

    @Override
    public boolean insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> selectCommentOfANotice(int noticeId) {
        return commentMapper.selectCommentByNoticeId(noticeId);
    }
}
