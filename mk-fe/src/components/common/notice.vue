<template>
  <div class="notice">
    <div class="noice_title">
      {{type_name}} | {{title}}
      <el-button class="bottom_tag" @click="toDetail">详情</el-button>
    </div>
    <nobr style="font-weight: 600;margin-left: 10px;">{{authorName}}</nobr>
    <el-tag :key="tag" v-for="tag in keywordTag" class="keyword">{{tag}}</el-tag>
    <div class="main_text">{{summary}}</div>
    <div style="display: inline-block; vertical-align: bottom">
      <el-button v-if="like"
                 icon="el-icon-sugar"
                 type="primary"
                 class="bottom_tag"
                 @click="cancelLike">{{likeNum}}
      </el-button>
      <el-button v-else
                 class="bottom_tag"
                 icon="el-icon-sugar"
                 @click="addLike">{{likeNum}}
      </el-button>
    </div>
    <div style="display: inline-block; vertical-align: bottom">
      <el-button v-if="favorite"
                 icon="el-icon-star-on"
                 type="primary"
                 class="bottom_tag"
                 @click="cancelFavorite">
      </el-button>
      <el-button v-else
                 class="bottom_tag"
                 icon="el-icon-star-off"
                 @click="addFavorite">
      </el-button>
    </div>
    <div style="display: inline-block">
      <el-button v-if="follow"
                 class="bottom_tag"
                 type="primary"
                 @click="cancelFollow">取消关注
      </el-button>
      <el-button v-else
                 class="bottom_tag"
                 @click="addFollow">关注作者
      </el-button>
    </div>
    <el-button class="bottom_tag" v-if="review" @click="toReview">评审</el-button>
    <el-button class="bottom_tag" v-if="user" @click="toModify">编辑</el-button>
    <el-button class="bottom_tag" v-if="user" @click="toDelete">删除</el-button>
  </div>
</template>

<script>
  export default {
    name: "Notice",
    props: {
      id: {
        type: Number,
        default: 0
      },
      type: {
        type: Number,
        default: 1
      },
      authorId: {
        type: Number,
        default: 0
      },
      authorName: {
        type: String,
        default: 'uc主编'
      },
      keywords: {
        type: String,
        default: 'machine learning-c++从入门到入土'
      },
      title: {
        type: String,
        default: '震惊！冯如杯要写不完了？！'
      },
      summary: {
        type: String,
        default: "冯如杯写不完是怎么回事呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦"
      },
      user: {
        type: Boolean,
        default: false
      },
    },

    data() {
      return {
        keywordTag: [],
        type_name: '',
        review: true,
        favorite: false,
        follow: false,
        like: false,
        likeNum: 0
      }
    },
    methods: {
      toReview() {
        this.$router.push({
          path: '/review/',
          query: {
            id: this.id
          }
        });
      },

      toModify() {
        this.$router.push({
          path: '/modify_mevid/',
          query: {
            id: this.id
          }
        });
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

      getNoticeInfo() {
        this.getFavoriteInfo()
        this.getFollowInfo()
        this.getLikeInfo()
      },

      getFavoriteInfo() {
        this.axios.get('/favorite/checkFavorite', {
          params: {
            id: localStorage.getItem("accountId"),
            noticeID: this.id
          }
        })
          .then((res) => {
            if (res.data == 0) {
              this.favorite = true
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },

      getFollowInfo() {
        this.axios.get('/follow/checkFollow', {
          params: {
            id: localStorage.getItem("accountId"),
            followingID: this.authorId
          }
        })
          .then((res) => {
            if (res.data == 0) {
              this.follow = true
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },

      getLikeInfo() {
        this.axios.get('/like/checkLikeState', {
          params: {
            id: localStorage.getItem("accountId"),
            noticeId: this.id
          }
        })
          .then((res) => {
            if (res.data.isLike == 1) {
              this.like = true
            }
            this.likeNum = res.data.like_num
          })
          .catch((error) => {
            console.log(error)
          })
      },

      addFavorite() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('noticeID', this.id)
        this.axios.post('/favorite/addFavorite', params)
          .then((res) => {
            this.favorite = true
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancelFavorite() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('noticeID', this.id)
        this.axios.post('/favorite/deleteFavorite', params)
          .then((res) => {
            this.favorite = false
          })
          .catch((error) => {
            console.log(error)
          })
      },

      addFollow() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('followingID', this.authorId)
        this.axios.post('/follow/addFollow', params)
          .then((res) => {
            this.follow = true
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancelFollow() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('followingID', this.authorId)
        this.axios.post('/follow/deleteFollow', params)
          .then((res) => {
            this.follow = false
          })
          .catch((error) => {
            console.log(error)
          })
      },

      addLike() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('noticeId', this.id)
        this.axios.post('/like/likeThis', params)
          .then((res) => {
            this.like = true
            this.likeNum += 1
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancelLike() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('noticeId', this.id)
        this.axios.post('/like/cancelLike', params)
          .then((res) => {
            this.like = false
            this.likeNum -= 1
          })
          .catch((error) => {
            console.log(error)
          })
      }
    },

    mounted() {
      this.keywordTag = this.keywords.split('-')
      if (this.type == 1) {
        this.type_name = "微证据"
      } else {
        this.type_name = "微猜想"
      }
      this.getNoticeInfo()
    }
  }
</script>

<style>
  .notice {
    border: 1px solid #409EFF;
    width: 99%;
    border-radius: 10px;
    margin-bottom: 10px;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 20px;
    font-weight: 500;
    display: inline-block;
    margin: 10px;
    margin-bottom: 5px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .main_text {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    margin: 10px;
    margin-bottom: 1px;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
  }

  .bottom_tag {
    margin: 12px;
    margin-right: 0;
    line-height: 8px;
    height: 32px;
  }
</style>
