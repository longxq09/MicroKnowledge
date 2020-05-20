<template>
  <div>
    <v-head></v-head>
    <div class="note" :style="note" ref="visitor">
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
    </div>
  </div>
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
        activeName: 'first',
        url: 'https://images.669pic.com/element_banner/98/21/0/24/fdf28e13d04e144c4d79648bf7ae8bd4.jpg',
        note: {
          backgroundImage: "url(" +
            'data:image/jpeg;base64,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'
            + ")",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
          backgroundSize: "cover",
          minHeight: '680px',
          position: 'absolute',
          top: '60px'
        },
        height: ''
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
    z-index: 100;
  }
</style>
