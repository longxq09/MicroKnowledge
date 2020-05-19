<template>
  <el-container>
    <el-header>
      <v-head></v-head>
    </el-header>
    <el-main class="note" :style="note" style="width: 100%; height: 100%; padding: 0;">
      <el-card class="hotCard">
        <div slot="header" class="cardTitle">热榜</div>
        <div class="text item">
          <v-hot v-bind:user="true"></v-hot>
        </div>
      </el-card>
      <el-tabs v-model="activeName" class="isFixed">
        <el-tab-pane label="登录" name="first">
          <v-login></v-login>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <v-register></v-register>
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
  import vLogin from './common/Login.vue';
  import vRegister from './common/Register.vue';
  import vHot from './common/Hot.vue'
  export default {
    name: "Home",
    data() {
      return {
        fit: 'fill',
        activeName: 'first',
        url: 'https://images.669pic.com/element_banner/98/21/0/24/fdf28e13d04e144c4d79648bf7ae8bd4.jpg',
        note: {
          backgroundImage: "url(" +
            'https://pic.90sjimg.com/back_origin_pic/05/78/31/845f23b8d5a43f50e6ef958ad939e501.jpg!/watermark/url/LzkwX3dhdGVyX3Y2LnBuZw==/repeat/true' + ")",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
          backgroundSize: "cover",
        }
      }
    },
    components: {
      vHead,
      vFooter,
      vNotice,
      vLogin,
      vRegister,
      vHot
    },
    methods: {
    },

  }
</script>

<style scoped>
  .cardTitle {
    font-size: 20px;
    text-align: center;
  }
  .hotCard {
    width: 60%;
    margin: 40px 5% 40px 7.5%;
    display: inline-block;
  }
  .isFixed {
    width: 20%;
    display: inline-block;
    position:fixed;
    top: 100px;
    z-index:999;
  }
</style>
