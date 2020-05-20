package com.wzs.service;

import com.wzs.bean.Account;


public interface LoginService {
    boolean hasMatchUserById(int userId, String password);

    boolean hasMatchUserByEmail(String email, String password);

    Account findAccountById(int userId);

    Account findAccountByEmail(String email);

    boolean reUserPassword(int id, String newPassword);

    String getUserPassword(int userId);

    boolean addAccount(Account account);

    Account findAccountByActiveCode(String activeCode);

    boolean updateAccount(Account account);

    boolean forgetPassword(Account account);


}
