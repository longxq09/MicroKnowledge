package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/27 21:05
 */
public class MicroNotice {
    private int id = 0;
    private int type = 0;
    private int authorID = 0;
    private String authorName = null;
    private String topic = null;
    private String reference = null;
    private String keywords = null;
    private String title = null;
    private String summary = null;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time = null;
    private String showTime = null;
    private int Judge = 0;
    private double hot;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof MicroNotice)) {
            return false;
        } else {
            MicroNotice right = (MicroNotice) obj;
            return this.id == right.getId();
        }
    }

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getShowTime() {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return format.format(time);
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public double getHot() {
        return hot;
    }

    public void setHot(double hot) {
        this.hot = hot;
    }
}
