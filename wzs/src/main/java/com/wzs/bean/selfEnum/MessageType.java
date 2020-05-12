package com.wzs.bean.selfEnum;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/8 14:55
 */
public enum MessageType {
    FOLLOW(1),      //用户被关注      xxx 关注了您
    LIKE(2),        //微公告被点赞    xxx 点赞了您的 xxx(title)微公告
    FAVORITE(3),    //微公告被收藏    xxx 收藏了您的 xxx(title)微公告
    COMMENT(4),     //微公告被评论    xxx 评论了您的 xxx(title)微公告
    REPLY(5),       //评论被回复      xxx 回复了您
    PASS(6),        //微公告通过评审   您的xxx(title)微公告通过评审
    FAIL(7),        //微公告评审失败   您的xxx(title)微公告评审失败
    ADMIN(8);   //管理员公告，全局通知

    private int index;
    MessageType(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }

}
