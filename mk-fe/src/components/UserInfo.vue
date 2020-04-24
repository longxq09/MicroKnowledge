<template>
	<el-container>
		<el-header>
			<v-head v-bind:title="title"  v-bind:userinfo=true></v-head>
		</el-header>
		<el-main>
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="昵称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="个性签名">
          <el-input type="textarea" v-model="form.signature"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option label="男" value="male"></el-option>
            <el-option label="女" value="female"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="教育经历">
          <el-select v-model="form.education" placeholder="请选择">
            <el-option label="高中及以下" value="male"></el-option>
            <el-option label="本科" value="female"></el-option>
            <el-option label="硕士" value="female"></el-option>
            <el-option label="博士" value="female"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工作">
          <el-input v-model="form.work"></el-input>
        </el-form-item>
        <el-form-item label="居住地">
          <el-input type="textarea" v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="自我介绍">
          <el-input type="textarea" v-model="form.introduction"></el-input>
        </el-form-item>
        <el-form-item label="突出贡献">
          <el-input type="textarea" v-model="form.contribution"></el-input>
        </el-form-item>
        <el-form-item label="专业领域">
          <el-input type="textarea" v-model="form.expertise"></el-input>
        </el-form-item>
        <el-form-item label="兴趣领域">
          <el-tag :key="tag"
            v-for="tag in interestTags"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)">
            {{tag}}
          </el-tag>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">立即更新</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
			</el-form>
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
        title: "微知 | 个人信息",
        activeName: 'first',
        interestTags: [],
        selectVisible: false,
        selectValue: '',
        dialogImageUrl: '',
        dialogVisible: false,
        notdialogVisible: true,
        form: {}
      }
    },
		components: {
			vHead,
			vFooter
		},
		mounted() {
		  console.log("qwq");
		  this.getUserInfo();
		},
		methods: {
		  getUserInfo() {
		    var params = new URLSearchParams();
        params.append('account', 'wzs01@163.com');
		    this.axios.get('/user/info', params)
          .then((res) => {
            console.log(res.data);
            this.form = res.data
            this.interestTags = String(this.form.interest).split('-');
          })
          .catch((error) => {
            console.log(error);
          });
		  },
			handleClose(tag) {
        this.expertiseTags.splice(this.expertiseTags.indexOf(tag), 1);
      },

      showInput() {
        this.inputVisible = true;
        this.$nextTick(_ => {
          this.$refs.saveTagInput.$refs.input.focus();
        });
      },

      handleInputConfirm() {
        let selectValue = this.selectValue;
        if (selectValue) {
          this.expertiseTags.push(selectValue);
        }
        this.selectVisible = false;
        this.selectValue = '';
      },



      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },

      submit() {
        var params = new URLSearchParams();
        params.append('account', 'wzs01@163.com');
        params.append('name', this.form.name);
        params.append('sex', this.form.sex);
        params.append('education', this.form.education);
        params.append('work', this.form.work);
        params.append('address', this.form.address);
        params.append('introduction', this.form.introduction);
        params.append('contribution', this.form.contribution);
        params.append('expertise', this.form.expertise);
        params.append('interest', this.form.interest);
        this.axios.post('/user/info', params)
          .then((res) => {
            var remindType = res.data.code == 0 ? 'success' : 'info';
            var remindTitle = res.data.code == 0 ? '修改成功' : '修改失败';
            var remindContent = res.data.code == 0 ? '个人信息更新啦！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
            console.log("*********" + res.data.message);
            if(res.data.code == 0) {
              this.$router.push('/user');
            }
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            });
          })
          .catch((error) => {
            console.log(error);
          });
      },

      cancel() {
        this.$router.push('/user');
      }
		}

  }
</script>

<style>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

	.item {
		float: right;
		margin: 10px;
	}

	.el-tag + .el-tag {
    margin-left: 10px;
  }

  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

</style>
