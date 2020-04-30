package com.wzs.mapper;

import com.wzs.bean.Account;
import com.wzs.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMap {

    int getIdMatchCount(final long id, final String password);

    int getEmailMatchCount(final String email, final String password);


    int getAdminMatch(final String email);

    int getIdMatch(final long id);

    Account findAccountById(final long id);

    Account findAccountByEmail(final String email);

    int addAccount(final String email, final String password);

    int resetPassword(final long id, final String newPassword);

    String getPassword(final long id);

}
