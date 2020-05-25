<template>
  <div style="width: 99%;">
    <div>
      <i class="el-icon-s-custom"> {{userInfo.name}}</i>
      <i v-if="userInfo.sex==='male'" class="el-icon-male"></i>
      <i v-if="userInfo.sex==='female'" class="el-icon-female"></i>
    </div>
    <div>
      <i class="el-icon-s-promotion"> {{userInfo.email}}</i>
    </div>
    <div>
      <i class="el-icon-school"> {{userInfo.education}}</i>
    </div>
    <div>
      <i class="el-icon-office-building"> {{userInfo.works}}</i>
    </div>
    <div>
      <i class="el-icon-sugar"> interest</i>
      <v-show-topic v-bind:str="userInfo.interest"></v-show-topic>
    </div>
    <div>
      <i class="el-icon-medal"> expertise</i>
      <v-show-topic v-bind:str="userInfo.expertise"></v-show-topic>
    </div>
  </div>
</template>
<script>
  import vShowTopic from './ShowTopic'
  export default {
    name: "UserInfoBoard",
    props: {
      id: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        userInfo: {}
      }
    },
    components: {
      vShowTopic
    },
    mounted() {
      this.userInfo = {}
      this.getUserInfo()
    },
    watch: {
      $route() {
        if (this.$route.query.accountId) {
          this.id = this.$route.query.accountId
        } else {
          this.id = sessionStorage.getItem("accountId")
        }
      },
      id() {
        this.getUserInfo()
      },
    },
    methods: {
      getUserInfo() {
        this.axios.get('/user/info', {
          params: {
            id: this.id
          }
        }).then((res) => {
          this.userInfo = res.data
        })
          .catch((error) => {
            console.log(error)
          })
      },
    }
  }
</script>
