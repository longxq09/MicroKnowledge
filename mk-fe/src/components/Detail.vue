<template>
  <el-container>
    <el-header>
      <v-head v-bind:title="head_title" v-bind:homepage=true v-bind:detail=true></v-head>
    </el-header>
    <el-main>
      <div class="noice_title">{{form.type_str}} | {{form.title}}</div>
      <nobr style="font-weight: 600;margin-left: 10px;">{{form.authorName}}</nobr>
      <nobr style="font-weight: 400;font-size: 15px;margin-left: 10px;">{{form.time}}</nobr>
      <el-tag :key="tag" v-for="tag in keyWordList" class="keyword">{{tag}}</el-tag>
      <div style="margin: 10px;margin-top: 30px;margin-bottom: 30px;">{{form.text}}</div>
      <div class="bottom_text">引用
        <nobr :key="reference" v-for="reference in referenceList"> | {{reference}}</nobr>
      </div>
      <div class="bottom_text">分类
        <nobr :key='label' v-for="label in labelList"> | {{label}}</nobr>
      </div>

      <v-like v-bind:accountId="accountId" v-bind:id="id">
      </v-like>

      <v-favorite v-bind:accountId="accountId" v-bind:id="id">
      </v-favorite>

      <v-follow v-bind:accountId="accountId" v-bind:id="id" v-bind:authorId="authorId">
      </v-follow>

      <div class="comment_count">
        {{reply_num}}评论
      </div>
      <div style="border-bottom: 1px solid lightgrey;overflow:hidden;padding-top: 5px;padding-bottom: 20px;">
        <div class="comment_photo">
          <el-avatar>user</el-avatar>
        </div>
        <div class="comment_text">
          <el-input type="textarea" rows="2" style="width: 90%;" v-model="reply_text"></el-input>
          <el-button type="primary" style="width: 9%; height: 51px;" @click="submit_reply">发表<br>评论</el-button>
        </div>
      </div>
      <div v-if="refresh">
        <v-comment :key="value.id" v-for="(value,index) in exhibition" v-bind:noticeId="value.noticeId" v-bind:authorId="value.authorId"
                   v-bind:fromId="value.fromId" v-bind:fromName="value.fromName" v-bind:toId="value.toId" v-bind:toName="value.toName"
                   v-bind:content="value.content" v-bind:time="value.disTime" @childFn="changFlag">
        </v-comment>
      </div>
    </el-main>
    <v-footer></v-footer>
  </el-container>
</template>

