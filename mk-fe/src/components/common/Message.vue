<template>
  <div style="border-bottom: 1px solid lightgrey;overflow:hidden;padding-top: 5px;">
    <div class="comment_photo">
      <el-avatar>user</el-avatar>
    </div>
    <div class="comment_text">
      <div style="margin-bottom: 5px;">
        <nobr>
          {{fromName}}
        </nobr>
        <nobr style="float: right;color: grey;">
          {{time}}
        </nobr>
      </div>
      <div v-if="if_has_notice" style="margin-bottom: 5px;">
          {{message_text1}}
          <el-button type="text" @click="dialogTableVisible = true" style="font-size: 15px;">
          【{{relatedNoticeTitle}}】</el-button>{{message_text2}}
      </div>
      <div v-else style="margin-bottom: 5px;">
        {{message_text1}}
      </div>
      <div v-if="if_has_detail" style="background-color:white; width: 98%;;color: #409EFF;border: 1px solid #C6E2FF;padding: 5px;">
        {{detail}}
      </div>
      <p class="comment_content">{{content}}</p>

      <el-dialog title="详情" :visible.sync="dialogTableVisible">
        <v-detail v-bind:noticeId="relatedNoticeId"></v-detail>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import vDetail from './Detail.vue'
  export default {
    name: "Massage",
    props: {
      type: {
        type: Number,
        default: 5
      },
      fromName: {
        type: String,
        default: '不懂学术怪'
      },
      relatedNoticeId: {
        type: Number,
        default: 1
      },
      relatedNoticeTitle: {
        type: String,
        default: '震惊！不懂学术怪居然不懂学术！'
      },
      detail: {
        type: String,
        default: '喵'
      },
      time: {
        type: String,
        default: '5/2'
      },
      flag: {
        type: Boolean,
        default: false
      },
    },
    components: {
      vDetail
    },

    data() {
      return {
        message_text1: '',
        message_text2: '',
        if_has_notice: true,
        if_has_detail: false,
        dialogTableVisible: false,
        dialogFormVisible: false,
      }
    },
    methods: {
      reply() {

      },
    },
    mounted() {
      if (this.type == 1) {
        this.message_text1 = "关注了你";
        this.if_has_notice = false;
      } else if (this.type == 2) {
        this.message_text1 = "点赞了你的微公告";
      } else if (this.type == 3) {
        this.message_text1 = "收藏了你的微公告";
      } else if (this.type == 4) {
        this.message_text1 = "评论了你的微公告";
        this.if_has_detail=true;
      } else if (this.type == 5) {
        this.message_text1 = "在微公告";
        this.message_text2 = "底下回复了你的评论";
        this.if_has_detail=true;
      } else if (this.type == 6) {
        this.message_text1 = "恭喜！你发布的微公告";
        this.message_text2 = "通过了评审";
      } else if (this.type == 7) {
        this.message_text1 = "很抱歉！你发布的微公告未通过评审";
        this.message_text2 = "未通过评审";
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
