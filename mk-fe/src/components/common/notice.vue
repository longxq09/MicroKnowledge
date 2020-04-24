<template>
  <div class="notice">
    <div class="noice_title">{{type}} | {{title}}</div>
    <nobr style="font-weight: 600;margin-left: 10px;">{{author}}</nobr>
    <el-tag key="tag" v-for="tag in keyWordTags" class="keyword">{{tag}}</el-tag>
    <div class="main_text">{{main_text}}</div>
    <el-tag class="bottom_tag">评论</el-tag>
    <el-tag class="bottom_tag">收藏</el-tag>
    <el-tag class="bottom_tag">点赞</el-tag>
    <el-tag class="bottom_tag">关注作者</el-tag>
    <el-tag class="bottom_tag">举报内容</el-tag>
  </div>
</template>

<script>
  export default {
    name: "Notice",
    props: {
      type: {
        type: String,
        default: '微猜想'
      },
      title: {
        type: String,
        default: '震惊！冯如杯要写不完了？！'
      },
      author: {
        type: String,
        default: 'uc主编'
      },
      keyWordTags: {
        type: Array,
        default: ['machine learning', 'c++从入门到入土']
      },
      main_text: {
        type: String,
        default: "冯如杯写不完是怎么回事呢？冯如杯相信大家都很熟悉，但是冯如杯写不完是怎么回事呢，下面就让小编带大家一起了解吧。冯如杯写不完， 其实就是冯如杯就是憨憨， 大家可能会很惊讶冯如杯怎么会写不完呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦"
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
  }
</style>
