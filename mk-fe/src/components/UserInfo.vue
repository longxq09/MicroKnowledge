<template>
	<el-container>
		<el-header>
			<v-head v-bind:title="title"  v-bind:userinfo=true></v-head>
		</el-header>
		<el-main>
      <el-tabs tab-position="left">
        <el-tab-pane label="账号管理">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="头像">
              <el-upload
                class="avatar-uploader"
                action=""
                :show-file-list="false"
                :auto-upload="true"
                :http-request="uploadImage"
                :before-upload="beforeImageUpload">
                <img v-if="form.picture" :src="form.picture" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit">立即更新</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="安全管理">
          <el-form :model="passwordForm" status-icon
                   :rules="rules" ref="passwordForm"
                   label-width="100px"
                   class="passwordForm">
            <el-form-item label="旧密码" prop="old">
              <el-input type="password" v-model="passwordForm.old" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="new">
              <el-input type="password" v-model="passwordForm.new" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="repeat">
              <el-input type="password" v-model="passwordForm.repeat" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="rePassword">提交</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="信息管理">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="个性签名">
              <el-input type="textarea" v-model="form.signature"></el-input>
            </el-form-item>
            <el-form-item label="性别">
              <el-radio v-model="form.sex" label="male">男</el-radio>
              <el-radio v-model="form.sex" label="female">女</el-radio>
            </el-form-item>
            <el-form-item label="教育经历">
              <el-select v-model="form.education" placeholder="请选择">
                <el-option label="高中及以下" value="高中及以下"></el-option>
                <el-option label="本科" value="本科"></el-option>
                <el-option label="硕士" value="硕士"></el-option>
                <el-option label="博士" value="博士"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="工作">
              <el-input v-model="form.works"></el-input>
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
              <el-select
                style="display: block"
                v-model="expertiseTags"
                multiple
                value-key="id">
                <el-option
                  v-for="(value, index) in topics"
                  :key="value.id"
                  :label="value.topicName"
                  :value="value.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="兴趣领域">
              <el-select
                style="display: block"
                v-model="interestTags"
                multiple
                value-key="id">
                <el-option
                  v-for="(value, index) in topics"
                  :key="value.id"
                  :label="value.topicName"
                  :value="value.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submit">立即更新</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
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
      var validateOld = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if(this.passwordForm.old !== '') {
            this.$refs.passwordForm.validateField('new')
          }
          callback()
        }
      }
      var validateNew = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.passwordForm.repeat !== '') {
            this.$refs.passwordForm.validateField('repeat')
          }
          callback()
        }
      }
      var validateRepeat = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value != this.passwordForm.new) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }
      return {
        title: "微知 | 个人信息",
        topics: Array,
        interestTags: [],
        expertiseTags: [],
        form: {},
        passwordForm: {old: '', new: '', repeat: ''},
        rules: {
          old: [
            { validator: validateOld, trigger: 'blur' }
          ],
          new: [
            { validator: validateNew, trigger: 'blur' }
          ],
          repeat: [
            { validator: validateRepeat, trigger: 'blur' }
          ]
        }
      }
    },
		components: {
			vHead,
			vFooter
		},
		mounted() {
		  this.getUserInfo();
		},
		methods: {
		  getUserInfo() {
        this.axios.get('/topic/getTopicList')
          .then((res) => {
            this.topics = res.data
          })
          .catch((error) => {
            console.log(error)
          })
		    this.axios.get('/user/info', {
		      params: { id: localStorage.getItem("accountId")}
		    }).then((res) => {
            this.form = res.data
            var tags = res.data.interest.split('-')
            for(var i = 0; i < tags.length; i++)
              this.interestTags.push(parseInt(tags[i]))
            tags = res.data.expertise.split('-')
            for(var i = 0; i < tags.length; i++)
              this.expertiseTags.push(parseInt(tags[i]))
          })
          .catch((error) => {
            console.log(error)
          });
		  },

      submit() {
        var params = new URLSearchParams()
        params.append('id', localStorage.getItem("accountId"))
        params.append('name', this.form.name)
        params.append('sex', this.form.sex)
        params.append('education', this.form.education)
        params.append('works', this.form.works)
        params.append('address', this.form.address)
        params.append('introduction', this.form.introduction)
        params.append('contribution', this.form.contribution)
        params.append('interest', this.getInterest())
        params.append('expertise', this.getExpertise())
        params.append('picture', this.form.picture)
        this.axios.post('/user/info', params)
          .then((res) => {
            var remindTitle = res.data.code == 0 ? '修改成功' : '修改失败'
            var remindContent = res.data.code == 0 ? '个人信息更新啦！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧'
            if(res.data.code == 0) {
              this.$router.push('/user');
            }
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            })
          })
          .catch((error) => {
            console.log(error)
          })
      },

      getInterest() {
        var interest = ''
        for (var i = 0; i < this.interestTags.length; i++)
          interest = interest + this.interestTags[i].toString() + '-'
        return interest.slice(0, -1)
      },

      getExpertise() {
        var expertise = ''
        for (var i = 0; i < this.expertiseTags.length; i++)
          expertise = expertise + this.expertiseTags[i].toString() + '-'
        return expertise.slice(0, -1)
      },

      rePassword() {
        var params = new URLSearchParams()
        params.append('oldPassword', this.passwordForm.old)
        params.append('newPassword', this.passwordForm.new)
        params.append('email', localStorage.getItem("email"))
        params.append('id', localStorage.getItem("accountId"))
        this.axios.post('/user/rePassword', params)
          .then((res) => {
            var remindTitle = res.data.code == 0 ? '修改成功' : '修改失败';
            var remindContent = res.data.code == 0 ? '密码更新成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~密码更改失败';
            if(res.data.code == 0) {
              this.$router.push('/user');
            }
            this.$alert(remindContent, remindTitle, {
              confirmButtonText: '确定'
            })
          })
          .catch((error) => {
            console.log(error)
          })
      },

      cancel() {
        this.$router.push('/user')
      },

      beforeImageUpload(file) {
        const isJPG = file.type === 'image/jpeg'
        const isLt1M = file.size / 1024 / 1024 < 1
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!')
          return false
        }
        if (!isLt1M) {
          this.$message.error('上传头像图片大小不能超过 1MB!')
          return false
        }
      },

      uploadImage(image) {
		    var params = new URLSearchParams()
        params.append("id", localStorage.getItem("accountId"))
        params.append("imgStr", image.file)
        this.axios.post('/user/picUpload', params)
          .then((res) => {
            if(res.data.code == 0) {
              this.form.picture = res.data.picture
            }
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }

  }
</script>

<style>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }
  .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
      width: 178px;
      height: 178px;
      display: block;
  }

  .passwordForm {
    width: 300px;
  }
</style>
