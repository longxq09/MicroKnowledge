package com.wzs.mapper;

import com.wzs.bean.Admin;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminMapper {


    int getAdminMatch(final String email, final String password);

    Admin findAdminByEmail(final String email);

    int resetAdminPassword(final int id, final String password);

    String getAdminPassword(final int id);


}
