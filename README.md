# MicroKnowledge
SE work

`前后端已经串联功能`：

- 用户登录
- 用户注册
- 个人信息
- 搜索



`暂时操作步骤`：

- idea打开wzs，添加wzs和mk-fe两个module
- 导入wzs.sql至mysql
- 根据本地设置修改DemoApplication.yml中MySQL的设置
- edit configuration, DemoApplication 和 npm run dev同时运行即可



`微公告部分定义说明`

数据库字段

- id为自增主键，唯一确定微公告
- type是微公告种类，0为数据库默认值，1为微证据，2为微猜想
- authorId与authorName为与作者信息对应
- topic，是管理员设置的领域种类
- reference，对于微证据是引用论文，对于微猜想是应用的微证据id串
- keywords是多个关键词由“-”连接
- title和summary是题目与内容
- judge，表示评审状态，……

功能及对应url，（还未加入状态判断）

- 新增微证据 url：/mEvidence/addMEvid
- 新增微猜想 url：/mGuess/addGuess
- 跳转到修改微证据界面 url：/mEvidence/toModifyMEvid
- 跳转到修改猜想据界面 url：/mGuess//toModifyMGuess
- 修改微证据 url：/mEvidence/modifyMEvid
- 修改猜想据 url：/mGuess//modifyMGuess
- (新增微猜想时)获取微证据列表：//mGuess/getMEvid
- 获得所有微公告(用于混合显示)：/mNotice/getNotices
- 删除某条微公告：/mNotice/deleteNotice



`用户信息`：

- url:	/user/info

- 具体字段

```
private int id;			//id为自增主键，唯一确定用户
private String email;	//邮箱
private String name;	//姓名
private String picture;     //头像url
private String sex;			//性别
private String address;		//地址
private String signature;   //个性签名
private String education;   //学历
private String works;        //工作信息，包括affiliation即工作单位
private String expertise;    //专业领域
private String interest;    //偏好领域
private String introduction;            //简介
private String contribution;            //贡献
```



`其他`：

1. 获取所有topic(TopicController)：/topic/getTopicList
2. 搜索(searchController)：/search



收藏(favoriteController)：

- url：	/favorite{

```
/checkFavorite	查看是否收藏
/getFavoriteList	获得所有个人收藏的微知识（微知识按收藏时间排序）
/addFavorite	增加收藏
/deleteFavorite	取消收藏
```

}

- 具体字段

```
int id;		//id为自增主键
int userID;	 //用户id
int noticeID;	 //收藏的微知识id
```



关注(FollowController)：	

- url：	/follow{

```
/getFollowingState 查看关注的人的动态（微知识按创建时间排序）
/checkFollow	查看是否关注
/getFollowing	获得个人关注名单
/getFollower	获得个人粉丝名单
/addFollow		增加关注
/deleteFollow    取消关注
```

}

- 具体字段

```
int id;			//id为自增主键
int followerID;   //粉丝
int followingID;   //关注的人

//followerID关注了followingID
```