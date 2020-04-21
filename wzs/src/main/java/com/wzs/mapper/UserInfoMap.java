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

    public UserInfo findInfoByEmail(final String email) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findInfoByEmail", email);
    }

    public int editUserInfo(final UserInfo userInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editUserInfo", userInfo);
    }

    public final long addUserInfo(final UserInfo userInfo) {
        if (sqlSessionTemplate.insert(NAMESPACE + "addUserInfo", userInfo) > 0) {
            return sqlSessionTemplate.selectOne(NAMESPACE + "getUserId", userInfo);
        } else {
            return -1;
        }
    }

    public ArrayList<UserInfo> getAllInfos() {
        List<UserInfo> readers = sqlSessionTemplate.selectList(NAMESPACE + "getAllInfos");
        return (ArrayList<UserInfo>) readers;
    }
}
