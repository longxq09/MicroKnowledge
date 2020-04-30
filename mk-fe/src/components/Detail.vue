<template>
  <el-container>
    <el-header>
      <v-head v-bind:title="head_title"></v-head>
    </el-header>
    <el-main>
      <div class="noice_title">{{form.type}} | {{form.title}}</div>
      <nobr style="font-weight: 600;margin-left: 10px;">{{form.author}}</nobr>
      <el-tag key="tag" v-for="tag in keyWordList" class="keyword">{{tag}}</el-tag>
      <div class="main_text">{{form.text}}</div>
      <div class="bottom_text">引用
        <nobr key="reference" v-for="reference in referenceList" > | {{reference}}</nobr>
      </div>
      <div class="bottom_text">分类
        <nobr key='label' v-for="label in labelList" > | {{label}}</nobr>
      </div>
      <div style="text-align: center;" v-if="need_review">
        <el-button class="review_button" @click="review(1)"> 通过 </el-button>
        <el-button class="review_button" @click="review(2)">不通过</el-button>
      </div>

      <div style="text-align: center;" v-else>
        <el-button class="review_choose" v-if="review_pass">通过<br>{{pass_num}}</el-button>
        <el-button class="review_button" v-else> 通过<br>{{pass_num}} </el-button>
        <el-button class="review_button"  v-if="review_pass">不通过<br>{{unpass_num}}</el-button>
        <el-button class="review_choose" v-else>不通过<br>{{unpass_num}}</el-button>

      </div>
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
        head_title: "微知 MicroKnowledge",
        referenceList: ['c系列丛书', '从入门到如图'],
        keyWordList: ['machine learning', 'python从入门到入土'],
        labelList: ['深度学习', 'hhh'],
        need_review:true,
        review_pass:true,
        pass_num:1,
        unpass_num:3,
        form: {
          type: "微证据",
          title: '震惊！冯如杯要写不完了？！',
          text: '冯如杯写不完是怎么回事呢？冯如杯相信大家都很熟悉，但是冯如杯写不完是怎么回事呢，下面就让小编带大家一起了解吧。冯如杯写不完， 其实就是冯如杯就是憨憨， 大家可能会很惊讶冯如杯怎么会写不完呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦',
          keyWord: '',
          label: '',
          reference: '',
          author: 'uc主编',
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
        var id = this.$route.params.id;

        //--------------------------------------------------------
        //此处this.$route.params.id还没设置好页面入口，可以先替换为常数值
        //此处判断id正负是因为跟wjk商量首页发送的信息中用id正负来区分微公告类型
        //此处的url要加一个author字段，但考虑后端这个没实现，暂时没加上，用的手动默认值
        //--------------------------------------------------------
        //最后还差一个url来得到该微公告的评审现况以及用户是否评审
        //需要设计一下，前端发用户id和微公告id，后端需要发回来
        //need_review：用户是否还需要评审，评审过了为false，未评审为true
        //review_pass：如果用户已经评审过则需要返回评审结果，否则该值无意义
        //pass_num/unpass_num：当前该微公告的评审结果，通过/未通过人数
        //--------------------------------------------------------

        if (id > 0) {
          params.append('id', id);
          try {
            let res = await this.axios.post('/MEvidence/toModifyMEvid', params);
            console.log(res.data);
            this.form.title = res.data.title;
            this.form.text = res.data.summary;
            this.form.keyWord = res.data.keywords;
            this.form.label = res.data.topic;
            this.form.reference = res.data.citedPaper;
            this.form.author = res.data.author;
          } catch (err) {
            console.log(err);
          }
        } else {
          params.append('id', -id);
          try {
            let res = await this.axios.post('/MGuess/toModifyMGuess', params);
            console.log(res.data);
            this.form.title = res.data.title;
            this.form.text = res.data.summary;
            this.form.keyWord = res.data.keywords;
            this.form.label = res.data.topic;
            this.form.reference = res.data.citedEvidList;
          } catch (err) {
            console.log(err);
          }
        }
        this.referenceList = this.form.reference.split('-');
        this.keyWordList = this.form.keyWord.split('-');
        this.labelList = this.form.label.split('-');
      },


      review(type){
        this.need_review=false;
        if(type==1){
          this.review_pass=true;
          this.pass_num=this.pass_num+1;
        }
        else{
          this.review_pass=false;
          this.unpass_num=this.unpass_num+1;
        }
        //----------------------------------
        //此处还要加一个url，返回用户的投票结果
        //----------------------------------
      }
    }

  }
</script>

<style>
  .el-header {
    background-color: #FFFFFF;
    line-height: 1.5;
  }

  .el-main {
    background-color: #F4F4F5;
    color: #333;
    padding-left: 15%;
    padding-right: 25%;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 25px;
    font-weight: 500;
    display: inline-block;
    margin: 10px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .main_text {
    margin: 10px;
    margin-top: 30px;
    margin-bottom: 30px;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
    margin-right: 10px;
    border-radius: 30px;
  }
  .bottom_text{
    margin-left: 10px;
    color: slategrey;
    font-size:15px;
  }
  .review_button{
    width: 100px;
    height: 50px;
    margin: 30px;
  }
  .review_choose{
    width: 100px;
    height: 50px;
    margin: 30px;
    background-color: #ECF5FF;
    color: #409EFF;
    border: 1px solid #C6E2FF;
  }
</style>
