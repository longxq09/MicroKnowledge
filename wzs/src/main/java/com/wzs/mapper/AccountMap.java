package com.wzs.mapper;

import com.wzs.bean.Account;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountMap {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.wzs.mapper.AccountMap.";

    public int getIdMatchCount(final long id, final String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatchCount", map);
    }

    public int getIdMatch(final long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatch", map);
    }

    public Account findAccountById(final long id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findAccountById", id);
    }

    public int resetPassword(final long id, final String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", newPassword);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", map);
    }

    public String getPassword(final long id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", id);
    }



}
