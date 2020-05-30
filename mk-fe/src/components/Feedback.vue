<template>
    <el-row style="width: 990px; margin-bottom: 5%;margin-top: 5%; margin-left: 10%;">
      <el-col :span="16">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>留言</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="handleSaying()">提交</el-button>
          </div>
          <p>您可以在下方写下想对开发者说的话，我们会认真读取您的每一条留言！</p>
          <p>内容可以包括但不限于</p>
          <p>您的使用感受</p>
          <p>对我们的改进意见</p>
          <p>您在使用过程中遇到的bug</p>
          <p>......</p>
          <el-input type="textarea" rows="7" v-model="saying"></el-input>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>评分</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="handleScore()">提交</el-button>
          </div>
          <p>您也可以为我们评分，帮助我们找到不足:-)</p>
          <el-form label-width="80px">
            <el-form-item label="界面设计">
              <el-rate v-model="value1" show-text></el-rate>
            </el-form-item>
            <el-form-item label="功能设计">
              <el-rate v-model="value2" show-text></el-rate>
            </el-form-item>
            <el-form-item label="总体评价">
              <el-rate v-model="value3" show-text></el-rate>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
</template>

<script>
  export default {
    name: "Feedback",
    data() {
      return {
        saying: "",
        value1: 0,
        value2: 0,
        value3: 0,
      }
    },
    methods: {
      handleSaying()
      {
        if(this.saying.length==0){
          this.$alert("写点什么再提交吧！", {
            confirmButtonText: '确定'
          });
        }
        else{
            this.$alert("感谢您的反馈！我们将仔细认真阅读！", {
              confirmButtonText: '确定'
            });
            this.saying="";
        }
      },
      handleScore()
      {
        if(this.value1==0||this.value2==0||this.value3==0){
          this.$alert("请打完分再提交吧！", {
            confirmButtonText: '确定'
          });
        }
        else{
            this.$alert("感谢您的反馈！我们继续改进！", {
              confirmButtonText: '确定'
            });
            this.value1=0;
            this.value2=0;
            this.value3=0;
        }
      },
    }

  }
</script>

<style>
  .el-form-item {
    margin-top: 30px;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 90%;
    background-color: #f4f8ff;
  }
</style>
