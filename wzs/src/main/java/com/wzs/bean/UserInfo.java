package com.wzs.bean;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    private long id;
    private String name;
    private String sex;
    private Date birth;
    private String address;
    private String phone;

    public void setInfo(long id, String name, String sex, Date birth, String address, String phone){
        this.address=address;
        this.birth=birth;
        this.name=name;
        this.phone=phone;
        this.id=id;
        this.sex=sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
