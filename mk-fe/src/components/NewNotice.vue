<template>
  <el-container>
    <el-header>
      <div class="title">微知 MicroKnowledge | 微证据发布</div>
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
          <el-checkbox v-for="(value,index) in labelList" key="value.topicName" @change="chooseItem(value.id)">{{value.topicName}}</el-checkbox>
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
  import vFooter from './common/Footer.vue';
  export default {
    name: "NewNotice",
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
    components: {
      vFooter
    },
    created() {
      console.log("init");
      this.getUserInfo();
    },
    methods: {
        getUserInfo() {
          var params = new URLSearchParams();
          this.axios.get('/Topic/getTopicList', params)
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
      chooseItem(id) {
        console.log("id:"+id);
        if (this.labelChoose.indexOf(id) == -1) {
          this.labelChoose.push(id)
        } else {
          this.labelChoose.splice(this.labelChoose.indexOf(id), 1);
        }
        console.log("labelChoose:"+this.labelChoose);
      },
      toHomepageSubmit() {
        this.form.reference = this.referenceTags.join('-');
        this.form.keyWord = this.keyWordTags.join('-');
        this.form.label = this.labelChoose.join('-');
        var params = new URLSearchParams();
        params.append('topic', this.form.label);
        params.append('citedPaper', this.form.reference);
        params.append('keywords', this.form.keyWord);
        params.append('title', this.form.title);
        params.append('summary', this.form.text);
        params.append('authorId', 0);
        this.axios.post('/MEvidence/addMEvid', params)
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
