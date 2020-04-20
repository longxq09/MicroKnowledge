package com.wzs.service;
import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;
import com.wzs.mapper.AccountMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AccountMap accountMap;

    public boolean hasMatchUserById(long userId, String password) {
        return accountMap.getIdMatchCount(userId, password) > 0;
    }

    public boolean hasMatchUserByEmail(String email, String password) {
        return accountMap.getEmailMatchCount(email, password) > 0;
    }

    public Account findAccountById(long userId) {
        return accountMap.findAccountById(userId);
    }

    public Account findAccountByEmail(String email) {
        return accountMap.findAccountByEmail(email);
    }

    public boolean reUserPassword(long readerId, String newPassword) {
        return accountMap.resetPassword(readerId, newPassword) > 0;
    }

    public String getUserPassword(long userId) {
        return accountMap.getPassword(userId);
    }

    public boolean addAccount(UserInfo userInfo, String password) {
        return accountMap.addAccount(userInfo, password) > 0;
    }
}
