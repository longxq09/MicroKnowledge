<template>
	<el-form :model="dataForm" :rules="rules">
		<el-form-item label="邮箱" prop="email">
			<el-input type="text" v-model="dataForm.email" placeholder="请输入账号"></el-input>
		</el-form-item>
		<el-form-item label="密码" prop="password">
			<el-input type="password" v-model="dataForm.password" placeholder="请输入密码"></el-input>
		</el-form-item>
		<el-button @click="login">登录</el-button>
	</el-form>
</template>

<script>
  export default {
    name: "Login",
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else if (value.length < 6) {
          callback(new Error('密码长度至少6位'))
        } else {
          if (this.forgetForm.repeat !== '') {
            this.$refs.forgetForm.validateField('repeat')
          }
          callback()
        }
      }
      var validateRepeat = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.forgetForm.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }
      return {
        dataForm: {name: '', password: '', email: ''},
        rules: {
          username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}],
          email: [{required: true, message: '邮箱不可为空', trigger: 'blur'}],
        },
        active: 0,
      }
    },
    methods: {
      login () {
        var params = new URLSearchParams();
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
				this.axios.post('/user/login', params)
          .then((res)=>{
            console.log(res.data.code)
            if (res.data.code === "0") {
              const h = this.$createElement;
              this.$notify({
                title: '登陆成功',
                message: h('i', { style: 'color: teal'}, '欢迎来到微知！')
              });
              sessionStorage.setItem("accountId", res.data.id)
              sessionStorage.setItem("email", this.dataForm.email)
              this.$router.push("/homepage");
            } else if(res.data.code === "1" || res.data.code === "2"){
              let remindTitle = '登陆失败'
              let remindContent = '账号或密码错误'
              if (res.data.code === 2) {
                remindContent = '未进行邮箱验证，请前往邮箱查看验证邮件！'
              }
              this.$alert(remindContent, remindTitle, {
                confirmButtonText: '确定'
              });
            } else {
              sessionStorage.setItem("accountId", res.data.id)
              sessionStorage.setItem("email", this.dataForm.email)
              sessionStorage.setItem("admin", "true")
              this.$router.push('/admin/message')
              const h = this.$createElement;
              this.$notify({
                title: '登陆成功',
                message: h('i', { style: 'color: teal'}, '欢迎来到微知管理界面！')
              });
            }
          })
          .catch((res)=>{
            console.log(res.message);
          });
      },
    }
  }
</script>
<style scoped>
  .el-form-item {
    margin-top: 0;
  }
</style>
