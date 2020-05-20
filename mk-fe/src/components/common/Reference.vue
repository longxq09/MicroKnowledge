<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>{{title}}</span>
    </div>
    <div>
      auther:{{authorName}}
    </div>
    <div>
      {{summary}}
    </div>
  </el-card>
</template>

<script>
  export default {
    name: "Detail",
    props: {
      title: {
        type: String,
        default: 'test'
      },
      authorName: {
        type: String,
        default: 'test'
      },
      summary: {
        type: String,
        default: "test"
      },
    },

    data() {
      return {}
    },
    components: {
    },
    created() {
    },
    methods: {

    }
  }
</script>

<style>
  .el-header {
    background-color: #FFFFFF;
    line-height: 1.5;
  }
</style>
