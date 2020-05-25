<template>
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
</template>

<script>
  export default {
    name: "Like",
    props: {
      id: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        like: false,
        likeNum: 0,
        accountId: sessionStorage.getItem("accountId")
      }
    },
    mounted() {
      this.accountId = sessionStorage.getItem("accountId")
      this.getLikeInfo()
    },
    methods: {
      getLikeInfo() {
        this.axios.get('/like/checkLikeState', {
          params: {
            id: this.accountId,
            noticeId: this.id
          }
        })
          .then((res) => {
            if (res.data.isLike === 1) {
              this.like = true
            } else {
              this.like = false
            }
            this.likeNum = res.data.like_num
          })
          .catch((error) => {
            console.log(error)
          })
      },

      addLike() {
        this.like = true
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('noticeId', this.id)
        this.axios.post('/like/likeThis', params)
          .then((res) => {
            if (res.data === 0) {
              this.likeNum += 1
              this.$message({
                message: '点赞成功',
                type: 'success'
              });
            } else {
              this.$message.error('点赞失败');
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancelLike() {
        this.like = false
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('noticeId', this.id)
        this.axios.post('/like/cancelLike', params)
          .then((res) => {
            if (res.data === 0) {
              this.likeNum -= 1
              this.$message({
                message: '取消成功',
                type: 'success'
              });
            } else {
              this.$message.error('取消失败');
            }
          })
          .catch((error) => {
            console.log(error)
          })
      }

    }
  }
</script>

<style>
  .bottom_tag {
    margin-left: 10px;
    margin-bottom: 10px;
    line-height: 8px;
    height: 32px;
  }
</style>
