<template>
	<el-container>
		<el-header>
			<v-head v-bind:user=true></v-head>
		</el-header>
		
		<el-main>
			
		</el-main>
		<v-footer></v-footer>
	</el-container>
</template>


<script>
import vHead from './common/Header.vue';
import vFooter from './common/Footer.vue';
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
		components: {
			vHead,
			vFooter
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
