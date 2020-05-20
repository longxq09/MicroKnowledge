<template>
    <el-menu
      style="height: 100%; width: 200px;"
      router="true"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="homepage">
        <i class="el-icon-location"></i>
        <span>首页</span>
      </el-menu-item>
      <el-submenu index="add">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>发布</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="new_mevid">发布微证据</el-menu-item>
          <el-menu-item index="new_mguess">发布微猜想</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="user">
        <i class="el-icon-document"></i>
        <span slot="title">个人主页</span>
      </el-menu-item>
    </el-menu>
</template>

<script>
  export default {
  }
</script>
