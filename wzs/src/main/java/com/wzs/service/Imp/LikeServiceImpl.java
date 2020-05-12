package com.wzs.service.Imp;

import com.wzs.mapper.LikeMapper;
import com.wzs.mapper.Like_numMapper;
import com.wzs.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    @Resource
    private Like_numMapper like_numMapper;

    @Override
    public Map<String, Integer> checkLikeState(int noticeId, int id) {
        Map<String, Integer> res = new HashMap<>();
        int num = like_numMapper.getLikeNum(noticeId);
        res.put("like_num",num);
        boolean isLike = (likeMapper.isContain(noticeId,id) > 0);
        res.put("isLike",isLike==true?1:0);
        res.put("code",0);
        return res;
    }

    // TODO 先add再get，事务原子性怎么办
    @Override
    public int addLike(int noticeId, int id) {
        likeMapper.addLike(noticeId,id);
        like_numMapper.addLikeNum(noticeId);
        return 0;
//        return like_numMapper.getLikeNum(noticeId);
    }

    @Override
    public int delLike(int noticeId, int id) {
        likeMapper.delLike(noticeId,id);
        like_numMapper.subLikeNum(noticeId);
        return 0;
//        return like_numMapper.getLikeNum(noticeId);
    }


}
