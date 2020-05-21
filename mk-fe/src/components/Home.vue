<template>
  <div>
    <v-head></v-head>
    <v-menu style="display: inline-block"></v-menu>
    <div id="home" style="position: absolute; top: 60px;">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
  import vHead from './common/Header.vue';
  import vMenu from './common/NavMenu.vue';
  export default {
    name: 'home',
    data() {
      return {
        homeLeft: '80px'
      }
    },
    components: {
      vHead,
      vMenu
    },
    mounted() {
      if (sessionStorage.getItem("accountId") === '' || sessionStorage.getItem("accountId") === null) {
        this.$router.push('/')
      }
      let homeElement = document.getElementById("home")
      let menuElement = document.getElementById("menu")
      homeElement.style.left = menuElement.style.width
      let elementResizeDetectorMaker = require("element-resize-detector");
      let erd = elementResizeDetectorMaker()
      erd.listenTo(menuElement, function (element) {
        homeElement.style.left = element.style.width
      })
    }
  }
</script>
