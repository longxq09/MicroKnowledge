<template>
  <div>
    <v-head :key="new Date().getTime()"></v-head>
    <v-admin-menu style="display: inline-block"></v-admin-menu>
    <div id="admin" style="position: relative; top: 60px;">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
  import vHead from './common/Header.vue';
  import vAdminMenu from './common/AdminMenu'
  export default {
    name: 'home',
    data() {
      return {
        homeLeft: '80px'
      }
    },
    components: {
      vHead,
      vAdminMenu
    },
    mounted() {
      /*
      if (sessionStorage.getItem("accountId") === '' || sessionStorage.getItem("accountId") === null) {
        this.$router.push('/')
      }
      */
      let adminElement = document.getElementById("admin")
      let menuElement = document.getElementById("adminMenu")
      adminElement.style.left = menuElement.style.width
      let elementResizeDetectorMaker = require("element-resize-detector");
      let erd = elementResizeDetectorMaker()
      erd.listenTo(menuElement, function (element) {
        adminElement.style.left = element.style.width
      })
    }
  }
</script>
