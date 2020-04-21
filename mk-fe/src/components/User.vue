<template>
	<el-container>
		<el-header>
			<div class="title">微知 MicroKnowledge</div>
			<el-badge class="item">
				<el-button @click="userInfo">个人信息</el-button>
			</el-badge>
			<div class="avator">
				<el-avatar> user </el-avatar>
			</div>
		</el-header>
		<el-main>
			
		</el-main>
		<el-footer>copyright: Jiayi</el-footer>
	</el-container>
</template>

<script>
  export default {
    name: "User",
    data() {
      return {
				activeName: 'first',
        dataForm: {username: 'admin', password: '123456'},
				fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        rules: {
          username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}]
        }
      }
    },
		methods: {
      userInfo() {
				this.$router.push('/userinfo');
			}
		}
		
  }
</script>

<style>
  .el-header{
    background-color: #FFFFFF;
    line-height: 1.5;
  }
  
	.title {
		color: #409EFF;
    text-align: left;
		font-size: 30px;
		font-weight: 800;
		display: inline-block;
	}
	.el-footer {
		text-align: center;
	}
	
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }
	
	.item {
		float: right;
		margin: 10px;
	}
	
	.avator {
		display: inline-block;
		float: right;
		margin-top: 5px;
	}
</style>