<script>
  import vHead from './common/Header.vue';
  import vFooter from './common/Footer.vue';
  import vComment from './common/Comment.vue';
  import vFollow from './common/Follow.vue'
  import vLike from './common/Like.vue'
  import vFavorite from './common/Favorite'
  export default {
    name: "Detail",
    data() {
      return {
        head_title: "微知 MicroKnowledge",
        referenceList: ['c系列丛书', '从入门到如图'],
        keyWordList: ['machine learning', 'python从入门到入土'],
        labelList: ['深度学习', 'hhh'],
        reply_text: '',
        exhibition: Array,
        refresh: true,
        radioValue: true,
        reply_num: 0,
        user: true,
        accountId: 0,
        id:0,
        form: {
          type_str: "微证据",
          title: '震惊！冯如杯要写不完了？！',
          text: '冯如杯写不完是怎么回事呢？冯如杯相信大家都很熟悉，但是冯如杯写不完是怎么回事呢，下面就让小编带大家一起了解吧。冯如杯写不完， 其实就是冯如杯就是憨憨， 大家可能会很惊讶冯如杯怎么会写不完呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦',
          keyWord: '',
          label: '',
          reference: '',
          authorName: 'uc主编',
          authorId: 0,
          type: 1,
        },
      }
    },
    components: {
      vHead,
      vFooter,
      vComment,
      vFollow,
      vLike,
      vFavorite,
    },
    created() {
      console.log("init");
      this.accountId = localStorage.getItem("accountId");
      this.id=this.$route.query.id;
      this.getUserInfo();
    },
    methods: {
      changFlag(a) {
        console.log("we catch the change!!!!!!!!!!!!!!!");
        this.refresh = false;
        var params2 = new URLSearchParams();
        params2.append('noticeId', this.$route.query.id);
        this.axios.post('/comment/getCommentsOfNotice', params2)
          .then((res) => {
            this.exhibition = res.data;
            this.reply_num = this.exhibition.length;
          })
          .catch((error) => {
            console.log(error)
          });
        this.refresh = true;
      },
      async getUserInfo() {
        var params = new URLSearchParams();
        params.append('noticeId', this.$route.query.id);
        try {
          let res = await this.axios.post('/comment/getCommentsOfNotice', params);
          this.exhibition = res.data;
          this.reply_num = this.exhibition.length;
        } catch (err) {
          console.log(err);
        }
        var params2 = new URLSearchParams();
        params2.append('id', this.$route.query.id);
        try {
          let res = await this.axios.post('/mNotice/getNoticeById', params2);
          console.log(res.data);
          this.form.title = res.data.title;
          this.form.text = res.data.summary;
          this.form.keyWord = res.data.keywords;
          this.form.label = res.data.topic;
          this.form.reference = res.data.reference;
          this.form.authorName = res.data.authorName;
          this.form.authorId = res.data.authorId
          this.form.type = res.data.type;
          this.form.time = res.data.time;

          this.referenceList = this.form.reference.split('-');
          this.keyWordList = this.form.keyWord.split('-');
          this.labelList = this.form.label.split('-');
          if (this.form.type == 1) {
            this.form.type_str = "微证据";
          } else {
            this.form.type_str = "微猜想";
          }
        } catch (err) {
          console.log(err);
        }
      },

      submit_reply() {
        if (this.reply_text.length !== 0) {
          console.log("reply_success");
          var params = new URLSearchParams();
          params.append('fromId', 1);
          params.append('toId', -1);
          params.append('toName', '');
          params.append('content', this.reply_text);
          params.append('noticeId', this.$route.query.id);
          params.append('authorId', this.form.authorId);
          //params.append('authorId', 1);

          this.axios.post('/comment/replyComment', params)
            .then((res) => {
              // var remindType = res.data.code == 0 ? 'success' : 'info';
              var remindTitle = res.data === 0 ? '回复成功' : '回复失败';
              var remindContent = res.data === 0 ? '回复成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
              console.log("------------" + res.data);
              this.$alert(remindContent, remindTitle, {
                confirmButtonText: '确定'
              });
              this.reply_text = "";
              this.refresh = false;
              var params2 = new URLSearchParams();
              params2.append('noticeId', this.$route.query.id);
              this.axios.post('/comment/getCommentsOfNotice', params2)
                .then((res) => {
                  this.exhibition = res.data;
                  this.reply_num = this.exhibition.length;
                })
                .catch((error) => {
                  console.log(error)
                })
              this.refresh = true;
            })
            .catch((error) => {
              console.log(error);
            });

        }

      },
    }

  }
</script>

<style>
  .el-header {
    background-color: #FFFFFF;
    line-height: 1.5;
  }

  .el-main {
    background-color: #F4F4F5;
    color: #333;
    padding-left: 15%;
    padding-right: 25%;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 25px;
    font-weight: 500;
    display: inline-block;
    margin: 10px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
    margin-right: 10px;
    border-radius: 30px;
  }

  .bottom_text {
    margin-left: 10px;
    color: slategrey;
    font-size: 15px;
  }

  .review_button {
    width: 100px;
    height: 50px;
    margin: 30px;
  }

  .review_choose {
    width: 100px;
    height: 50px;
    margin: 30px;
    background-color: #ECF5FF;
    color: #409EFF;
    border: 1px solid #C6E2FF;
  }

  .bottom_tag {
    margin: 10px;
    margin-right: 0;
    line-height: 7px;
    height: 25px;
  }

  .comment_count {
    width: 100%;
    border-bottom: 1px solid lightgrey;
    margin-left: 10px;
    padding-top: 15px;
    padding-bottom: 15px;
    font-size: 18px;
  }
</style>
