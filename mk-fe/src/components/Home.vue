<template>
	<el-container>
		<el-header>
			<v-head></v-head>
		</el-header>
		<el-main>
			<div style="height: 360px">
				<el-image class="image" :src="url" :fit="fit"></el-image>
				<el-tabs v-model="activeName" style="display: inline-block; margin-left: 15%; height: 300px">
					<el-tab-pane label="登录" name="first">
						<el-form :model="dataForm" :rules="rules">
							<el-form-item label="邮箱" prop="email">
								<el-input type="text" v-model="dataForm.email" placeholder="请输入账号"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="password">
								<el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
							</el-form-item>
							<el-button @click="login">登录</el-button>
						</el-form>
					</el-tab-pane>
					<el-tab-pane label="注册" name="second">
						<el-form :model="dataForm" :rules="rules">
							<el-form-item label="昵称" prop="name">
								<el-input type="text" v-model="dataForm.name" placeholder="请输入昵称"></el-input>
							</el-form-item>
							<el-form-item label="邮箱" prop="email">
								<el-input type="text" v-model="dataForm.email" placeholder="请输入邮箱"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="password">
								<el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
							</el-form-item>
							<el-button @click="register">注册</el-button>
							<div class="remind">将对您的邮箱进行验证</div>
						</el-form>
					</el-tab-pane>
				</el-tabs>
			</div>
			<div>

			</div>
		</el-main>
		<v-footer></v-footer>
	</el-container>
</template>

<script>
import vHead from './common/Header.vue';
import vFooter from './common/Footer.vue';
  export default {
    name: "Home",
    data() {
      return {
        fit: 'fill',
        activeName: 'first',
        dataForm: {name: '', password: '', email: ''},
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        rules: {
          username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}],
          email: [{required: true, message: '邮箱不可为空', trigger: 'blur'}],
        }
      }
    },
		components: {
			vHead,
			vFooter
		},
		methods: {
      login () {
        var params = new URLSearchParams();
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
				this.axios.post('/user/login', params)
          .then((res)=>{
            var remindType = res.data.code == 0 ? 'success' : 'info';
            var remindTitle = res.data.code == 0 ? '登陆成功' : '登陆失败';
            var remindContent = res.data.code == 0 ? '欢迎来到微知！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
            if (res.data.code==0){
              this.$router.push("/user");
            }
						this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: remindType,
                  message: remindTitle
                });
              }
            });
          })
          .catch((res)=>{
						this.loginFail = true;
            console.log(res.message);
          });
      },
			register () {
        var params = new URLSearchParams();
        params.append('name', this.dataForm.username);
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
        this.axios.post('/user/register', params)
          .then((res)=>{
            if (res.data.code==0){
              this.$router.push("/home");
            }
          })
          .catch((res)=>{
            console.log(res.data.message);
          });
      }
    }

  }
</script>

<style>

  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

	.remind {
		display: inline-block;
		font-size: 8px;
		color: red;
		text-decoration: underline;
	}

	.image {
    width: 200px;
    height: 200px;
    display: inline-block;
    margin-left: 20%;
    vertical-align: top;
    padding-top: 32px;
  }
</style>
