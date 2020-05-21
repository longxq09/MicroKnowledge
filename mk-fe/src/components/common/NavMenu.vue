<template>
  <div id="menu" ref="menu" style="z-index: 105; position: fixed; top: 60px;">
    <el-menu
      style="height: 100%;"
      router
      :default-active="$route.path"
      :collapse="isCollapse"
      active-text-color="#ffd04b">
      <div style="text-align: right">
        <el-button v-if="!isCollapse"
                   style="border-color: white"
                   icon="el-icon-arrow-left"
                   @click="changeCollapse">
        </el-button>
        <el-button v-if="isCollapse"
                   style="border-color: white"
                   icon="el-icon-arrow-right"
                   @click="changeCollapse">
        </el-button>
      </div>
      <el-menu-item index="/homepage">
        <i class="el-icon-s-home"></i>
        <span slot="title">首页</span>
      </el-menu-item>
      <el-submenu index="add">
        <template slot="title">
          <i class="el-icon-circle-plus-outline"></i>
          <span>发布</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/new_mevid">发布微证据</el-menu-item>
          <el-menu-item index="/new_mguess">发布微猜想</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/user">
        <i class="el-icon-user"></i>
        <span slot="title">个人主页</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        isCollapse: true,
        height: ''
      }
    },
    mounted() {
      this.$refs.menu.style.width = '80px'
      this.height = document.documentElement.clientHeight
      window.onresize = function () {
        this.height = document.documentElement.clientHeight
      }
    },
    watch: {
      height: function (val) {
        this.changeHeight(val)
      },
    },
    methods: {
      changeHeight(height) {
        this.$refs.menu.style.height = height + 'px'
      },
      changeCollapse() {
        this.isCollapse = !this.isCollapse
        if (this.isCollapse) {
          this.$refs.menu.style.width = '80px'
        } else {
          this.$refs.menu.style.width = '200px'
        }
      }
    },
  }
</script>
