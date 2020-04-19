package com.wzs.mapper;

import com.wzs.bean.UserInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserInfoMap {

    private final static String NAMESPACE = "com.wzs.mapper.UserInfoMap.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;


    public UserInfo findInfoById(final long id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findInfoById", id);
    }

    public int editReaderInfo(final UserInfo userInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editReaderInfo", userInfo);
    }

    public int editReaderCard(final UserInfo userInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editReaderCard", userInfo);
    }

    public final long addReaderInfo(final UserInfo userInfo) {
        if (sqlSessionTemplate.insert(NAMESPACE + "addReaderInfo", userInfo) > 0) {
            return sqlSessionTemplate.selectOne(NAMESPACE + "getReaderId", userInfo);
        } else {
            return -1;
        }
    }

    public int deleteReaderInfo(final long id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderInfo", id);
    }

    public ArrayList<UserInfo> getAllInfos() {
        List<UserInfo> readers = sqlSessionTemplate.selectList(NAMESPACE + "getAllInfos");
        return (ArrayList<UserInfo>) readers;
    }
}
