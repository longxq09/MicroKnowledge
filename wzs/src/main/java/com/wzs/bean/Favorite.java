package com.wzs.bean;

public class Favorite {
    private int id;
    private int userID;
    private int noticeID;

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
}
