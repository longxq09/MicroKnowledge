<template>
  <el-container>
    <el-header>
      <v-head v-bind:title="head_title"></v-head>
    </el-header>
    <el-main>
      <div class="noice_title">{{form.type_str}} | {{form.title}}</div>
      <nobr style="font-weight: 600;margin-left: 10px;">{{form.authorName}}</nobr>
      <nobr style="font-weight: 400;font-size: 15px;margin-left: 10px;">{{form.time}}</nobr>
      <el-tag :key="tag" v-for="tag in keyWordList" class="keyword">{{tag}}</el-tag>
      <div style="margin: 10px;margin-top: 30px;margin-bottom: 30px;">{{form.text}}</div>
      <div class="bottom_text">引用
        <nobr :key="reference" v-for="reference in referenceList"> | {{reference}}</nobr>
      </div>
      <div class="bottom_text">分类
        <nobr :key='label' v-for="label in labelList"> | {{label}}</nobr>
      </div>
      <div style="text-align: center;" v-if="need_review">
        <el-button class="review_button" @click="review(1)"> 通过 </el-button>
        <el-button class="review_button" @click="review(2)">不通过</el-button>
      </div>

      <div style="text-align: center;" v-else>
        <el-button class="review_choose" v-if="review_pass">通过<br>{{pass_num}}</el-button>
        <el-button class="review_button" v-else> 通过<br>{{pass_num}} </el-button>
        <el-button class="review_button" v-if="review_pass">不通过<br>{{unpass_num}}</el-button>
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
    name: "Review",
    data() {
      return {
        need_review: true,
        review_pass: true,
        pass_num: 1,
        unpass_num: 3,
        head_title: "微知 MicroKnowledge",
        referenceList: ['c系列丛书', '从入门到如图'],
        keyWordList: ['machine learning', 'python从入门到入土'],
        labelList: ['深度学习', 'hhh'],
        form: {
          type_str: "微证据",
          title: '震惊！冯如杯要写不完了？！',
          text: '冯如杯写不完是怎么回事呢？冯如杯相信大家都很熟悉，但是冯如杯写不完是怎么回事呢，下面就让小编带大家一起了解吧。冯如杯写不完， 其实就是冯如杯就是憨憨， 大家可能会很惊讶冯如杯怎么会写不完呢？ 但事实就是这样， 小编也感到非常惊讶。这就是关于冯如杯写不完的事情了， 大家有什么想法呢， 欢迎在评论区告诉小编一起讨论哦！ 啦啦啦啦啦啦啦',
          keyWord: '',
          label: '',
          reference: '',
          authorName: 'uc主编',
          authorId: 0,
          type: 1,
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
        params.append('id', this.$route.query.id);
        try {
          let res = await this.axios.post('/mNotice/getNoticeById', params);
          console.log(res.data);
          this.form.title = res.data.title;
          this.form.text = res.data.summary;
          this.form.keyWord = res.data.keywords;
          this.form.label = res.data.topic;
          this.form.reference = res.data.reference;
          this.form.authorName = res.data.authorName;
          this.form.authorId = res.data.authorId
          this.form.type = res.data.type;
          this.form.time = res.data.time;

          this.referenceList = this.form.reference.split('-');
          this.keyWordList = this.form.keyWord.split('-');
          this.labelList = this.form.label.split('-');
          if (this.form.type == 1) {
            this.type_str = "微证据";
          } else {
            this.type_str = "微猜想";
          }
        } catch (err) {
          console.log(err);
        }
        
        //我将传递给后端该登陆用户id和noticeid
        //需要后端传递给我一个map
        //里面参数为
        //need_review:该用户是否评审过这条微公告，没评审need_review为true
        //review_pass:如果该用户评审过，那么他的评审结果，通过为true，不通过为false
        //pass_num:当前该微公告评审通过票数
        //unpass_num:当前该微公告评审不通过票数
        
        var params2 = new URLSearchParams();
        params2.append('userId', localStorage.getItem("accountId"));
        params2.append('noticeId', this.$route.query.id);
        try {
          let res = await this.axios.post('一个获取评审情况的url的名字', params2);
          console.log(res.data);
          this.need_review = res.data.need_review;
          this.review_pass = res.data.review_pass;
          this.pass_num = res.data.pass_num;
          this.unpass_num = res.data.unpass_num;
        } catch (err) {
          console.log(err);
        }
      },


      review(type) {
        this.need_review = false;
        if (type == 1) {
          this.review_pass = true;
          this.pass_num = this.pass_num + 1;
        } else {
          this.review_pass = false;
          this.unpass_num = this.unpass_num + 1;
        }
        
        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        params.append('noticeId', this.$route.query.id);
        params.append('review_pass', this.review_pass);
        this.axios.post('一个反馈该用户评审结果的url', params)
          .then((res) => {
            // var remindType = res.data.code == 0 ? 'success' : 'info';
            var remindTitle = res.data === 0 ? '评价微证据成功' : '评价微证据失败';
            var remindContent = res.data === 0 ? '评价微证据成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
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

  .bottom_text {
    margin-left: 10px;
    color: slategrey;
    font-size: 15px;
  }

  .review_button {
    width: 100px;
    height: 50px;
    margin: 30px;
  }

  .review_choose {
    width: 100px;
    height: 50px;
    margin: 30px;
    background-color: #ECF5FF;
    color: #409EFF;
    border: 1px solid #C6E2FF;
  }
</style>
