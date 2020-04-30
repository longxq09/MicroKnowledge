<template>
  <el-container>
    <el-header>
      <v-head v-bind:title="title"></v-head>
    </el-header>
    <el-main>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="引用论文">
          <el-tag :key="tag" v-for="tag in referenceTags" closable :disable-transitions="false" @close="handleClose(tag,1)">
            {{tag}}
          </el-tag>
          <el-input type="textarea" rows="2" v-model="referenceValue" placeholder="Fill in your references as follows"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(1)">增加</el-button>
        </el-form-item>

        <el-form-item label="关键词">
          <el-tag :key="tag" v-for="tag in keyWordTags" closable :disable-transitions="false" @close="handleClose(tag,2)">
            {{tag}}
          </el-tag>
          <el-input v-model="keyWordValue" placeholder="Fill in your keyword as follows"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(2)">增加</el-button>
        </el-form-item>

        <el-form-item label="分类">
          <el-checkbox v-for="(value,index) in labelList" key="value.topicName" v-model="value.checked" @change="chooseItem(value.id)">{{value.topicName}}
          </el-checkbox>
        </el-form-item>

        <el-form-item label="主题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>

        <el-form-item label="正文">
          <el-input type="textarea" rows="10" v-model="form.text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="toHomepageSubmit">发布</el-button>
          <el-button @click="toHomepageCancel">取消</el-button>
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
    name: "NewMEvid",
    data() {
      return {
        title: "微知 MicroKnowledge | 微证据修改",
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
    components: {
      vHead,
      vFooter
    },
    created() {
      console.log("init");
      this.getUserInfo();
    },
    methods: {
      async getUserInfo() {
        var params = new URLSearchParams();

        try {
          let res = await this.axios.get('/Topic/getTopicList', params);
          console.log(res.data);
          this.labelList = res.data;
        } catch (err) {
          console.log(err);
        }

        params.append('id', this.$route.query.id);
        try {
          let res = await this.axios.post('/MEvidence/toModifyMEvid', params);
          console.log(res.data);
          this.form.title = res.data.title;
          this.form.text = res.data.summary;
          this.form.keyWord = res.data.keywords;
          this.form.label = res.data.topic;
          this.form.reference = res.data.citedPaper;

          this.referenceTags = this.form.reference.split('-');
          this.keyWordTags = this.form.keyWord.split('-');
          this.labelChoose = this.form.label.split('-');
          var temp = this.labelChoose;
          this.labelList.forEach(item => {
            item.checked = (temp.indexOf(item.id.toString()) !== -1);
          });
        } catch (err) {
          console.log(err);
        }


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
      chooseItem(id, value) {
        id_str = id.toString();
        console.log("id:" + id);
        if (this.labelChoose.indexOf(id_str) == -1) {
          this.labelChoose.push(id_str);
          value.checked = true;
        } else {
          this.labelChoose.splice(this.labelChoose.indexOf(id_str), 1);
          value.checked = false;
        }
        var temp = this.labelChoose;
        this.labelList.forEach(item => {
          item.checked = (temp.indexOf(item.id.toString()) !== -1);
        });
        console.log("labelChoose:" + this.labelChoose);
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

          this.form.reference = this.referenceTags.join('-');
          this.form.keyWord = this.keyWordTags.join('-');
          this.form.label = this.labelChoose.join('-');
          var params = new URLSearchParams();
          params.append('topic', this.form.label);
          params.append('citedPaper', this.form.reference);
          params.append('keywords', this.form.keyWord);
          params.append('title', this.form.title);
          params.append('summary', this.form.text);
          params.append('id', this.$route.query.id);
          params.append('authorId', 0);
          this.axios.post('/MEvidence/modifyMEvid', params)
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
  .el-header {
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

  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }

  .el-form-item {
    margin-right: 20%;
    margin-top: 30px;
  }

  .el-tag {
    margin-right: 10px;
    border-radius: 30px;
  }
</style>
