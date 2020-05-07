package com.wzs.service;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;


public interface LoginService {
    boolean hasMatchUserById(int userId, String password);

    boolean hasMatchUserByEmail(String email, String password);

    Account findAccountById(int userId);

    Account findAccountByEmail(String email);

    boolean reUserPassword(int readerId, String newPassword);

    String getUserPassword(int userId);

    boolean addAccount(UserInfo userInfo, String password);
}
