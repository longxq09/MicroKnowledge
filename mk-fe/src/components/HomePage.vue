<template>
  <el-container>
    <el-header>
      <v-head v-bind:homepage=true></v-head>
    </el-header>
    <el-main>
      <el-tabs v-model="activeName" style="margin-left: 15%;margin-right: 15%;">
        <el-tab-pane label="推荐" name="first">
          <v-notice :key="value.id" v-for="(value,index) in exhibition"
                    v-bind:id="value.id"
                    v-bind:type="value.type"
                    v-bind:authorId="value.authorID"
                    v-bind:authorName="value.authorName"
                    v-bind:keywords="value.keywords"
                    v-bind:title="value.title"
                    v-bind:summary="value.summary">
          </v-notice>
        </el-tab-pane>
        <el-tab-pane label="关注" name="second">
          <v-notice :key="value.id" v-for="(value,index) in followingState"
                    v-bind:id="value.id"
                    v-bind:type="value.type"
                    v-bind:authorId="value.authorID"
                    v-bind:authorName="value.authorName"
                    v-bind:keywords="value.keywords"
                    v-bind:title="value.title"
                    v-bind:summary="value.summary">
          </v-notice>
        </el-tab-pane>
        <el-tab-pane label="热榜" name="third">
        </el-tab-pane>
        <el-tab-pane label="评审" name="forth">
          <v-notice :key="value.id" v-for="(value,index) in review_exhibition"
                    v-bind:id="value.id"
                    v-bind:type="value.type"
                    v-bind:authorName="value.authorName"
                    v-bind:keywords="value.keywords"
                    v-bind:title="value.title"
                    v-bind:summary="value.summary"
                    v-bind:review=true>
          </v-notice>
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
  import vComment from './common/Comment.vue';
  export default {
    name: "HomePage",
    data() {
      return {
        exhibition: Array,
        followingState: Array,
        activeName: "first",
      }
    },

    components: {
      vHead,
      vFooter,
      vNotice,
      vComment
    },
    created() {
      this.getUserInfo()
      this.getFollowingState()
    },
    methods: {
      async getUserInfo() {
        var params = new URLSearchParams();
        try {
          let res = await this.axios.get('/mNotice/getNotices', params);
          this.exhibition = res.data;
        } catch (err) {
          console.log(err);
        }
        try {
          let res = await this.axios.get('/review/getReviewList', params);
          this.review_exhibition = res.data;
        } catch (err) {
          console.log(err);
        }
      },
      getFollowingState() {
        this.axios.get('/follow/getFollowingState', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
          this.followingState = res.data
        })
          .catch((error) => {
            console.log(error)
          });
      }
    },
  }
</script>

<style>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }
</style>
