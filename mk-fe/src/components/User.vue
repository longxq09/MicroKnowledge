<template>
  <div style="width: 900px; margin-top: 40px; margin-left: 10%;">
    <el-image class="image" :src="src"></el-image>
    <div style="display: inline-block; margin-top: 12px; margin-bottom: 20px; width: 400px">
      <v-user-info-board v-bind:id="accountId"></v-user-info-board>
    </div>
    <el-tabs v-model="activeName" style="width: 90%; margin-left: 8%; margin-bottom: 80px">
      <el-tab-pane label="关注" name="first">
        <div class="follow" v-for="(value,index) in following" :key="new Date().getTime()">
          <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
          <div style="display: inline-block" >
            <v-user-name :id="value.id.toString()"
                         :show="'name'"
                         :button="value.name">
            </v-user-name>
            <v-user-name :id="value.id.toString()"
                         :show="'email'"
                         :button="value.email">
            </v-user-name>
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
        <div class="follow" v-for="(value,index) in follower" :key="value.name">
          <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
          <div style="display: inline-block">
            <v-user-name :id="value.id.toString()"
                         :show="'name'"
                         :button="value.name">
            </v-user-name>
            <v-user-name :id="value.id.toString()"
                         :show="'email'"
                         :button="value.email">
            </v-user-name>
          </div>
          <v-follow v-if="accountId===hostId"
                    style="float: right"
                    v-bind:accountId="accountId"
                    v-bind:authorId="value.id">
          </v-follow>
          <el-divider></el-divider>
        </div>
      </el-tab-pane>
      <el-tab-pane label="收藏" name="third">
        <v-notice :key="new Date().getTime()"
                  v-for="(value,index) in favorite"
                  v-bind:accountId="accountId"
                  v-bind:id="value.id"
                  v-bind:type="value.type"
                  v-bind:authorName="value.authorName"
                  v-bind:authorId="value.authorID"
                  v-bind:keywords="value.keywords"
                  v-bind:title="value.title"
                  v-bind:summary="value.summary">
        </v-notice>
      </el-tab-pane>
      <el-tab-pane label="我的发布" v-if="accountId===hostId" name="forth">
        <v-notice :key="value.id"
                  v-for="(value,index) in myNotice"
                  v-bind:accountId="accountId"
                  v-bind:id="value.id"
                  v-bind:type="value.type"
                  v-bind:authorName="value.authorName"
                  v-bind:authorId="value.authorID"
                  v-bind:keywords="value.keywords"
                  v-bind:title="value.title"
                  v-bind:summary="value.summary"
                  v-bind:judge="value.judge"
                  v-bind:user=true>
        </v-notice>
      </el-tab-pane>
      <el-tab-pane v-if="accountId===hostId"
                   label="我的消息" name="fifth">
        <v-message :key="value.id" v-for="(value,index) in message_list"
                   v-bind:type="value.type"
                   v-bind:fromName="value.fromUserName"
                   v-bind:relatedNoticeId="value.relatedNoticeId"
                   v-bind:relatedNoticeTitle="value.relatedNoticeTitle"
                   v-bind:detail="value.detail"
                   v-bind:time="value.disTime">
        </v-message>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import vNotice from './common/Notice.vue';
  import vMessage from './common/Message.vue';
  import vFollow from './common/Follow.vue';
  import vShowTopic from './common/ShowTopic.vue';
  import vUserName from './common/UserName'
  import vUserInfoBoard from './common/UserInfoBoard'
  export default {
    name: "User",
    data() {
      return {
        hostId: sessionStorage.getItem("accountId"),
        accountId: sessionStorage.getItem("accountId"),
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
      vNotice,
      vMessage,
      vFollow,
      vShowTopic,
      vUserName,
      vUserInfoBoard
    },
    created() {
      if (this.$route.query.accountId) {
        this.accountId = this.$route.query.accountId;
      }
      if (this.$route.params.activeName !== undefined) {
        this.activeName = this.$route.params.activeName;
      }
    },
    mounted() {
      this.initial()
    },
    watch: {
      $route() {
        if (this.$route.query.accountId) {
          this.accountId = this.$route.query.accountId
        } else {
          this.accountId = sessionStorage.getItem("accountId")
        }
        if (this.$route.params.activeName) {
          this.activeName = this.$route.params.activeName
        }
      },
      accountId() {
        this.initial()
      },
    },
    methods: {
      initial() {
        this.getMessage()
        this.getNotice()
        this.getFollow()
        this.getFavorite()
      },
      async getMessage() {
        let params = new URLSearchParams();
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
  .image {
    display: inline-block;
    width: 300px;
    margin: 12px 4% 40px 12%;
    vertical-align: top;
  }

  .follow {
    margin: 12px;
  }
</style>
