<template>
	<div class="header">
		<div class="title">{{title}}</div>
		<el-badge class="button" v-if="user">
			<el-button @click="toUserInfo">个人信息</el-button>
		</el-badge>
		<el-badge class="button" v-if="userinfo">
			<el-button @click="toUser">回到主页</el-button>
		</el-badge>
		<div class="photo" v-if="user">
			<el-avatar> user </el-avatar>
		</div>
	</div>
</template>

<script>
	export default {
		name: "Header",
		props: {
			title: {
				type: String,
				default: '微知 MicroKnowledge'
			},	
			user: {
				type: Boolean,
				default: false
			},
			userinfo: {
				type: Boolean,
				default: false
			}
		},
		methods: {
			toUserInfo() {
				console.log("miaomiaomiao");
				this.$router.push('/userinfo');
			},
			toUser() {
				this.$router.push('/user');
			}
		}
	}
</script>

<style>
	.header{
    background-color: #FFFFFF;
    line-height: 1.5;
		width: 100%;
  }
  
	.title {
		color: #409EFF;
    text-align: left;
		font-size: 30px;
		font-weight: 800;
		display: inline-block;
	}
	
	.button {
		float: right;
		margin: 10px;
	}
	
	.photo {
		display: inline-block;
		float: right;
		margin-top: 5px;
	}
	
</style>