package com.wzs.bean;

import com.wzs.bean.selfEnum.MessageType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 14:51
 */
public class Message {
    private int id;
    private int userId;
    private int type;

    private int fromUserId;
    private String fromUserName;

    private int relatedNoticeId;
    private int relatedNoticeType;
    private String relatedNoticeTitle;

    private String detail;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time = null;
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public int getRelatedNoticeId() {
        return relatedNoticeId;
    }

    public void setRelatedNoticeId(int relatedNoticeId) {
        this.relatedNoticeId = relatedNoticeId;
    }

    public int getRelatedNoticeType() {
        return relatedNoticeType;
    }

    public void setRelatedNoticeType(int relatedNoticeType) {
        this.relatedNoticeType = relatedNoticeType;
    }

    public String getRelatedNoticeTitle() {
        return relatedNoticeTitle;
    }

    public void setRelatedNoticeTitle(String relatedNoticeTitle) {
        this.relatedNoticeTitle = relatedNoticeTitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
