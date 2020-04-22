package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/20 12:01
 */
public class MicroEvidence {
    //与数据库对应，基础字段
    private int id;
    private int authorID;
    private String topic;
    private String citedPaper;
    private String keywords;
    private String summary;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time;

    //TODO 辅助
    private String idWithType;
    private int Judge;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCitedPaper() {
        return citedPaper;
    }

    public void setCitedPaper(String citedPaper) {
        this.citedPaper = citedPaper;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIdWithType() {
        return idWithType;
    }

    public void setIdWithType(String idWithType) {
        this.idWithType = idWithType;
    }

    public int getJudge() {
        return Judge;
    }

    public void setJudge(int judge) {
        Judge = judge;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
