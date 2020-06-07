<template>
  <div style="
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .24), 0 0 6px rgba(0, 0, 0, .10);
    padding-top: 7px;
    padding-bottom: 7px;
    margin-bottom: 10px;
    overflow:hidden;
    width: 99%;
    padding-right: 5px;
    background-color: #f0f8ff;">
    <div style="width: 100% ;overflow:hidden;margin-top: 5px;">
      <div class="comment_photo">
        <el-avatar>user</el-avatar>
      </div>
      <div class="comment_text">
        <nobr style="color: grey;">
          来自用户 <font color="#000000">{{fromName}}</font> 的反馈
        </nobr>
        <el-button @click="reply" class="reply_button">回复</el-button>
        <el-button @click="reply_one" class="reply_button">一键回复</el-button>
        <el-button type="success" icon="el-icon-check" circle v-if="flag==0"></el-button>
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
        <el-button type="primary" style="width: 9%; height: 51px;" @click="submit_reply">回复</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Comment",
    props: {
      fromId: {
        type: Number,
        default: 0
      },
      flag: {
        type: Number,
        default: 1
      },
      fromName: {
        type: String,
        default: '冯如杯队员'
      },
      content: {
        type: String,
        default: '冯如杯要写不完啦！\n冯如杯要写不完啦！\n冯如杯要写不完啦！\n重要的事情说三遍！'
      },
      time: {
        type: String,
        default: '5/2 23:59'
      },
      id: {
        type: Number,
        default: 0
      },
    },

    data() {
      return {
        reply_text: '',
        if_reply: false,
      }
    },
    methods: {
      reply() {
        this.if_reply = !this.if_reply;
      },
      reply_one(){

          var params = new URLSearchParams();
          params.append('fromUserId', this.fromId);
          params.append('detail', "收到了您的反馈~感谢！");
          params.append('id', this.id);

          this.axios.post('/message/adminReply', params)
            .then((res) => {
              // var remindType = res.data.code == 0 ? 'success' : 'info';
              var remindTitle = res.data === 0 ? '回复成功' : '回复失败';
              var remindContent = res.data === 0 ? '回复成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
              console.log("------------" + res.data);

                      this.$message({
                        message: '一键回复成功',
                        type: 'success'
                      });
            })

            .catch((error) => {
              console.log(error);
            });
      },
      submit_reply() {
        if (this.reply_text.length !== 0) {
          var params = new URLSearchParams();
          params.append('fromUserId', this.fromId);
          params.append('detail', this.reply_text);
          params.append('id', this.id);

          this.axios.post('/message/adminReply', params)
            .then((res) => {
              // var remindType = res.data.code == 0 ? 'success' : 'info';
              var remindTitle = res.data === 0 ? '回复成功' : '回复失败';
              var remindContent = res.data === 0 ? '回复成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
              console.log("------------" + res.data);
                      this.$message({
                        message: '回复成功',
                        type: 'success'
                      });
              this.reply_text = "";
              this.if_reply = !this.if_reply;
            })

            .catch((error) => {
              console.log(error);
            });
        }
      }
    },
    mounted() {
    },
    watch: {
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
