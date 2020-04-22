package com.wzs.service;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;


public interface LoginService {
    boolean hasMatchUserById(long userId, String password);

    boolean hasMatchUserByEmail(String email, String password);

    Account findAccountById(long userId);

    Account findAccountByEmail(String email);

    boolean reUserPassword(long readerId, String newPassword);

    String getUserPassword(long userId);

    boolean addAccount(UserInfo userInfo, String password);
}
