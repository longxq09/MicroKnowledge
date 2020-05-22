<template>
  <div>
    <el-button v-if="show==='name'"
               type="text"
               class="userName"
               @click="dialogVisible = true">{{title}}
    </el-button>
    <el-button v-if="show==='email'"
               type="text"
               class="userEmail"
               @click="dialogVisible = true">{{title}}
    </el-button>
    <el-button v-if="show==='author'"
               type="text"
               class="author"
               @click="dialogVisible = true">{{title}}
    </el-button>
    <el-button v-if="show==='detail'"
               type="text"
               class="detail"
               @click="dialogVisible = true">{{title}}
    </el-button>
    <el-dialog
      title="用户信息"
      :visible.sync="dialogVisible"
      width="400px"
      :append-to-body="true">
      <div style="margin-bottom: 20px">
        <v-user-info-board v-bind:id="accountId"></v-user-info-board>
      </div>
      <el-button @click="dialogVisible = false">返回</el-button>
      <el-button type="primary" @click="toOtherUser">查看个人主页</el-button>
    </el-dialog>
  </div>
</template>

<script>
  import vUserInfoBoard from './UserInfoBoard'
  export default {
    name: "Username",
    props: {
      id: {
        type: String,
        default: ''
      },
      show: {
        type: String,
        default: ''
      },
      button: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        title: '',
        accountId: '',
        dialogVisible: false,
      }
    },
    components: {
      vUserInfoBoard
    },
    mounted() {
      this.title = this.button
      this.accountId = this.id
    },
    methods: {
      toOtherUser() {
        this.dialogVisible = false
        if (sessionStorage.getItem("accountId")!=null &&
          sessionStorage.getItem("accountId")!=='') {
          this.$router.push({
            name: 'User',
            query: {
              accountId: this.accountId
            }
          })
        } else {
          this.$message({
            showClose: true,
            message: '未登录！请在登陆后查看！',
            type: 'error'
          });
        }

      },
    }
  }
</script>
<style>
  .userName {
    font-size: large;
    font-weight: bold;
    padding-bottom: 4px;
  }
  .userEmail {
    font-size: medium;
    padding-bottom: 4px;
  }
  .author {
    font-size: medium;
    font-weight: 600;
    margin-left: 10px;
  }
  .detail {
    font-size: small;
    font-weight: 600;
    margin-left: 10px;
  }
</style>
