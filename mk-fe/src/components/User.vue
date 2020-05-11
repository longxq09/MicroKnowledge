<template>
  <el-container>
    <el-header>
      <v-head v-bind:user=true></v-head>
    </el-header>
    <el-main>
      <div style="display: inline-block; width: 300px; margin: 20px; vertical-align: top">
        <el-image class="image" :src="src"></el-image>
        <p>{{name}}</p>
        <p>{{email}}</p>
      </div>
      <el-tabs v-model="activeName" style="display: inline-block; width: 680px">
        <el-tab-pane label="我的关注" name="first">
          <div class="follow" :key="index" v-for="(value,index) in following">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我的粉丝" name="second">
          <div class="follow" :key="index" v-for="(value,index) in follower">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我的收藏" name="third">
          <v-notice :key="value.id" v-for="(value,index) in favorite" v-bind:id="value.id" v-bind:type="value.type"
            v-bind:authorName="value.authorName" v-bind:keywords="value.keywords" v-bind:title="value.title"
            v-bind:summary="value.summary">
          </v-notice>
        </el-tab-pane>
        <el-tab-pane label="我的消息" name="forth">
          <v-message v-bind:type=1></v-message>
          <v-message v-bind:type=2></v-message>
          <v-message v-bind:type=3></v-message>
          <v-message v-bind:type=4></v-message>
          <v-message v-bind:type=5></v-message>
          <v-message v-bind:type=6></v-message>
          <v-message v-bind:type=7></v-message>

          <v-message :key="value.id" v-for="(value,index) in message_list"
          v-bind:type="value.type"
          v-bind:fromName="value.fromUserName"
          v-bind:relaltedNoticeId="value.relatedNoticeId"
          v-bind:relaltedNoticeTitle="value.relatedNoticeTitle"
          v-bind:detail="value.detail"
          v-bind:time="value.disTime"
          v-bind:flag="value.flag"
          >
          </v-message>

        </el-tab-pane>
      </el-tabs>
    </el-main>
    <v-footer></v-footer>
  </el-container>
</template>

<script>
  import vHead from './common/Header.vue';
  import vFooter from './common/Footer.vue';
  import vNotice from './common/Notice.vue';
  import vMessage from './common/Message.vue';
  export default {
    name: "User",
    data() {
      return {
        name: '',
        email: '',
        src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        activeName: 'first',
        following: Array,
        follower: Array,
        favorite: Array,
        message_list: Array
      };
    },
    components: {
      vHead,
      vFooter,
      vNotice,
      vMessage,
    },
    mounted() {
      this.getUserInfo()
      this.getFollow()
      this.getFavorite()
    },
    methods: {
      async getUserInfo() {
        this.axios.get('/user/info', {
            params: {
              id: localStorage.getItem("accountId")
            }
          }).then((res) => {
            this.name = res.data.name
            this.email = localStorage.getItem("email")
          })
          .catch((error) => {
            console.log(error)
          })

        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        try {
          let res = await this.axios.post('/message/getMessages', params);
          this.message_list = res.data;
          console.log(this.message_list);
        } catch (err) {
          console.log(err);
        }
      },
      getFollow() {
        this.axios.get('/follow/getFollowing', {
            params: {
              id: localStorage.getItem("accountId")
            }
          }).then((res) => {
            this.following = res.data
          })
          .catch((error) => {
            console.log(error)
          })
        this.axios.get('/follow/getFollower', {
            params: {
              id: localStorage.getItem("accountId")
            }
          }).then((res) => {
            this.follower = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      },
      getFavorite() {
        this.axios.get('/favorite/getFavoriteList', {
            params: {
              id: localStorage.getItem("accountId")
            }
          }).then((res) => {
            this.favorite = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }

  }
</script>

<style scoped>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

  .image {
    vertical-align: top;
    margin: 0;
  }

  .follow {
    margin: 12px;
  }
</style>
