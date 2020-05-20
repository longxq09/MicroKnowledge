<template>
  <div style="display: inline-block">
    <el-button v-if="follow"
               class="bottom_tag"
               type="primary"
               @click="cancelFollow">取消关注
    </el-button>
    <el-button v-if="!follow"
               class="bottom_tag"
               @click="addFollow">关注作者
    </el-button>
  </div>
</template>

<script>
  export default {
    name: "Follow",
    props: {
      id: {
        type: Number,
        default: 0
      },
      authorId: {
        type: Number,
        default: 0
      },
    },
    data() {
      return {
        follow: false,
        accountId: sessionStorage.getItem("accountId")
      }
    },
    mounted() {
      this.accountId = sessionStorage.getItem("accountId")
      this.getFollowInfo()
    },
    methods: {
      getFollowInfo() {
        this.axios.get('/follow/checkFollow', {
          params: {
            id: this.accountId,
            followingID: this.authorId
          }
        })
          .then((res) => {
            if (res.data == 0) {
              this.follow = true
            } else {
              this.follow = false
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },
      addFollow() {
        this.follow = true
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('followingID', this.authorId)
        this.axios.post('/follow/addFollow', params)
          .then((res) => {
            this.$router.go(0)
          })
          .catch((error) => {
            console.log(error)
          })
      },
      cancelFollow() {
        this.follow = false
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('followingID', this.authorId)
        this.axios.post('/follow/deleteFollow', params)
          .then((res) => {
            this.$router.go(0)
          })
          .catch((error) => {
            console.log(error)
          })
      },
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
