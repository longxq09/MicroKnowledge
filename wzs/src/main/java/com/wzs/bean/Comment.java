package com.wzs.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/1 14:52
 */
public class Comment {
    private int id;
    private int noticeId;
    private int authorId;

    private int fromId;
    private String fromName;
    private int toId;
    private String toName;

    private String content;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date time = null;
    private String disTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDisTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int startY = calendar.get(Calendar.YEAR);
        int startM = calendar.get(Calendar.MONTH);
        int startW = calendar.get(Calendar.WEEK_OF_YEAR);
        int startD = calendar.get(Calendar.DATE);
        int startH = calendar.get(Calendar.HOUR);
        int startMin = calendar.get(Calendar.MINUTE);
        calendar.setTime(new Date());
        int nowY = calendar.get(Calendar.YEAR);
        int nowM = calendar.get(Calendar.MONTH);
        int nowW = calendar.get(Calendar.WEEK_OF_YEAR);
        int nowD = calendar.get(Calendar.DATE);
        int nowH = calendar.get(Calendar.HOUR);
        int nowMin = calendar.get(Calendar.MINUTE);
        if(startY == nowY){
            if(startM == nowM){
//                if(startW == nowW){
                    if(startD == nowD){
                        if(startH == nowH){
                            if(startMin == nowMin){
                                return "1分钟前";
                            } else {
                                int dis = nowMin - startMin;
                                return dis+"分钟前";
                            }
                        } else {
                            int dis = nowH - startH;
                            return dis+"小时前";
                        }
                    } else {
                        int dis = nowD - startD;
                        return dis+"天前";
                    }
//                } else {
//                    int dis = nowW - startW;
//                    return dis+"周前";
//                }
            } else {
                int dis = nowM - startM;
                return dis+"月前";
            }
        } else {
            int dis = nowY - startY;
            return dis+"年前";
        }
    }

    public void setDisTime(String disTime) {
        this.disTime = disTime;
    }
}
