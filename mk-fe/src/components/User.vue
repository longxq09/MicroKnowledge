<template>
  <el-container>
    <el-header>
      <v-head v-bind:user=true></v-head>
    </el-header>
    <el-main>
      <div style="display: inline-block; width: 300px; margin-left: 8%; vertical-align: top">
        <el-image class="image" :src="src"></el-image>
        <p>{{name}}</p>
        <p>{{email}}</p>
      </div>
      <el-tabs v-model="activeName" style="display: inline-block; width: 600px; margin-left: 40px; margin-right: 8%">
        <el-tab-pane label="关注" name="first">
          <div class="follow" :key="index" v-for="(value,index) in following">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <v-follow v-if="accountId==hostId"
                      style="float: right"
                      v-bind:accountId="accountId"
                      v-bind:authorId="value.id">
            </v-follow>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="粉丝" name="second">
          <div class="follow" :key="index" v-for="(value,index) in follower">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <v-follow v-if="accountId==hostId"
                      style="float: right"
                      v-bind:accountId="accountId"
                      v-bind:authorId="value.id">
            </v-follow>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="收藏" name="third">
          <v-notice :key="value.id"
                    v-for="(value,index) in favorite"
                    v-bind:accountId="accountId"
                    v-bind:id="value.id"
                    v-bind:type="value.type"
                    v-bind:authorName="value.authorName"
                    v-bind:keywords="value.keywords"
                    v-bind:title="value.title"
                    v-bind:summary="value.summary"
                    v-bind:user=true>
          </v-notice>
        </el-tab-pane>
        <el-tab-pane label="我的发布" name="forth">
          <v-notice :key="value.id"
                    v-for="(value,index) in myNotice"
                    v-bind:accountId="accountId"
                    v-bind:id="value.id"
                    v-bind:type="value.type"
                    v-bind:authorName="value.authorName"
                    v-bind:keywords="value.keywords"
                    v-bind:title="value.title"
                    v-bind:summary="value.summary"
                    v-bind:judge="value.judge"
                    v-bind:user=true>
          </v-notice>
        </el-tab-pane>
        <el-tab-pane v-if="accountId==hostId"
                     label="我的消息" name="fifth">
          <v-message :key="value.id" v-for="(value,index) in message_list"
                     v-bind:type="value.type"
                     v-bind:fromName="value.fromUserName"
                     v-bind:relatedNoticeId="value.relatedNoticeId"
                     v-bind:relatedNoticeTitle="value.relatedNoticeTitle"
                     v-bind:detail="value.detail"
                     v-bind:time="value.disTime"
                     v-bind:flag="value.flag">
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
  import vFollow from './common/Follow.vue'
  export default {
    name: "User",
    data() {
      return {
        hostId: localStorage.getItem("accountId"),
        accountId: localStorage.getItem("accountId"),
        name: '',
        email: '',
        src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        activeName: 'first',
        following: Array,
        follower: Array,
        favorite: Array,
        myNotice: Array,
        message_list: Array
      };
    },
    components: {
      vHead,
      vFooter,
      vNotice,
      vMessage,
      vFollow
    },
    mounted() {
      this.getUserInfo()
      this.getNotice()
      this.getFollow()
      this.getFavorite()
    },
    methods: {
      async getUserInfo() {
        if (this.$route.params.activeName.length!=0) {
          this.activeName = this.$route.params.activeName;
        }
        this.axios.get('/user/info', {
          params: {
            id: this.accountId
          }
        }).then((res) => {
          this.name = res.data.name
        })
          .catch((error) => {
            console.log(error)
          })

        var params = new URLSearchParams();
        params.append('userId', this.accountId);
        try {
          let res = await this.axios.post('/message/getMessages', params);
          this.message_list = res.data;
        } catch (err) {
          console.log(err);
        }
      },
      getNotice() {
        this.axios.get('/mNotice/getSelfNotices', {
          params: {
            id: this.accountId
          }
        }).then((res) => {
          this.myNotice = res.data
        })
          .catch((error) => {
            console.log(error)
          })
      },
      getFollow() {
        this.axios.get('/follow/getFollowing', {
          params: {
            id: this.accountId
          }
        }).then((res) => {
          this.following = res.data
        })
          .catch((error) => {
            console.log(error)
          })
        this.axios.get('/follow/getFollower', {
          params: {
            id: this.accountId
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
            id: this.accountId
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
