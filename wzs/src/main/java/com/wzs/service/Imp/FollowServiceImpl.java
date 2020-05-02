package com.wzs.service.Imp;

import com.wzs.bean.Follow;
import com.wzs.mapper.FollowMapper;
import com.wzs.service.FollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {


    @Resource
    private FollowMapper followMapper;


    @Override
    public List<Follow> selectFollow(Map<String, Object> map){
        return followMapper.selectFollow(map);
    }

    @Override
    public boolean insertFollow(Follow follow){
        return followMapper.insertFollow(follow);
    }

    @Override
    public boolean deleteFollow(Follow follow){
        return followMapper.deleteFollow(follow);
    }
}
