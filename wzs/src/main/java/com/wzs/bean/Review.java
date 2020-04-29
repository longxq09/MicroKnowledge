package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Review {
    private int id;
    private int authorId;
    private int type;
    private int passNum;
    private int againstNum;
    private String topic;
    private String citation;
    private String keywords;
    private String title;
    private String summary;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public int getAgainstNum() {
        return againstNum;
    }

    public void setAgainstNum(int againstNum) {
        this.againstNum = againstNum;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
