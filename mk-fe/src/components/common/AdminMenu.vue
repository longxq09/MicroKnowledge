<template>
  <div id="adminMenu" ref="adminMenu" style="z-index: 105; position: fixed; top: 60px; height: 680px">
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
      <el-menu-item index="/admin">
        <i class="el-icon-s-home"></i>
        <span slot="title">主页</span>
      </el-menu-item>
      <el-menu-item index="/admin/topic">
        <i class="el-icon-magic-stick"></i>
        <span slot="title">管理标签</span>
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
      this.$refs.adminMenu.style.width = '80px'
      console.log("miao")
    },
    methods: {
      changeCollapse() {
        this.isCollapse = !this.isCollapse
        if (this.isCollapse) {
          this.$refs.adminMenu.style.width = '80px'
        } else {
          this.$refs.adminMenu.style.width = '200px'
        }
      }
    },
  }
</script>
