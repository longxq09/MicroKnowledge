<template>
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
</template>

<script>
  export default {
    name: "Favorite",
    props: {
      id: {
        type: Number,
        default: 0
      }
    },
    data() {
      return {
        favorite: false,
        accountId: sessionStorage.getItem("accountId")
      }
    },
    mounted() {
      this.accountId = sessionStorage.getItem("accountId")
      this.getFavoriteInfo()
    },
    methods: {
      getFavoriteInfo() {
        this.axios.get('/favorite/checkFavorite', {
          params: {
            id: this.accountId,
            noticeID: this.id
          }
        })
          .then((res) => {
            if (res.data == 0) {
              this.favorite = true
            } else {
              this.favorite = false
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },

      addFavorite() {
        this.favorite = true
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('noticeID', this.id)
        this.axios.post('/favorite/addFavorite', params)
          .then((res) => {
            if (res.data === 0) {
              this.$message({
                message: '收藏成功',
                type: 'success'
              });
            } else {
              this.$message.error('收藏失败');
            }
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancelFavorite() {
        this.favorite = false
        var params = new URLSearchParams()
        params.append('id', this.accountId)
        params.append('noticeID', this.id)
        this.axios.post('/favorite/deleteFavorite', params)
          .then((res) => {
            if (res.data === 0) {
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
