<template>
  <el-container>
    <el-header>
      <v-head v-bind:home="true"></v-head>
    </el-header>
    <el-main>
      <div style="height: 400px">
        <el-image class="image" :src="url" :fit="fit"></el-image>
        <el-tabs v-model="activeName" style="display: inline-block; margin-left: 15%">
          <el-tab-pane label="登录" name="first">
            <v-login></v-login>
          </el-tab-pane>
          <el-tab-pane label="注册" name="second">
            <v-register></v-register>
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-card style="width: 80%; margin: auto">
        <div slot="header" class="cardTitle">热榜</div>
        <div class="text item">
        </div>
      </el-card>
    </el-main>
    <v-footer></v-footer>
  </el-container>
</template>

<script>
  import vHead from './common/Header.vue';
  import vFooter from './common/Footer.vue';
  import vNotice from './common/Notice.vue';
  import vLogin from './Login.vue';
  import vRegister from './Register.vue';
  export default {
    name: "Home",
    data() {
      return {
        fit: 'fill',
        activeName: 'first',
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
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

<style scoped>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

  .image {
    width: 20%;
    height: 60%;
    display: inline-block;
    margin-left: 20%;
    vertical-align: top;
    padding-top: 32px;
  }

  .cardTitle {
    font-size: 20px;
    text-align: center;
  }
</style>
