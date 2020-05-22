<template>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="引用">
              <el-select
                filterable
                style="display: block"
                v-model="referenceTags"
                multiple
                value-key="id"
                placeholder="只能从您已收藏的微证据中选择哦">
                <el-option
                  v-for="(value, index) in referenceList"
                  :key="value.id"
                  :label="value.title"
                  :value="value.id">
                </el-option>
              </el-select>
        </el-form-item>

        <el-form-item label="关键词">
          <div>
            <el-tag :key="tag" v-for="tag in keyWordTags" closable :disable-transitions="false" @close="handleClose(tag)">
              {{tag}}
            </el-tag>
          </div>
          <el-input v-model="keyWordValue" maxlength="10" show-word-limit placeholder="Fill in your keyword as follows" style="width: 90%;"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag()">增加</el-button>
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
          <el-input type="textarea" rows="10" v-model="form.text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="toHomepageSubmit">发布</el-button>
          <el-button @click="toHomepageCancel">取消</el-button>
        </el-form-item>
      </el-form>
</template>

<script>
  export default {
    name: "NewMGuess",
    data() {
      return {
        referenceTags: [],
        keyWordTags: [],
        labelList: Array,
        referenceList: Array,
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


        params.append('userId', sessionStorage.getItem("accountId"));
        this.axios.post('/mGuess/getMEvid', params)
          .then((res) => {
            console.log(res.data);
            this.referenceList = res.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },

      user() {
        this.$router.push('/user');
      },

      handleClose(tag) {
        this.keyWordTags.splice(this.keyWordTags.indexOf(tag), 1);

      },

      addTag() {
        let keyWordValue = this.keyWordValue;
        if (keyWordValue) {
          this.keyWordTags.push(keyWordValue);
        }
        this.keyWordValue = '';
        console.log(this.keyWordTags.join('-'))

      },
      toHomepageSubmit() {
        if (this.referenceTags.length <= 1) {
          this.$alert("请填写至少两个引用微证据", "引用微证据不能少于两个", {
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
          this.form.reference = this.referenceTags.join('-');
          this.form.keyWord = this.keyWordTags.join('-');
          this.form.label = this.labelChoose.join('-');
          var params = new URLSearchParams();
          params.append('topic',this.form.label);
          params.append('reference', this.form.reference);
          params.append('keywords', this.form.keyWord);
          params.append('title', this.form.title);
          params.append('summary', this.form.text);
          params.append('authorId', 0);
          this.axios.post('/mGuess/addMGuess', params)
            .then((res) => {
              // var remindType = res.data.code == 0 ? 'success' : 'info';
              var remindTitle = res.data === 0 ? '发布微猜想成功' : '发布微猜想失败';
              var remindContent = res.data === 0 ? '发布微猜想成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
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
