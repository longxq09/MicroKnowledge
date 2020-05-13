<template>
  <div class="header">
    <div class="title">{{title}}</div>
    <div style="width:200px; display:inline-block; margin-left: 8px">
      <el-input
        v-model="searchContent"
        placeholder="搜索关键字">
      </el-input>
    </div>
    <el-button
      style="display: inline"
      type="primary"
      icon="el-icon-search"
      @click="toSearch">
    </el-button>
    <el-badge class="button" v-if="accountId!=''">
      <el-button @click="logout">登出</el-button>
    </el-badge>
    <el-badge class="button" v-if="search">
      <el-button @click="back">返回</el-button>
    </el-badge>
    <el-badge class="button" v-if="homepage">
      <el-button @click="toUser">个人主页</el-button>
    </el-badge>
    <el-badge class="button" v-if="homepage">
      <el-button @click="toNewMEvid">发布新证据</el-button>
    </el-badge>
    <el-badge class="button" v-if="homepage">
      <el-button @click="toNewMGuess">发布新猜想</el-button>
    </el-badge>
    <el-badge class="button" v-if="detail">
      <el-button @click="toHomePage">回到首页</el-button>
    </el-badge>
    <el-badge class="button" v-if="user">
      <el-button @click="toHomePage">回到首页</el-button>
    </el-badge>
    <el-badge class="button" v-if="user">
      <el-button @click="toUserInfo">修改个人信息</el-button>
    </el-badge>
    <el-badge class="button" v-if="userinfo">
      <el-button @click="toUser">回到个人主页</el-button>
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
        searchContent: ''
      }
    },
    methods: {
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
      toNewMEvid() {
        this.$router.push('/new_mevid');
      },
      toNewMGuess() {
        this.$router.push('/new_mguess');
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
    background-color: #FFFFFF;
    line-height: 1.5;
    width: 100%;
  }
  .title {
    color: #409EFF;
    text-align: left;
    font-size: 30px;
    font-weight: 800;
    display: inline-block;
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
</style>
