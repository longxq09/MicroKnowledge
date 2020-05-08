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
    private MessageType type;

    private String fromUserId;
    private String fromUserName;

    private int relatedNoticeId;
    private int relatedNoticeType;
    private int relatedNoticeTitle;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time = null;

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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
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

    public int getRelatedNoticeTitle() {
        return relatedNoticeTitle;
    }

    public void setRelatedNoticeTitle(int relatedNoticeTitle) {
        this.relatedNoticeTitle = relatedNoticeTitle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
