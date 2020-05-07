<template>
	<el-container>
		<el-header>
			<v-head v-bind:user=true></v-head>
		</el-header>
		<el-main>
      <div class="image">
        <el-image :src="src"></el-image>
      </div>
			<el-tabs v-model="activeName">
        <el-tab-pane label="我的关注" name="first">
        </el-tab-pane>
        <el-tab-pane label="我的粉丝" name="second">
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
		  this.getFollow()
      this.getFavorite()
		},
		methods: {
      getFollow() {
        this.axios.get('/getFollowing', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
            this.following = res.data
            console.log(res.data)
          })
          .catch((error) => {
            console.log(error)
          })
        this.axios.get('/getFollower', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
            this.follower = res.data
            console.log(this.follower)
          })
          .catch((error) => {
            console.log(error)
          })
      },
      getFavorite() {
        this.axios.get('/getFavoriteList', {
          params: { id: localStorage.getItem("accountId")}
        }).then((res) => {
          this.favorite = res.data
          console.log(this.follower)
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
    width: 240px;
  }

</style>
