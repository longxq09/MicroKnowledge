<template>
	<el-container>
		<el-header>
			<v-head></v-head>
		</el-header>
		<el-main>
		  <div style="height: 360px">
        <el-image class="image" :src="url" :fit="fit"></el-image>
        <el-tabs v-model="activeName" style="display: inline-block; margin-left: 15%; height: 300px">
          <el-tab-pane label="登录" name="first">
            <v-login></v-login>
          </el-tab-pane>
          <el-tab-pane label="注册" name="second">
            <v-register></v-register>
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-card style="width: 800px; margin: auto">
        <div slot="header" class="cardTitle">热榜</div>
        <div class="text item">
          <v-notice></v-notice>
          <v-notice></v-notice>
        </div>
      </el-card>
		</el-main>
		<v-footer></v-footer>
	</el-container>
</template>

<script>
import vHead from './common/Header.vue';
import vFooter from './common/Footer.vue';
import vNotice from './common/notice.vue';
import vLogin from './Login.vue';
import vRegister from './Register.vue';
  export default {
    name: "Home",
    data() {
      return {
        fit: 'fill',
        activeName: 'first',
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        activeName2: 'first',
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
			vNotice,
			vLogin,
			vRegister
		},
		methods: {
    }

  }
</script>

<style>

  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

	.remind {
		display: inline-block;
		font-size: 8px;
		color: red;
		text-decoration: underline;
	}

	.image {
    width: 200px;
    height: 200px;
    display: inline-block;
    margin-left: 20%;
    vertical-align: top;
    padding-top: 32px;
  },

  .card {
    width: 800px;
    margin: auto;
  }

  .cardTitle {
    font-size: 20px;
    text-align: center;
  }
</style>
