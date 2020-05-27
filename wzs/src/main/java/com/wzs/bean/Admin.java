package com.wzs.bean;

public class Admin {
    private int admin_id;
    private String password;
    private String email;

    public int getId() {
        return admin_id;
    }

    public void setId(int id) {
        this. admin_id= id;
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

}
