<template>
  <div class="notice">
    <div class="noice_title">
      {{type_name}} | {{title}}
      <el-tag class="keyword" v-if="user">{{state}}</el-tag>
      <el-button class="bottom_tag" @click="toDetail"  v-if="!review">详情</el-button>
      <div v-if="login" style="display: inline-block">
        <el-button class="bottom_tag" v-if="modify" @click="toModify">编辑</el-button>
        <el-button class="bottom_tag" v-if="modify" @click="toDelete">删除</el-button>
        <el-button class="bottom_tag" v-if="review" @click="toReview">评审</el-button>
      </div>
    </div>
    <nobr style="font-weight: 600;margin-left: 10px;">{{authorName}}</nobr>
    <el-tag :key="tag" v-for="tag in keywordTag" class="keyword" v-if="has_keyword">{{tag}}</el-tag>
    <div class="main_text">{{summary}}</div>
    <div v-if="login">
      <v-like v-bind:accountId="accountId"
              v-bind:id="id"
              v-if="toShow">
      </v-like>
      <v-favorite v-bind:accountId="accountId"
                  v-bind:id="id"
                  v-if="toShow">
      </v-favorite>
      <v-follow v-bind:accountId="accountId"
                v-bind:id="id"
                v-bind:authorId="authorId"
                v-if="toShow">
      </v-follow>
    </div>
  </div>
</template>

<script>
  import vFollow from './Follow.vue'
  import vLike from './Like.vue'
  import vFavorite from './Favorite'
  export default {
    name: "Notice",
    props: {
      state:{
        type: String,
        default : "评审中"
      },
      accountId: {
        type: String,
        default: localStorage.getItem("accountId")
      },
      modify:{
        type: Boolean,
        default: false
      },
      id: {
        type: Number,
        default: -1
      },
      type: {
        type: Number,
        default: 1
      },
      authorId: {
        type: Number,
        default: -1
      },
      authorName: {
        type: String,
        default: ''
      },
      keywords: {
        type: String,
        default: ''
      },
      title: {
        type: String,
        default: ''
      },
      summary: {
        type: String,
        default: ""
      },
      user: {
        type: Boolean,
        default: false
      },
      review: {
        type: Boolean,
        default: false
      },
      judge:{
        type: Number,
        default: 0
      }
    },

    data() {
      return {
        keywordTag: [],
        type_name: '',
        toShow: false,
        login: false,
        has_keyword:true,
      }
    },
    components: {
      vFollow,
      vLike,
      vFavorite
    },
    methods: {
      toReview() {
        this.$router.push({
          path: '/review/',
          query: {
            id: this.id
          }
        });
      },
      toModify() {
        if (this.type == 1) {
          this.$router.push({
            path: '/modify_mevid/',
            query: {
              id: this.id
            }
          });
        } else {
          this.$router.push({
            path: '/modify_mguess/',
            query: {
              id: this.id
            }
          });
        }
      },
      toDetail() {
        this.$router.push({
          path: '/detail/',
          query: {
            id: this.id
          }
        });
      },
      toDelete(){
        this.$confirm('确认删除吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          }).then(() => {
            this.sureDelete();
          }).catch(() => {
        });
      },
      sureDelete() {
        var params = new URLSearchParams();
        params.append('id', this.id);
        this.axios.post('/mNotice/deleteNotice', params)
          .then((res) => {
            var remindTitle = res.data === 0 ? '删除微证据成功' : '删除微证据失败';
            var remindContent = res.data === 0 ? '删除微证据成功！' : '好像哪里出了问题/(ㄒoㄒ)/~~再试一次吧';
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
    mounted() {
      this.keywordTag = this.keywords.split('-');
      if(this.keywords.length==0){
        this.has_keyword=false;
      }
      if (this.type === 1) {
        this.type_name = "微证据";
      } else {
        this.type_name = "微猜想";
      }
      this.login = localStorage.getItem("accountId")!=""
      this.toShow= !(this.user||this.review)
      if(this.user){
        this.modify=(this.judge==0);
        if(this.judge==1){
          this.state="已通过";
        }
        else if(this.judge==0){
          this.state="评审中";
        }
        else{
          this.state="未通过";
        }
      }
    },
  }
</script>

<style>
  .notice {
    border: 1px solid #409EFF;
    width: 99%;
    border-radius: 10px;
    margin-bottom: 10px;
  }

  .noice_title {
    color: #409EFF;
    text-align: left;
    font-size: 20px;
    font-weight: 500;
    display: inline-block;
    margin: 10px;
    margin-bottom: 5px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .main_text {
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    margin: 10px;
  }

  .keyword {
    margin-left: 10px;
    border-radius: 30px;
    height: 23px;
  }

  .bottom_tag {
    margin-left: 10px;
    margin-bottom: 5px;
    line-height: 8px;
    height: 32px;
  }
</style>
