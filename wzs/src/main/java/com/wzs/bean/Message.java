package com.wzs.bean;

import com.wzs.bean.selfEnum.MessageType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
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
    private String disTime;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

}
