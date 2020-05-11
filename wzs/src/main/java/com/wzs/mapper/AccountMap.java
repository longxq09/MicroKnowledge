package com.wzs.mapper;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMap {

    int getIdMatchCount(final int id, final String password);

    int getEmailMatchCount(final String email, final String password);

    int getAdminMatch(final String email, final String password);


    Account findAccountById(final int id);

    Account findAccountByEmail(final String email);

    int addAccount(Account account);

    int resetPassword(final int id, final String password);

    String getPassword(final int id);

    Account findAccountByActiveCode(String activeCode);

    boolean updateAccount(Account account);

}
