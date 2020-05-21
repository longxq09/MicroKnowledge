<template>
  <el-tabs v-model="activeName" class="tabs">
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
      <v-hot></v-hot>
    </el-tab-pane>
    <el-tab-pane label="评审" name="forth">
      <v-notice :key="value.id" v-for="(value,index) in review_exhibition"
                v-if="accountId!=value.authorID"
                v-bind:id="value.id"
                v-bind:type="value.type"
                v-bind:authorName="value.authorName"
                v-bind:keywords="value.keywords"
                v-bind:title="value.title"
                v-bind:summary="value.summary"
                v-bind:review=true
                v-bind:comment=false>
      </v-notice>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
  import vNotice from './common/Notice.vue';
  import vComment from './common/Comment.vue';
  import vHot from './common/Hot.vue'
  export default {
    name: "HomePage",
    data() {
      return {
        exhibition: Array,
        review_exhibition: Array,
        followingState: Array,
        activeName: "first",
        accountId: 0,
      }
    },

    components: {
      vNotice,
      vComment,
      vHot
    },
    created() {
      this.getUserInfo()
      this.getFollowingState()
    },
    methods: {
      async getUserInfo() {
        this.accountId=Number(sessionStorage.getItem("accountId"));
        console.log("====="+this.accountId);
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

          this.review_exhibition.forEach(item => {
            item.ifShow = (this.accountId!=item.authorID);
          });
        } catch (err) {
          console.log(err);
        }
      },
      getFollowingState() {
        this.axios.get('/follow/getFollowingState', {
          params: { id: sessionStorage.getItem("accountId")}
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
  .tabs {
    width: 700px;
    margin-top: 20px;
    margin-left: 15%;
    margin-right: 15%;
  }
</style>
