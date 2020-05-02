package com.wzs.mapper;
import com.wzs.bean.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface FollowMapper {

    List<Follow>  selectFollow(Map<String, Object> map);

    boolean insertFollow(Follow follow);

    boolean deleteFollow(Follow follow);
}
