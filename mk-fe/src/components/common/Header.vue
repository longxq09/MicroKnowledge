<template>
  <div class="header">
    <div class="title">{{title}}</div>
    <div class="searchBox">
      <div style="width:200px; display: inline-block; margin-left: 8px">
        <el-input
          v-model="searchContent"
          placeholder="搜索关键字">
        </el-input>
      </div>
      <el-button
        type="primary"
        icon="el-icon-search"
        @click="toSearch">
      </el-button>
    </div>
    <el-badge class="button" v-if="login">
      <el-button @click="logout">登出</el-button>
    </el-badge>
    <el-badge class="button" v-if="search">
      <el-button @click="back">返回</el-button>
    </el-badge>
    <el-badge class="button" v-if="(user||detail)&&login">
      <el-button @click="toHomePage">回到首页</el-button>
    </el-badge>
    <el-badge class="button" v-if="!login">
      <el-button @click="toLogin">登录</el-button>
    </el-badge>
    <div class="photo" v-if="user">
      <el-avatar> user </el-avatar>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Header",
    props: {
      title: {
        type: String,
        default: '微知 MicroKnowledge'
      },
      home: {
        type: Boolean,
        default: false,
      },
      search: {
        type: Boolean,
        default: false,
      },
      homepage: {
        type: Boolean,
        default: false
      },
      userinfo: {
        type: Boolean,
        default: false
      },
      user: {
        type: Boolean,
        default: false
      },
      detail: {
        type: Boolean,
        default: false
      },
    },
    data() {
      return {
        accountId: localStorage.getItem("accountId"),
        searchContent: '',
        new_message: 0,
        if_show_new: false,
        login: false,
      }
    },
    mounted() {
      this.login = localStorage.getItem("accountId") != ""
      this.getUserInfo();
    },
    methods: {
      async getUserInfo() {
        if (this.accountId!="") {
          var params = new URLSearchParams();
          params.append('userId', this.accountId);
          try {
            let res = await this.axios.post('/message/getUnReadNum', params);
            console.log("getUnReadNum="+res.data);
            this.new_message = res.data;
            if (this.new_message != 0) {
              this.if_show_new = true;
            }
          } catch (err) {
            console.log(err);
          }
        }
      },
      toLogin() {
        this.$router.push('/')
      },
      logout() {
        this.axios.post('/user/logout')
          .then((res)=>{
            localStorage.setItem("accountId", "")
            localStorage.setItem("email", "")
            let remindTitle = res.data
            let remindContent = '期待您的下一次访问！'
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            });
            this.$router.push('/')
          })
          .catch((res)=>{
            console.log(res.message);
          });
      },
      toHomePage() {
        this.$router.push('/homepage');
      },
      toUser() {
        this.$router.push('/user');
      },
      toUser_message(){
        this.$router.push({
          name: 'User',
          params: {
            activeName: "fifth"
          }
        });
      },
      back() {
        if(localStorage.getItem("accountId") === "") {
          this.$router.push('/')
        }
        else {
          this.toHomePage()
        }
      },
      toUserInfo() {
        this.$router.push('/userinfo');
      },
      toSearch() {
        this.$router.push({
          path: '/search',
          query: {
            word: this.searchContent,
            kind: 0,
            topic: ''
          }
        })
      }
    }
  }
</script>

<style>
  .header {
    position: fixed;
    height: 60px;
    background-color: #3f51b5;
    color: white;
    line-height: 1.5;
    width: 100%;
    z-index: 100;
  }
  .title {
    padding-top: 12px;
    padding-left: 20px;
    color: white;
    font-size: 24px;
    font-weight: 800;
    display: inline-block;
  }
  .searchBox {
    display: inline-block;
    text-align: center;
  }
  .button {
    float: right;
    margin: 10px;
  }
  .photo {
    display: inline-block;
    float: right;
    margin-top: 5px;
  }
  .red_dot {
    width: 20px;
    height: 20px;
    line-height: 20px;
    font-size: 10px;
    color: #fff;
    text-align: center;
    background-color: #f00;
    border-radius: 50%;
    position: absolute;
    right: -10px;
    top: -10px;
  }
</style>
