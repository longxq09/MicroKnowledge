<template>
  <el-container>
    <el-header style="padding: 0">
      <v-head></v-head>
    </el-header>
    <el-main style="width: 100%; padding: 0;">
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
    </el-main>
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
        activeName: 'first',
        url: 'https://images.669pic.com/element_banner/98/21/0/24/fdf28e13d04e144c4d79648bf7ae8bd4.jpg',
        note: {
          backgroundImage: "url(" +
            'https://i2.wp.com/files.123freevectors.com/wp-content/original/132518-geometric-abstract-dark-blue-background-vector.jpg?w=800&q=95'
            + ")",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
          backgroundSize: "cover",
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
    mounted() {
      this.height = document.documentElement.clientHeight
      window.onresize = function () {
        this.height = document.documentElement.clientHeight
      }
    },
    watch: {
      height: function (val) {
        this.changeFixed(val)
      }
    },
    methods: {
      changeFixed(height) {
        this.$refs.visitor.style.height = height + 'px'
      }
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
