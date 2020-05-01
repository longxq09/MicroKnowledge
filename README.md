# MicroKnowledge
SE work

`前后端已经串联功能`：

- 用户登录
- 用户注册



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