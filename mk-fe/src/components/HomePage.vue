<template>
  <div>
    <el-tabs v-model="activeName" class="tabs"
             @tab-click="getData">
      <el-tab-pane label="推荐" name="first" style="width: 100%">
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
        <v-page v-bind:display="hotNotices"></v-page>
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
  import { Loading } from 'element-ui';
  export default {
    name: "HomePage",
    data() {
      return {
        exhibition: Array,
        review_exhibition: Array,
        followingState: Array,
        hotNotices: Array,
        activeName: "first",
        accountId: 0,
        total:0
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
      this.accountId = Number(sessionStorage.getItem("accountId"));
    },
    mounted() {
      console.log("mounted")
      this.getNotices()
      this.getReviewList()
    },
    methods: {
      getData(tab) {
        if(tab.name === "first") {
          this.getNotices()
        } else if(tab.name === "second") {
          this.getFollowingState()
        } else if (tab.name === "third") {
          this.getHotNotices()
        }
      },
      getReviewList() {
        let noticeParams = new URLSearchParams();
        noticeParams.append('u', this.accountId);
        this.axios.get('/review/getReviewList', { params: noticeParams})
          .then((res) => {
            this.review_exhibition = res.data;
            this.review_exhibition.forEach(item => {
              item.ifShow = (this.accountId !== item.authorID);
            })
            console.log(this.review_exhibition)
          })
          .catch((err) => {
            console.log(err);
          })
      },
      getFollowingState() {
        let loadingInstance =  Loading.service({
          text: '正在加载中...',
        });
        this.axios.get('/follow/getFollowingState', {
          params: { id: this.accountId}
        }).then((res) => {
          this.followingState = res.data
          loadingInstance.close();
        })
          .catch((error) => {
            console.log(error)
          });
      },
      getNotices() {
        let loadingInstance =  Loading.service({
          text: '正在加载中...',
        });
        let noticeParams = new URLSearchParams();
        noticeParams.append('accountId', this.accountId);
        this.axios.get('/mNotice/getNotices', { params: noticeParams})
          .then((res) => {
            this.exhibition = res.data;
            loadingInstance.close();
          })
          .catch((error) => {
            console.log(error)
          })
      },
      getHotNotices() {
        let loadingInstance =  Loading.service({
          text: '正在加载中...',
        });
        this.axios.get('/mNotice/getHotTemp')
          .then((res) => {
            this.hotNotices = res.data
            loadingInstance.close();
          })
          .catch((error) => {
            console.log(error)
          });
      },
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
