<template>
	<el-form :model="dataForm" :rules="rules">
		<el-form-item label="邮箱" prop="email">
			<el-input type="text" v-model="dataForm.email" placeholder="请输入账号"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="password">
			<el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
		</el-form-item>
		<el-button @click="login">登录</el-button>
    <el-button type="text" @click="forgetPassVisible = true">忘记密码？</el-button>
    <el-dialog title="找回密码" :visible.sync="forgetPassVisible" :append-to-body="true">
      <el-steps :active="active" finish-status="success">
        <el-step title="输入邮箱"></el-step>
        <el-step title="输入邮箱验证码"></el-step>
        <el-step title="重置密码"></el-step>
      </el-steps>
      <div v-if="active==0">邮箱：<el-input v-model="forgetEmail"></el-input></div>
      <div v-if="active==1">验证码：<el-input v-model="forgetCode"></el-input></div>
      <div v-if="active==2">新密码：<el-input v-model="forgetPass"></el-input></div>
      <div v-if="active==2">重复密码：<el-input v-model="forgetRePass"></el-input></div>
      <el-button style="margin-top: 12px;" @click="next" v-if="active<2">下一步</el-button>
      <el-button style="margin-top: 12px" @click="submit" v-if="active==2">提交</el-button>
    </el-dialog>
	</el-form>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        dataForm: {name: '', password: '', email: ''},
        rules: {
          username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}],
          email: [{required: true, message: '邮箱不可为空', trigger: 'blur'}],
        },
        forgetPassVisible: false,
        active: 0,
        forgetEmail: '',
        forgetCode: '',
        forgetPass: '',
        forgetRePass: '',
      }
    },
		methods: {
      next() {
        this.active++
      },
      submit() {
        this.forgetPassVisible = false;
        this.active = 0
        const h = this.$createElement;
        this.$notify({
          title: '重置密码成功！',
          message: h('i', { style: 'color: teal'}, '登录试试看吧！')
        });
      },
      login () {
        var params = new URLSearchParams();
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
        var remindTitle = '登陆失败'
        var remindContent = '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧'
				this.axios.post('/user/login', params)
          .then((res)=>{
            if (res.data.code == 0) {
              remindTitle = '登陆成功'
              remindContent = '欢迎来到微知！'
              localStorage.setItem("accountId", res.data.id)
              localStorage.setItem("email", this.dataForm.email)
              this.$router.push("/homepage");
            } else if (res.data.code == 2) {
              remindContent = '未进行邮箱验证，请前往邮箱查看验证邮件！'
            }
						this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            });
          })
          .catch((res)=>{
            console.log(res.message);
          });
      },
    }
  }
</script>
