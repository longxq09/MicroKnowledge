<template>
  <el-container>
    <el-header>
      <v-head v-bind:title="head_title" v-bind:homepage=true v-bind:detail=true></v-head>
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
        <el-button class="review_button" @click="review(-1)">不通过</el-button>
      </div>

      <div style="text-align: center;" v-else>
        <el-button class="review_choose" v-if="review_pass" @click="cancle(1)">通过<br>{{pass_num}}</el-button>
        <el-button class="review_button" v-else> 通过<br>{{pass_num}} </el-button>
        <el-button class="review_button" v-if="review_pass">不通过<br>{{unpass_num}}</el-button>
        <el-button class="review_choose" v-else @click="cancle(-1)">不通过<br>{{unpass_num}}</el-button>

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
        review_type: 0,
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

        var params2 = new URLSearchParams();
        params2.append('userId', localStorage.getItem("accountId"));
        params2.append('noticeId', this.$route.query.id);
        try {
          let res = await this.axios.post('/review/userToReview', params2);
          console.log(res.data);
          this.review_type = res.data.type;
          if (this.review_type == 0) {
            this.need_review = true;
          }
          if (this.review_type == 1) {
            this.need_review = false;
            this.review_pass = true;
          }
          if (this.review_type == -1) {
            this.need_review = false;
            this.review_pass = false;
          }
          this.pass_num = res.data.pass_num;
          this.unpass_num = res.data.unpass_num;
        } catch (err) {
          console.log(err);
        }
      },


      review(type) {
        this.need_review = false;
        this.review_pass = (type == 1);

        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        params.append('noticeId', this.$route.query.id);
        params.append('type', type);
        this.axios.post('/review/doReview', params)
          .then((res) => {
            this.pass_num = res.data.pass_num;
            this.unpass_num = res.data.unpass_num;
            this.review_type = res.data.type;
          })
          .catch((error) => {
            console.log(error);
          });
      },
      cancle(type) {
        this.need_review = true;

        var params = new URLSearchParams();
        params.append('userId', localStorage.getItem("accountId"));
        params.append('noticeId', this.$route.query.id);
        params.append('type', 0);
        this.axios.post('/review/doReview', params)
          .then((res) => {
            this.pass_num = res.data.pass_num;
            this.unpass_num = res.data.unpass_num;
            this.review_type = res.data.type;
          })
          .catch((error) => {
            console.log(error);
          });
      },
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
