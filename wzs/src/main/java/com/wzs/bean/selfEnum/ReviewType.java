package com.wzs.bean.selfEnum;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/11 21:40
 */
public enum ReviewType {
    YES(1),
    NO(-1);

    private int index;

    ReviewType(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }
}
