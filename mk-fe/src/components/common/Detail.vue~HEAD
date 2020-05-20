<template>
  <div>
    <div class="noice_title">{{form.type_str}} | {{form.title}}</div>
    <div>
      <font style="font-weight: 600;margin-left: 10px;">{{form.authorName}}</font>
      <font style="font-weight: 400;font-size: 15px;margin-left: 10px;">{{form.time}}</font>
    </div>
    <el-tag :key="tag" v-for="tag in keyWordList" class="keyword" v-if="has_keyword">{{tag}}</el-tag>
    <div style="margin: 10px;margin-top: 30px;margin-bottom: 30px;width: 100%;white-space:normal;display:block;">{{form.text}}</div>
    <div class="bottom_text" v-if="!guess">引用 : {{form.reference}}
    </div>
    <div v-else class="bottom_text">引用 :
      <el-popover placement="right" width="400" trigger="click"
       :key="value.id"
       v-for="(value,index) in guessReference"
       >
        <v-reference
       v-bind:title="value.title"
       v-bind:authorName="value.authorName"
       v-bind:summary="value.summary"></v-reference>
        <el-button type="text" slot="reference" style="box-shadow: 0 2px 4px rgba(0, 0, 0, .24), 0 0 6px rgba(0, 0, 0, .10);margin-left: 5px;">{{value.title}}</el-button>
      </el-popover>
    </div>
    <div class="bottom_text">分类 : {{form.label}}
    </div>

    <div class="comment_count">
      {{reply_num}}评论
    </div>
    <div v-if="login" style="border-bottom: 1px solid lightgrey;overflow:hidden;padding-top: 5px;padding-bottom: 20px;">
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
  </div>
</template>

<script>
  import vComment from './Comment.vue';
  import vReference from './Reference.vue';
  export default {
    name: "Detail",
    props: {
      noticeId: {
        type: Number,
        default: 0
      },
    },
    data() {
      return {
        login: false,
        head_title: "微知 MicroKnowledge",
        referenceList: [],
        keyWordList: [],
        labelList: [],
        reply_text: '',
        exhibition: Array,
        refresh: true,
        radioValue: true,
        reply_num: 0,
        user: true,
        accountId: 0,
        id: 0,
        guess: false,
        guessReference: Array,
        form: {
          type_str: "微证据",
          title: '震惊！还没加载出来？！',
          text: '没加载出来是怎么回事呢？网页加载相信大家都很熟悉，但是没加载出来是怎么回事呢，下面就让小编带大家一起了解吧。其实加载不需要很久， 大家可能会很惊讶为什么没加载出来呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于没加载出来的事情， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！',
          keyWord: '',
          label: '',
          reference: '',
          authorName: 'uc主编',
          authorId: 0,
          type: 1,
          has_keyword: true,
        },
      }
    },
    components: {
      vComment,
      vReference
    },
    created() {
      this.accountId = localStorage.getItem("accountId")
      this.login = this.accountId != ""
      this.id = Number(this.noticeId)
      this.getUserInfo();
    },
    methods: {
      changFlag(a) {
        this.refresh = false;
        var params2 = new URLSearchParams();
        params2.append('noticeId', this.noticeId);
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
        params.append('noticeId', this.noticeId);
        try {
          let res = await this.axios.post('/comment/getCommentsOfNotice', params);
          this.exhibition = res.data;
          this.reply_num = this.exhibition.length;
        } catch (err) {
          console.log(err);
        }
        var params2 = new URLSearchParams();
        params2.append('id', this.noticeId);
        try {
          let res = await this.axios.post('/mNotice/getNoticeById', params2);
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
          this.form.reference = this.referenceList.join(' | ')
          this.keyWordList = this.form.keyWord.split('-');
          this.labelList = this.form.label.split('-');
          this.form.label = this.labelList.join(' | ');
          if (this.form.type == 1) {
            this.form.type_str = "微证据";
          } else {
            this.form.type_str = "微猜想";
            this.guess = true;

            var params3 = new URLSearchParams()
            params3.append('id', this.noticeId)
            this.axios.post('/mNotice/getReference', params3)
              .then((res) => {
                this.guessReference = res.data;
              })
              .catch((error) => {
                console.log(error)
              })
          }
          if (this.form.keyWord.length == 0) {
            this.has_keyword = false;
          }
        } catch (err) {
          console.log(err);
        }
      },

      submit_reply() {
        if (this.reply_text.length !== 0) {
          var params = new URLSearchParams();
          params.append('fromId', 1);
          params.append('toId', -1);
          params.append('toName', '');
          params.append('content', this.reply_text);
          params.append('noticeId', this.noticeId);
          params.append('authorId', this.form.authorId);
          this.axios.post('/comment/replyComment', params)
            .then((res) => {
              var remindTitle = res.data === 0 ? '回复成功' : '回复失败';
              var remindContent = res.data === 0 ? '回复成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
              this.$alert(remindContent, remindTitle, {
                confirmButtonText: '确定'
              });
              this.reply_text = "";
              this.refresh = false;
              var params2 = new URLSearchParams();
              params2.append('noticeId', this.noticeId);
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
    white-space: normal;
    display: block;
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
