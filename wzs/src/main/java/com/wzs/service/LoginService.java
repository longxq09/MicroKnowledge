package com.wzs.service;
import com.wzs.bean.Account;
import com.wzs.mapper.AccountMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AccountMap accountMap;

    public boolean hasMatchUser(long readerId, String password) {
        return accountMap.getIdMatchCount(readerId, password) > 0;
    }

    public Account findAccountById(long readerId) {
        return accountMap.findAccountById(readerId);
    }

    public boolean reUserPassword(long readerId, String newPassword) {
        return accountMap.resetPassword(readerId, newPassword) > 0;
    }

    public String getUserPassword(long readerId) {
        return accountMap.getPassword(readerId);
    }
}
