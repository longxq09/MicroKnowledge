<template>
	<el-container>
		<el-header>
			<v-head></v-head>
		</el-header>
		<el-main>
			<div style="height: 300px">
				<el-image
					style="width: 200px; height: 200px; display: inline-block; margin-left: 20%"
					:src="url"
					:fit="fit"></el-image>
				<el-tabs v-model="activeName" style="display: inline-block; margin-left: 15%">
					<el-tab-pane label="登录" name="first">
						<el-form :model="dataForm" :rules="rules">
							<el-form-item label="邮箱" prop="email">
								<el-input type="text" v-model="dataForm.email" placeholder="请输入账号"></el-input>
							</el-form-item>
							<el-form-item label="密码" prop="password">
								<el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
							</el-form-item>
							<el-button @click="login">登录</el-button>
							<el-alert
								v-show="loginFail"
								title="邮箱或密码错误"
								type="error"
								center
								show-icon>
							</el-alert>
						</el-form>
					</el-tab-pane>
					<el-tab-pane label="注册" name="second">
						<el-form :model="dataForm" :rules="rules">
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
				loginFail: false,
				activeName: 'first',
        dataForm: {username: 'admin', password: '123456', email: 'test@mail.com'},
				fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
				fit: 'fill',
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
            if (res.data.code==0){
              this.$router.push("/user");
            }
						else {
							this.loginFail = true;
						}
          })
          .catch((res)=>{
						this.loginFail = true;
            console.log(res.message);
          });
      },
			register () {
        var params = new URLSearchParams();
        params.append('name', this.dataForm.username);
        params.append('password',this.dataForm.password);
        this.axios.post('/user/login', params)
          .then((res)=>{
            if (res.data.code==0){
              this.$router.push("/user");
            }
          })
          .catch((res)=>{
						this.loginFail = true;
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
</style>
