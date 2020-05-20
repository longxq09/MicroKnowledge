<template>
  <div class="notice">
    <div class="noice_title">
      {{type_name}} | <el-button type="text" @click="dialogTableVisible = true">{{title}}</el-button>

      <el-dialog title="详情" :visible.sync="dialogTableVisible">
        <v-detail v-bind:noticeId="id"></v-detail>
      </el-dialog>
      <el-tag class="keyword" v-if="user">{{state}}</el-tag>
      <div v-if="login" style="display: inline-block">
      </div>
    </div>
    <nobr style="font-weight: 600;margin-left: 10px;">{{authorName}}</nobr>
    <el-tag :key="tag" v-for="tag in keywordTag" class="keyword" v-if="has_keyword">{{tag}}</el-tag>
    <div class="main_text">{{summary}}</div>
    <div v-if="login">
      <v-like v-bind:accountId="accountId" v-bind:id="id" v-if="toShow">
      </v-like>
      <v-favorite v-bind:accountId="accountId" v-bind:id="id" v-if="toShow">
      </v-favorite>
      <v-follow v-bind:accountId="accountId" v-bind:id="id" v-bind:authorId="authorId" v-if="toShow">
      </v-follow>
      <el-button class="bottom_tag" v-if="modify" @click="toModify">编辑</el-button>
      <el-button class="bottom_tag" v-if="modify" @click="toDelete">删除</el-button>
      <el-button class="bottom_tag" v-if="review" @click="toReview">评审</el-button>

        <el-button class="bottom_tag" @click="reviewResult(1)"  v-if="need_review&&review"> 通过 </el-button>
        <el-button class="bottom_tag" @click="reviewResult(-1)"  v-if="need_review&&review">不通过</el-button>

        <el-button class="bottom_tag" type="primary" v-if="review_pass&&!need_review&&review" @click="cancle(1)">通过{{pass_num}}</el-button>
        <el-button class="bottom_tag" type="info" v-if="!review_pass&&!need_review&&review"> 通过{{pass_num}} </el-button>
        <el-button class="bottom_tag" type="info" v-if="review_pass&&!need_review&&review">不通过{{unpass_num}}</el-button>
        <el-button class="bottom_tag" type="primary" v-if="!review_pass&&!need_review&&review" @click="cancle(-1)">不通过{{unpass_num}}</el-button>
    </div>
  </div>
</template>

<script>
  import vFollow from './Follow.vue'
  import vLike from './Like.vue'
  import vFavorite from './Favorite'
  import vDetail from './Detail.vue'
  export default {
    name: "Notice",
    props: {
      state: {
        type: String,
        default: "评审中"
      },
      accountId: {
        type: String,
        default: localStorage.getItem("accountId")
      },
      modify: {
        type: Boolean,
        default: false
      },
      id: {
        type: Number,
        default: -1
      },
      type: {
        type: Number,
        default: 1
      },
      authorId: {
        type: Number,
        default: -1
      },
      authorName: {
        type: String,
        default: ''
      },
      keywords: {
        type: String,
        default: ''
      },
      title: {
        type: String,
        default: ''
      },
      summary: {
        type: String,
        default: ""
      },
      user: {
        type: Boolean,
        default: false
      },
      review: {
        type: Boolean,
        default: false
      },
      judge: {
        type: Number,
        default: 0
      }
    },

    data() {
      return {
        keywordTag: [],
        type_name: '',
        toShow: false,
        login: false,
        has_keyword: true,
        need_review: false,
        review_pass:true,
        review_type:0,
        pass_num:0,
        unpass_num:0,
        dialogTableVisible: false,
        dialogFormVisible: false,

      }
    },
    components: {
      vFollow,
      vLike,
      vFavorite,
      vDetail
    },
    methods: {
      async getReviewInfo(){
        var params2 = new URLSearchParams();
        params2.append('userId', localStorage.getItem("accountId"));
        params2.append('noticeId', this.id);
        try {
          let res = await this.axios.post('/review/userToReview', params2);
          this.review_type = res.data.type;
          if (this.review_type == 0) {
            this.need_review = true;
          }
          if (this.review_type == 1) {
            this.need_review = false;
            this.review_pass = true;
          }
          if (this.review_type == -1) {
            this.need_review = false;
            this.review_pass = false;
          }
          this.pass_num = res.data.pass_num;
          this.unpass_num = res.data.unpass_num;
        } catch (err) {
          console.log(err);
        }
      },
      init() {
        this.keywordTag = this.keywords.split('-');
        if (this.keywords.length == 0) {
          this.has_keyword = false;
        }
        if (this.type === 1) {
          this.type_name = "微证据";
        } else {
          this.type_name = "微猜想";
        }
        this.login = localStorage.getItem("accountId") != ""
        this.toShow = !(this.user || this.review)
        if (this.user) {
          this.modify = (this.judge == 0);
          if (this.judge == 1) {
            this.state = "已通过";
          } else if (this.judge == 0) {
            this.state = "评审中";
          } else {
            this.state = "未通过";
          }
        }
      },
      toReview() {
        this.$router.push({
          path: '/review/',
          query: {
            id: this.id
          }
        });
      },
      toModify() {
        if (this.type == 1) {
          this.$router.push({
            path: '/modify_mevid/',
            query: {
              id: this.id
            }
          });
        } else {
          this.$router.push({
            path: '/modify_mguess/',
            query: {
              id: this.id
            }
          });
        }
      },
      toDetail() {
        this.$router.push({
          path: '/detail/',
          query: {
            id: this.id
          }
        });
      },
      toDelete() {
        this.$confirm('确认删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          this.sureDelete();
        }).catch(() => {});
      },
      sureDelete() {
        var params = new URLSearchParams();
        params.append('id', this.id);
        this.axios.post('/mNotice/deleteNotice', params)
          .then((res) => {
            var remindTitle = res.data === 0 ? '删除微证据成功' : '删除微证据失败';
            var remindContent = res.data === 0 ? '删除微证据成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            });
            if (res.data === 0) {
              this.$router.push('/homepage');
            }
          })
          .catch((error) => {
            console.log(error);
          });
      },

      reviewResult(type) {
        this.need_review = false;
        this.review_pass = (type == 1);

        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        params.append('noticeId', this.id);
        params.append('type', type);
        this.axios.post('/review/doReview', params)
          .then((res) => {
            this.pass_num = res.data.pass_num;
            this.unpass_num = res.data.unpass_num;
            this.review_type = res.data.type;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      cancle(type) {
        this.need_review = true;

        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        params.append('noticeId', this.id);
        params.append('type', 0);
        this.axios.post('/review/doReview', params)
          .then((res) => {
            this.pass_num = res.data.pass_num;
            this.unpass_num = res.data.unpass_num;
            this.review_type = res.data.type;
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
    mounted() {
      this.init();
      if(this.review){
        this.getReviewInfo();
      }
    },
  }
</script>

<style>
  .notice {
    /* border: 1px solid #409EFF; */
    width: 99%;
    /* border-radius: 10px; */
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .24), 0 0 6px rgba(0, 0, 0, .10);
    padding-top: 7px;
    padding-bottom: 7px;
    margin-bottom: 10px;
    background-color: white;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 20px;
    /*font-weight: 500;*/
    /*display: inline-block;*/
    margin: 10px;
    margin-bottom: 0px;
    width: 100%;
  }

  .main_text {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    margin: 10px;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
    margin-bottom: 3px;
  }

  .bottom_tag {
    margin-left: 10px;
    line-height: 8px;
    height: 32px;
  }
</style>
