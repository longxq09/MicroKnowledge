<template>
  <el-container>
    <el-header>
      <div class="title">微知 MicroKnowledge | 微证据发布</div>
    </el-header>
    <el-main>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="引用论文">
          <el-tag :key="tag" v-for="tag in referenceTags" closable :disable-transitions="false" @close="handleClose(tag)">
            {{tag}}
          </el-tag>
          <el-input type="textarea" rows="2" v-model="referenceValue" placeholder="Fill in your references as follows"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(1)">增加</el-button>
        </el-form-item>

        <el-form-item label="关键词">
          <el-tag :key="tag" v-for="tag in keyWordTags" closable :disable-transitions="false" @close="handleClose(tag)">
            {{tag}}
          </el-tag>
          <el-input v-model="keyWordValue" placeholder="Fill in your keyword as follows"></el-input>
          <el-button class="button-new-tag" size="small" @click="addTag(2)">增加</el-button>
        </el-form-item>

        <el-form-item label="分类">
          <el-checkbox :key="label" v-for="label in labelList" @change="chooseItem(value.id)">{{label}}</el-checkbox>
        </el-form-item>

        <el-form-item label="主题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>

        <el-form-item label="正文">
          <el-input type="textarea" rows="10" v-model="form.text"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">发布</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-main>
		<v-footer></v-footer>
  </el-container>
</template>

<script>
import vFooter from './common/Footer.vue';
  export default {
    name: "New_notice",
    data() {
      return {
        referenceTags: [],
        keyWordTags: ['machine learning'],
        labelList: ['machine learning', 'deep learning', 'c/c++', 'python', 'deep learning',
          'c/c++', 'python', 'deep learning', 'c/c++', 'python', 'deep learning', 'c/c++', 'python',
        ],
        referenceValue: '',
        keyWordValue: '',
        form: {
          title: '',
          text: ''
        }
      }
    },
    components: {
    	vFooter
    },
    methods: {
      user() {
        this.$router.push('/user');
      },

      handleClose(tag) {
        this.expertiseTags.splice(this.expertiseTags.indexOf(tag), 1);
      },

      addTag(a) {
        if (a == 1) {
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
        }
      },
      chooseItem(id) {
        if (this.labelChoose.indexOf(id) == -1) {
          this.labelChoose.push(id)
        } else {
          this.labelChoose.splice(this.labelChoose.indexOf(id), 1);
        }
      },
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
