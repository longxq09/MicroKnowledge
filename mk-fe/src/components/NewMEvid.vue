<template>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="引用论文">
          <div>
          <el-tag :key="tag" v-for="tag in referenceTags" closable :disable-transitions="false" @close="handleClose(tag,1)">
            {{tag}}
          </el-tag>
          </div>
          <el-input v-model="referenceValue" placeholder="Fill in your references as follows" style="width: 90%;"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(1)">增加</el-button>
        </el-form-item>

        <el-form-item label="关键词">
          <div>
          <el-tag :key="tag" v-for="tag in keyWordTags" closable :disable-transitions="false" @close="handleClose(tag,2)">
            {{tag}}
          </el-tag>
          </div>
          <el-input v-model="keyWordValue" placeholder="Fill in your keyword as follows" style="width: 90%;" maxlength="10" show-word-limit ></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(2)">增加</el-button>
        </el-form-item>

        <el-form-item label="分类">
              <el-select
                filterable
                style="display: block"
                v-model="labelChoose"
                multiple
                value-key="id">
                <el-option
                  v-for="(value, index) in labelList"
                  :key="value.id"
                  :label="value.topicName"
                  :value="value.id">
                </el-option>
              </el-select>
        </el-form-item>

        <el-form-item label="主题">
          <el-input v-model="form.title" maxlength="30" show-word-limit></el-input>
        </el-form-item>

        <el-form-item label="正文">
          <el-input type="textarea" rows="10" maxlength="250" v-model="form.text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="toHomepageSubmit">发布</el-button>
          <el-button @click="toHomepageCancel">取消</el-button>
        </el-form-item>
      </el-form>
</template>

<script>
  export default {
    name: "NewMEvid",
    data() {
      return {
        referenceTags: [],
        keyWordTags: [],
        labelList: Array,
        referenceValue: '',
        keyWordValue: '',
        labelChoose: [],
        form: {
          title: '',
          text: '',
          keyWord: '',
          label: '',
          reference: ''
        },
      }
    },
    created() {
      console.log("init");
      this.getUserInfo();
    },
    methods: {
      getUserInfo() {
        var params = new URLSearchParams();
        this.axios.get('/topic/getTopicList', params)
          .then((res) => {
            console.log(res.data);
            this.labelList = res.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },

      user() {
        this.$router.push('/user');
      },

      handleClose(tag, type) {
        if (type == 1) {
          this.referenceTags.splice(this.referenceTags.indexOf(tag), 1);
        } else {
          this.keyWordTags.splice(this.keyWordTags.indexOf(tag), 1);
        }
      },

      addTag(type) {
        if (type == 1) {
          let referenceValue = this.referenceValue;
          if (referenceValue) {
            this.referenceTags.push(referenceValue);
          }
          this.referenceValue = '';
        } else {
          let keyWordValue = this.keyWordValue;
          if (keyWordValue) {
            this.keyWordTags.push(keyWordValue);
          }
          this.keyWordValue = '';
          console.log(this.keyWordTags.join('-'))
        }
      },
      toHomepageSubmit() {
        if (this.referenceTags.length == 0) {
          this.$alert("请填写至少一个引用论文", "引用论文不能为空", {
            confirmButtonText: '确定'
          });
        } else if (this.keyWordTags.length == 0) {
          this.$alert("请填写至少一个关键词", "关键词不能为空", {
            confirmButtonText: '确定'
          });
        } else if (this.labelChoose.length == 0) {
          this.$alert("请填写至少一个分类", "分类不能为空", {
            confirmButtonText: '确定'
          });
        } else if (this.form.title.length == 0) {
          this.$alert("请填主题", "主题不能为空", {
            confirmButtonText: '确定'
          });
        } else if (this.form.text.length == 0) {
          this.$alert("请填写正文部分", "正文部分不能为空", {
            confirmButtonText: '确定'
          });
        } else {
          console.log("this.referenceTags="+this.referenceTags);
          this.form.reference = this.referenceTags.join('-');
          this.form.keyWord = this.keyWordTags.join('-');
          this.form.label = this.labelChoose.join('-');
          var params = new URLSearchParams();
          params.append('topic', this.form.label);
          params.append('reference', this.form.reference);
          params.append('keywords', this.form.keyWord);
          params.append('title', this.form.title);
          params.append('summary', this.form.text);
          params.append('authorId', 0);
          this.axios.post('/mEvidence/addMEvid', params)
            .then((res) => {
              // var remindType = res.data.code == 0 ? 'success' : 'info';
              var remindTitle = res.data === 0 ? '发布微证据成功' : '发布微证据失败';
              var remindContent = res.data === 0 ? '发布微证据成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
              console.log("------------" + res.data);
              this.$alert(remindContent, remindTitle, {
                confirmButtonText: '确定'
              });
              if (res.data === 0) {
                this.$router.push('/homepage');
              }
            })
            .catch((error) => {
              console.log(error);
            });
        }

      },
      toHomepageCancel() {
        this.$router.push('/homepage');
      }
    }

  }
</script>

<style>
  .el-form-item {
    margin-right: 20%;
    margin-top: 30px;
    width: 900px;
  }

  .el-tag {
    margin-right: 10px;
    border-radius: 30px;
  }
</style>
