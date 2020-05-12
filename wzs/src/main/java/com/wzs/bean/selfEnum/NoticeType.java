package com.wzs.bean.selfEnum;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 20:49
 */
public enum NoticeType {
    EVIDENCE(1),
    GUESS(2);

    private int index;

    NoticeType(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

}
