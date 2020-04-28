<template>
  <el-container>
    <el-header>
      <v-head v-bind:user=true></v-head>
    </el-header>
    <el-main>
      <el-tabs v-model="activeName" style="margin-left: 15%;margin-right: 15%;">
        <el-tab-pane label="推荐" name="first">
          <v-notice v-bind:user=true></v-notice>
          <v-notice></v-notice>
        </el-tab-pane>
        <el-tab-pane label="关注" name="second">
        </el-tab-pane>
        <el-tab-pane label="热榜" name="third">
        </el-tab-pane>
      </el-tabs>
    </el-main>
    <v-footer></v-footer>
  </el-container>
</template>

<script>
  import vHead from './common/Header.vue';
  import vFooter from './common/Footer.vue';
  import vNotice from './common/Notice.vue';
  export default {
    name: "HomePage",
    data() {
      return {
				activeName: 'first',
        type: "微猜想",
        title: "震惊！冯如杯要写不完了？！",
        author: "uc主编",
        keyWordTags: ['machine learning', 'c++从入门到入土'],
        main_text: "冯如杯写不完是怎么回事呢？冯如杯相信大家都很熟悉，但是冯如杯写不完是怎么回事呢，下面就让小编带大家一起了解吧。冯如杯写不完， 其实就是冯如杯就是憨憨， 大家可能会很惊讶冯如杯怎么会写不完呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦",
      }
    },

    components: {
      vHead,
      vFooter,
      vNotice
    },
    methods: {}

  }
</script>

<style>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }
</style>
