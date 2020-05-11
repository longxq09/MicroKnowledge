package com.wzs.bean;

import java.io.Serializable;

public class Account implements Serializable {

    private int id;
    private String email;
    private String password;

    private int activeStatus;   // 激活状态 0 未激活 1 已激活

    private String activeCode;  // 激活码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this. id= id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }
}
