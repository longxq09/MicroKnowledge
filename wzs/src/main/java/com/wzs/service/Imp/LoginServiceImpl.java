package com.wzs.service.Imp;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;
import com.wzs.mapper.AccountMap;
import com.wzs.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AccountMap accountMap;

    public boolean hasMatchUserById(int userId, String password) {
        return accountMap.getIdMatchCount(userId, password) > 0;
    }

    public boolean hasMatchUserByEmail(String email, String password) {
        return accountMap.getEmailMatchCount(email, password) > 0;
    }

    public Account findAccountById(int userId) {
        return accountMap.findAccountById(userId);
    }

    public Account findAccountByEmail(String email) {
        return accountMap.findAccountByEmail(email);
    }

    public boolean reUserPassword(int readerId, String newPassword) {
        return accountMap.resetPassword(readerId, newPassword) > 0;
    }

    public String getUserPassword(int userId) {
        return accountMap.getPassword(userId);
    }

    public boolean addAccount(UserInfo userInfo, String password) {
        return accountMap.addAccount(userInfo.getEmail(), password) > 0;
    }
}

