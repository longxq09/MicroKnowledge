package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Review {
    private int userId;
    private int noticeId;
    private int type;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
