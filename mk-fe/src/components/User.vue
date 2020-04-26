<template>
	<el-container>
		<el-header>
			<v-head v-bind:user=true></v-head>
		</el-header>
		<el-main>
			<el-tabs v-model="activeName" style="margin-left: 15%;margin-right: 15%;">
        <el-tab-pane label="推荐" name="first">
          <v-notice></v-notice>
          <v-notice></v-notice>
        </el-tab-pane>
        <el-tab-pane label="关注" name="second">
        </el-tab-pane>
        <el-tab-pane label="热榜" name="third">
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
        activeName: 'first'
      }
    },
		components: {
			vHead,
			vFooter,
			vNotice
		},
		methods: {

		}

  }
</script>

<style scoped>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

</style>
