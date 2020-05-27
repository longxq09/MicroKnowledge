<template>
  <div id="app">
    <v-admin v-if="$route.meta.admin"></v-admin>
    <vHome v-if="$route.meta.user"></vHome>
    <router-view v-if="$route.meta.visitor"></router-view>
  </div>
</template>
<script>
  import vHome from './components/Home.vue'
  import vAdmin from './components/AdminHome'
  export default {
    name:'app',
    components: {
      vHome,
      vAdmin
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
            position: 'right',
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
