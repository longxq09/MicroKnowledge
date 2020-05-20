package com.wzs.bean;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/20 23:32
 */
public class UserRating {
    private int userId;
    private int noticeId;
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
