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
        System.out.println("noticeId: " + noticeId);
        int num = like_numMapper.getLikeNum(noticeId);
        res.put("like_num",num);
        if (likeMapper.isContain(noticeId,id) != null)  {
            res.put("isLike",1);
        } else {
            res.put("isLike",0);
        }
        res.put("code",0);
        return res;
    }

    // TODO 先add再get，事务原子性怎么办
    @Override
    public int addLike(int noticeId, int id) {
        if (likeMapper.isContain(noticeId,id) == null)  {
            likeMapper.addLike(noticeId,id);
            like_numMapper.addLikeNum(noticeId);
        }
        return 0;
    }

    @Override
    public int delLike(int noticeId, int id) {
        if (likeMapper.isContain(noticeId,id) != null)  {
            likeMapper.delLike(noticeId,id);
            like_numMapper.subLikeNum(noticeId);
        }
        return 0;
    }

    @Override
    public int getLikeNumByNoticeId(int noticeId) {
        return likeMapper.getLikeNumByNoticeId(noticeId);
    }

}
