<template>
	<el-form :model="dataForm" :rules="rules" autocomplete="off">
    <el-form-item label="昵称" prop="name">
      <el-input type="text" v-model="dataForm.name"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input type="text" v-model="dataForm.email"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="dataForm.password"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="repeat">
      <el-input type="password" v-model="dataForm.repeat"></el-input>
    </el-form-item>
    <el-button @click="register">注册</el-button>
    <div class="remind">将对您的邮箱进行验证</div>
  </el-form>
</template>

<script>
  export default {
    name: "Register",
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else if (value.length < 6) {
          callback(new Error('密码长度至少6位'))
        } else {
          if (this.dataForm.repeat !== '') {
            this.$refs.dataForm.validateField('repeat')
          }
          callback()
        }
      }
      var validateRepeat = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value != this.dataForm.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }
      return {
        dataForm: {name: '', password: '', email: '', repeat: ''},
        rules: {
          name: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, validator: validatePass, trigger: 'blur'}],
          repeat: [{required: true, validator: validateRepeat, trigger: 'blur' }],
          email: [{required: true, message: '邮箱不可为空', trigger: 'blur'}],
        },
      }
    },
		methods: {
      register () {
        if (this.dataForm.password.length < 6) {
          let remindTitle = '密码长度过短！'
          let remindContent = '密码长度最少6位，请重新输入！'
          this.$alert(remindContent, remindTitle, {
            confirmButtonText: '确定'
          });
          return
        }
        if (this.dataForm.password !== this.dataForm.repeat) {
          let remindTitle = '确认密码出错！'
          let remindContent = '两次密码输入不一致，请重新输入！'
          this.$alert(remindContent, remindTitle, {
            confirmButtonText: '确定'
          });
          return
        }
        let params = new URLSearchParams();
        params.append('name', this.dataForm.name);
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
        this.axios.post('/user/register', params)
          .then((res)=>{
            var remindTitle = res.data.code == 0 ? '注册成功' : '注册失败';
            var remindContent = res.data.code == 0 ? '请进行邮箱验证后登录' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
            if (res.data.code==0){
              this.$router.push("/");
            }
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定',
            });
          })
          .catch((res)=>{
            console.log(res.data.message);
          });
      }
    }
  }
</script>
<style scoped>
  .remind {
    display: inline-block;
    font-size: 8px;
    color: red;
    text-decoration: underline;
  }

  .el-form-item {
    margin-top: 0;
  }
</style>
