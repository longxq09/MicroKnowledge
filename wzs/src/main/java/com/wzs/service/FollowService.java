package com.wzs.service;

import com.wzs.bean.Follow;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface FollowService {


    List<Follow> selectFollow(Map<String, Object> map);

    boolean insertFollow(Follow follow);

    boolean deleteFollow(Follow follow);


}
