<template>
  <div class="notice">
    <div class="noice_title">{{type_name}} | {{title}}</div>
    <nobr style="font-weight: 600;margin-left: 10px;">{{authorName}}</nobr>
    <el-tag key="tag" v-for="tag in keywordTag" class="keyword">{{tag}}</el-tag>
    <div class="main_text">{{summary}}</div>
    <el-button class="bottom_tag">评论</el-button>
    <el-button class="bottom_tag">收藏</el-button>
    <el-button class="bottom_tag">点赞</el-button>
    <el-button class="bottom_tag">关注作者</el-button>
    <el-button class="bottom_tag">举报内容</el-button>
    <el-button class="bottom_tag" v-if="user" @click="toModify">编辑</el-button>
    <el-button class="bottom_tag" v-if="user" @click="toDelete">删除</el-button>
  </div>
</template>

<script>
  export default {
    name: "Notice",
    props: {
      id: {
        type: Number,
        default: 6
      },
      type: {
        type: Number,
        default: 1
      },
      authorName: {
        type: String,
        default: 'uc主编'
      },
      keywords: {
        type: String,
        default: 'machine learning-c++从入门到入土'
      },
      title: {
        type: String,
        default: '震惊！冯如杯要写不完了？！'
      },
      summary: {
        type: String,
        default: "冯如杯写不完是怎么回事呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦"
      },
      user: {
        type: Boolean,
        default: false
      },
    },

    data() {
      return {
        keywordTag: [],
        type_name: '',
      }
    },
    methods: {
      toModify() {
        this.$router.push({
          path: '/modify_mevid/',
          query: {
            id: 1
          }
        });
      },

      toDelete() {
        var params = new URLSearchParams();
        params.append('id', this.id);
        this.axios.post('/mNotice/deleteNotice', params)
          .then((res) => {
            var remindTitle = res.data === 0 ? '删除微证据成功' : '删除微证据失败';
            var remindContent = res.data === 0 ? '删除微证据成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            });
            if (res.data === 0) {
              this.$router.push('/homepage');
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    mounted() {
      this.keywordTag = this.keywords.split('-');
      if (this.type == 1) {
        this.type_name = "微证据";
      } else {
        this.type_name = "微猜想";
      }
    },
  }
</script>

<style>
  .notice {
    border: 1px solid #409EFF;
    width: 99%;
    border-radius: 10px;
    margin-bottom: 10px;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 20px;
    font-weight: 500;
    display: inline-block;
    margin: 10px;
    margin-bottom: 5px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .main_text {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    margin: 10px;
    margin-bottom: 1px;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
  }

  .bottom_tag {
    margin: 10px;
    margin-right: 0;
    line-height: 7px;
    height: 25px;
  }
</style>
