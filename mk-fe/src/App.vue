<template>
  <div id="app">
    <el-container v-if="$route.meta.keepAlive">
      <el-aside>
        <v-menu></v-menu>
      </el-aside>
      <el-container>
        <el-header>
          <v-head></v-head>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
        <el-footer>
          <v-footer></v-footer>
        </el-footer>
      </el-container>
    </el-container>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>
<script>
  import vHead from './components/common/Header.vue';
  import vFooter from './components/common/Footer.vue';
  import vMenu from './components/common/NavMenu.vue';
  export default {
    name:'app',
    components: {
      vHead,
      vFooter,
      vMenu
    },
    created() {
      setTimeout(() => {
        window.L2Dwidget.init({
          pluginRootPath: 'static/live2dw/',
          pluginJsPath: 'lib/',
          pluginModelPath: 'live2d-widget-model-koharu/assets/',
          tagMode: false,
          debug: false,
          model: {
            jsonPath: '/static/live2dw/live2d-widget-model-koharu/assets/koharu.model.json'
          },
          display: {
            position: 'left',
            width: 150,
            height: 300
          },
          mobile: {
            show: true
          },
          log: false
        })
      }, 1000)
    }
  }
</script>
<style>
  #app {}
</style>
