<template>
  <div style="border-bottom: 1px solid lightgrey;overflow:hidden;">
    <div style="width: 100% ;overflow:hidden;margin-top: 5px;">
      <div class="comment_photo">
        <el-avatar>user</el-avatar>
      </div>
      <div class="comment_text">
        <nobr style="color: grey;" v-if="is_return">
          {{fromName}} 回复 {{toName}}
        </nobr>
        <nobr style="color: grey;" v-else="is_return">
          {{fromName}}
        </nobr>
        <el-button @click="reply" class="reply_button">回复</el-button>
        <nobr style="float: right;color: grey;">
          {{time}}
        </nobr>
        <p class="comment_content">{{content}}</p>
      </div>
    </div>

    <div style="width: 95% ;overflow:hidden;margin-bottom: 10px;float: right; " v-if="if_reply">
      <div class="comment_photo">
        <el-avatar>user</el-avatar>
      </div>
      <div class="comment_text">
        <el-input type="textarea" rows="2" style="width: 90%;" v-model="reply_text"></el-input>
        <el-button type="primary" style="width: 9%; height: 51px;" @click="submit_reply">发表<br>评论</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Comment",
    props: {
      noticeId: {
        type: Number,
        default: 0
      },
      authorId: {
        type: Number,
        default: 0
      },
      fromId: {
        type: Number,
        default: 0
      },
      fromName: {
        type: String,
        default: '冯如杯队员'
      },
      toId: {
        type: Number,
        default: 0
      },
      toName: {
        type: String,
        default: '冯如杯队长'
      },
      content: {
        type: String,
        default: '冯如杯要写不完啦！\n冯如杯要写不完啦！\n冯如杯要写不完啦！\n重要的事情说三遍！'
      },
      time: {
        type: String,
        default: '5/2 23:59'
      },
    },

    data() {
      return {
        reply_text: '',
        if_reply: false,
        is_return: false,
        reply_watch: true,
      }
    },
    methods: {
      reply() {
        this.if_reply = !this.if_reply;
      },
      submit_reply() {
        if (this.reply_text.length !== 0) {
          console.log("reply_success");
          var params = new URLSearchParams();
          params.append('fromId', 1);
          params.append('toId', this.fromId);
          params.append('toName', this.fromName);
          params.append('content', this.reply_text);
          params.append('noticeId', this.noticeId);
          params.append('authorId', this.authorId);

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
              this.reply_watch = !this.reply_watch;
            })

            .catch((error) => {
              console.log(error);
            });
        }
      }
    },
    mounted() {
      this.is_return = (this.toId !== -1);
    },
    watch: {
      "reply_watch": function(newValue, oldValue) {
        console.log("we watch the change!!!!!!!!!!!!!!!");
        this.$emit('childFn', newValue);
      }
    },
  }
</script>

<style>
  .comment_text {
    float: right;
    width: calc(100% - 65px);
    vertical-align: top;
    display: inline-block;
    margin-top: 10px;
  }

  .comment_content {
    width: 100%;
    margin-top: 10px;
    margin-bottom: 15px;
    white-space: pre-wrap;
  }

  .reply_button {
    float: right;
    margin-left: 10px;
    margin-top: -2px;
    line-height: 7px;
    height: 25px;
    background-color: #F4F4F5;
    border: 1px solid #F4F4F5;
  }

  .comment_photo {
    vertical-align: top;
    width: 5%;
    display: inline-block;
    padding: 1%;
  }
</style>
