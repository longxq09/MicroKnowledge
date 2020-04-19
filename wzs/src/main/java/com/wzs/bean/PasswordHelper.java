package com.wzs.bean;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

    private String algorithmName = "md5";

    private int hashIterations = 10;

    public String encrypt(Long id, String password) {

        String sid = id + "";
        String newPassword = new SimpleHash(algorithmName, password,
                ByteSource.Util.bytes(sid), hashIterations).toHex();
        return newPassword;
    }

}
