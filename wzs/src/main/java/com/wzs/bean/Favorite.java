package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Favorite {
    private int id;
    private int userID;
    private int noticeID;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
