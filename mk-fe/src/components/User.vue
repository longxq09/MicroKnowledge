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
        follower: Array
      };
    },
		components: {
			vHead,
			vFooter,
			vNotice
		},
		mounted() {
		  this.getFollow()
		},
		methods: {
      getFollow() {
        this.axios.get('/getFollowing')
          .then((res) => {
            this.following = res.data
            console.log(res.data)
          })
          .catch((error) => {
            console.log(error)
          })
        this.axios.get('/getFollower')
          .then((res) => {
            this.follower = res.data
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
