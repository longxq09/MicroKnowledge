<template>
	<el-container>
		<el-header>
			<v-head v-bind:user=true></v-head>
		</el-header>
		<el-main>
      <div style="display: inline-block; width: 300px; margin: 20px; vertical-align: top">
        <el-image class="image" :src="src"></el-image>
        <p>{{name}}</p>
        <p>{{email}}</p>
      </div>
			<el-tabs v-model="activeName" style="display: inline-block; width: 680px">
        <el-tab-pane label="我的关注" name="first">
          <div class="follow" :key="index" v-for="(value,index) in following">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我的粉丝" name="second">
          <div class="follow" :key="index" v-for="(value,index) in follower">
            <el-avatar style="display: inline-block">{{value.name}}</el-avatar>
            <div style="display: inline-block">
              <div>{{value.name}}</div>
              <div>{{value.email}}</div>
            </div>
            <el-divider></el-divider>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我的收藏" name="third">
          <v-notice :key="value.id" v-for="(value,index) in favorite"
            v-bind:id="value.id"
            v-bind:type="value.type"
            v-bind:authorName="value.authorName"
            v-bind:keywords="value.keywords"
            v-bind:title="value.title"
            v-bind:summary="value.summary">
          </v-notice>
        </el-tab-pane>
      </el-tabs>
		</el-main>
		<v-footer></v-footer>
	</el-container>
</template>

<script>
import vHead from './common/Header.vue';
import vFooter from './common/Footer.vue';
import vNotice from './common/Notice.vue';
  export default {
    name: "User",
    data() {
      return {
        name: '',
        email: '',
        src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
        activeName: 'first',
        following: Array,
        follower: Array,
        favorite: Array
      };
    },
		components: {
			vHead,
			vFooter,
			vNotice
		},
		mounted() {
      this.getUserInfo()
		  this.getFollow()
      this.getFavorite()
		},
		methods: {
      getUserInfo() {
        this.axios.get('/user/info', {
          params: {id: localStorage.getItem("accountId")}
          }).then((res) => {
            this.name = res.data.name
            this.email = localStorage.getItem("email")
          })
          .catch((error) => {
            console.log(error)
          })
      },
      getFollow() {
        this.axios.get('/follow/getFollowing', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
            this.following = res.data
          })
          .catch((error) => {
            console.log(error)
          })
        this.axios.get('/follow/getFollower', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
            this.follower = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      },
      getFavorite() {
        this.axios.get('/favorite/getFavoriteList', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
          this.favorite = res.data
        })
          .catch((error) => {
            console.log(error)
          })
      }
		}

  }
</script>

<style scoped>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

  .image {
    vertical-align: top;
    margin: 0;
  }

  .follow {
    margin: 12px;
  }
</style>
