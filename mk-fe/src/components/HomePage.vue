<template>
  <div>
    <el-tabs v-model="activeName" class="tabs">
      <el-tab-pane label="推荐" name="first">
        <v-page v-bind:display="exhibition"></v-page>
        <!-- <v-notice :key="value.id" v-for="(value,index) in exhibition"
                  v-bind:id="value.id"
                  v-bind:type="value.type"
                  v-bind:topics="value.topic"
                  v-bind:authorId="value.authorID"
                  v-bind:authorName="value.authorName"
                  v-bind:keywords="value.keywords"
                  v-bind:title="value.title"
                  v-bind:summary="value.summary">
        </v-notice> -->
      </el-tab-pane>
      <el-tab-pane label="关注" name="second">
        <v-page v-bind:display="followingState"></v-page>
        <!-- <v-notice :key="value.id" v-for="(value,index) in followingState"
                  v-bind:id="value.id"
                  v-bind:type="value.type"
                  v-bind:authorId="value.authorID"
                  v-bind:authorName="value.authorName"
                  v-bind:keywords="value.keywords"
                  v-bind:title="value.title"
                  v-bind:summary="value.summary">
        </v-notice> -->
      </el-tab-pane>
      <el-tab-pane label="热榜" name="third">
        <v-hot></v-hot>
      </el-tab-pane>
      <el-tab-pane label="评审" name="forth">
        <v-page v-bind:display=review_exhibition v-bind:review=true v-bind:comment=false></v-page>
      </el-tab-pane>
    </el-tabs>
    <el-card class="cloud">
      <span slot="header">标签云</span>
      <v-topic-cloud></v-topic-cloud>
    </el-card>
  </div>
</template>

<script>
  import vNotice from './common/Notice.vue';
  import vComment from './common/Comment.vue';
  import vHot from './common/Hot.vue'
  import vTopicCloud from './common/TopicCloud'
  import vPage from './common/Page.vue'
  export default {
    name: "HomePage",
    data() {
      return {
        count: 2,
        exhibition: Array,
        display:Array,
        review_exhibition: Array,
        followingState: Array,
        activeName: "first",
        accountId: 0,
        dis:true,
        total:0,
      }
    },

    components: {
      vNotice,
      vComment,
      vHot,
      vTopicCloud,
      vPage
    },
    created() {
      this.getUserInfo()
      this.getFollowingState()
    },
    methods: {
      async getUserInfo() {
        this.accountId = Number(sessionStorage.getItem("accountId"));
        var params = new URLSearchParams();
        try {
          params.append('accountId', this.accountId);
          let res = await this.axios.get('/mNotice/getNotices', params);
          this.exhibition = res.data;
          console.log(this.exhibition)
        } catch (err) {
          console.log(err);
        }
        try {
          let res = await this.axios.get('/review/getReviewList', params);
          this.review_exhibition = res.data;

          this.review_exhibition.forEach(item => {
            item.ifShow = (this.accountId!=item.authorID);
          });
          console.log(this.review_exhibition);

          this.display=this.review_exhibition.slice(0,this.count);
          console.log(this.display);
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
    display: inline-block;
    width: 700px;
    margin-top: 20px;
    margin-left: 80px;
    margin-bottom: 40px;
  }
  .cloud {
    width: 300px;
    display: inline-block;
    margin-top: 60px;
    margin-left: 20px;
    vertical-align: top
  }
</style>
