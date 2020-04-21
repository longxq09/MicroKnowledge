<template>
	<el-container>
		<el-header>
			<div class="title">微知 MicroKnowledge | 个人信息</div>
			<el-badge class="item">
				<el-button @click="user">回到主页</el-button>
			</el-badge>
		</el-header>
		<el-main>
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item label="头像">
					<el-avatar> user </el-avatar>
					<el-upload
						action="https://jsonplaceholder.typicode.com/posts/"
						list-type="picture-card"
						:visible="notdialogVisible"
						:on-preview="handlePictureCardPreview"
						:on-remove="handleRemove">
						<i class="el-icon-plus"></i>
					</el-upload>
					<el-dialog :visible.sync="dialogVisible">
						<img width="100%" :src="dialogImageUrl" alt="">
					</el-dialog>
				</el-form-item>
				<el-form-item label="昵称">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="个性签名">
					<el-input type="textarea" v-model="form.desc"></el-input>
				</el-form-item>
				<el-form-item label="性别">
					<el-select v-model="form.region" placeholder="请选择">
						<el-option label="男" value="male"></el-option>
						<el-option label="女" value="female"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="教育经历">
					<el-select v-model="form.region" placeholder="请选择">
						<el-option label="" 高中及以下" value="male"></el-option>
						<el-option label="本科" value="female"></el-option>
						<el-option label="硕士" value="female"></el-option>
						<el-option label="博士" value="female"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="工作">
					<el-input v-model="form.name"></el-input>
				</el-form-item>
				<el-form-item label="居住地">
					<el-input type="textarea" v-model="form.desc"></el-input>
				</el-form-item>
				<el-form-item label="自我介绍">
					<el-input type="textarea" v-model="form.desc"></el-input>
				</el-form-item>
				<el-form-item label="突出贡献">
					<el-input type="textarea" v-model="form.desc"></el-input>
				</el-form-item>
				<el-form-item label="专业领域">
					<el-tag :key="tag" 
						v-for="tag in expertiseTags" 
						closable 
						:disable-transitions="false"
						@close="handleClose(tag)">
						{{tag}}
					</el-tag>
					<el-select 
						v-model="selectValue"
						v-if="selectVisible"
						@keyup.enter.native="handleInputConfirm"
						@blur="handleInputConfirm"
						placeholder="请选择">
						<el-option
							v-for="item in options"
							:key="item.value"
							:label="item.label"
							:value="item.value">
						</el-option>
					</el-select>
					<el-button v-else class="button-new-tag" size="small" @click="showInput">增加</el-button>
				</el-form-item>
				<el-form-item label="兴趣领域">
					<el-radio-group v-model="form.resource">
						<el-radio label="线上品牌商赞助"></el-radio>
						<el-radio label="线下场地免费"></el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit">立即更新</el-button>
					<el-button @click="cancel">取消</el-button>
				</el-form-item>
			</el-form>
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
				expertiseTags: ['English', 'Speech Model', 'Deep Learning'],
				selectVisible: false,
        selectValue: '',
				dialogImageUrl: '',
        dialogVisible: false,
				notdialogVisible: true,
				form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
		methods: {
      user() {
				this.$router.push('/user');
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
			
			handleRemove(file, fileList) {
        this.dialogVisible = false;
				this.notdialogVisible = true;
      },
			
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
				this.notdialogVisible = false;
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
