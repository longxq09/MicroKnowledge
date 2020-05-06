<template>
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
</template>

<script>
  export default {
    name: "Register",
    data() {
      return {
        dataForm: {name: '', password: '', email: ''},
        rules: {
          name: [{required: true, message: '账号不可为空', trigger: 'blur'}],
          password: [{required: true, message: '密码不可为空', trigger: 'blur'}],
          email: [{required: true, message: '邮箱不可为空', trigger: 'blur'}],
        },
      }
    },
		methods: {
      register () {
        var params = new URLSearchParams();
        params.append('name', this.dataForm.name);
        params.append('email', this.dataForm.email);
        params.append('password',this.dataForm.password);
        this.axios.post('/user/register', params)
          .then((res)=>{
            var remindType = res.data.code == 0 ? 'success' : 'info';
            var remindTitle = res.data.code == 0 ? '注册成功' : '注册失败';
            var remindContent = res.data.code == 0 ? '即将跳转个人页面' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
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
            console.log(res.data.message);
          });
      }
    }
  }
</script>
