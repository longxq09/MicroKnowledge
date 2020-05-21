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
        var params = new URLSearchParams()
        params.append('id', this.accountId)
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
        params.append('id', this.accountId)
        params.append('noticeID', this.id)
        this.axios.post('/favorite/deleteFavorite', params)
          .then((res) => {
            this.favorite = false
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
