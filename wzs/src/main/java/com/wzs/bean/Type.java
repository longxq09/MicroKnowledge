package com.wzs.bean;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/4/30 20:49
 */
public enum Type {
    EVIDENCE(1),
    GUESS(2);

    private int index;

    Type(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

}
