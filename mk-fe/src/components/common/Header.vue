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
    <el-dropdown v-if="login" @command="handleDropdown" class="dropdown">
      <el-avatar shape="square" :size="34" :src="avator"></el-avatar>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item></el-dropdown-item>
        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
        <el-dropdown-item command="editUserInfo">修改个人信息</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-badge style="float: right;margin: 10px;position: relative;" v-if="login">
      <el-button class="el-icon-message-solid" @click="toUserMessage" circle></el-button>
      <div class="red_dot" v-if="if_show_new">{{new_message}}</div>
    </el-badge>
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
    },
    data() {
      return {
        searchContent: '',
        new_message: 0,
        if_show_new: false,
        login: false,
        accountId: sessionStorage.getItem("accountId"),
        avator: 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3810033319,3262616285&fm=26&gp=0.jpg',
        male_pic: 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2644999504,2046739651&fm=26&gp=0.jpg',
        female_pic: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
      }
    },
    mounted() {
      this.login = sessionStorage.getItem("accountId") !== "" &&
        sessionStorage.getItem("accountId") != null
      this.getUserInfo();
    },
    methods: {
      async getUserInfo() {
        if (this.login) {
          let params = new URLSearchParams();
          params.append('userId', this.accountId);
          try {
            let res = await this.axios.post('/message/getUnReadNum', params);
            this.new_message = res.data;
            if (this.new_message != 0) {
              this.if_show_new = true;
            }
          } catch (err) {
            console.log(err);
          }
        }
      },
      toUserMessage(){
        this.$router.push({
          name: 'User',
          params: {
            activeName: "fifth"
          }
        });
      },
      handleDropdown(command) {
        if (command === "logout") {
          this.logout()
        } else if (command == "editUserInfo") {
          this.$router.push('/userinfo')
        }
      },
      logout() {
        this.axios.post('/user/logout')
          .then((res)=>{
            sessionStorage.setItem("accountId", "")
            sessionStorage.setItem("email", "")
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
  .dropdown {
    float: right;
    margin-right: 40px;
    margin-top: 10px;
    position: relative;
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
