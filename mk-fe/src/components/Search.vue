<template>
  <el-container>
    <el-header>
      <v-head v-bind:search="true"></v-head>
    </el-header>
    <el-main>
      <div class="filter">
        <span>关键字：</span>
        <el-input
          style="width: 30%; margin-right: 20px"
          v-model="dataForm.word"
          placeholder="请输入内容">
        </el-input>
        <span>类型：</span>
        <el-select
          style="width: 10%; margin-right: 20px"
          v-model="filterType"
          multiple
          placeholder="请选择">
          <el-option
            v-for="item in types"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <span>主题：</span>
        <el-select
          style="width: 30%"
          v-model="filterTopic"
          multiple
          placeholder="请选择">
          <el-option
            v-for="(value, index) in topics"
            :key="value.id"
            :label="value.topicName"
            :value="value.id">
          </el-option>
        </el-select>
        <el-button
          style="width: 8%; float: right"
          type="primary"
          round
          @click="click">
          高级搜索
        </el-button>
      </div>
      <p>找到{{resultNum}}条结果。</p>
      <v-notice :key="value.id" v-for="(value,index) in searchResult"
                v-bind:id="value.id"
                v-bind:type="value.type"
                v-bind:authorName="value.authorName"
                v-bind:keywords="value.keywords"
                v-bind:title="value.title"
                v-bind:summary="value.summary"
                v-bind:user="true">
      </v-notice>
    </el-main>
    <v-footer></v-footer>
  </el-container>
</template>

<script>
  import vHead from './common/Header.vue';
  import vFooter from './common/Footer.vue';
  import vNotice from './common/Notice.vue';
  export default {
    name: "Search",
    data() {
      return {
        types: [{
          value: 1,
          label: '微证据'
        }, {
          value: 2,
          label: '微猜想'
        }],
        topics: Array,
        filterType: [],
        filterTopic: [],
        dataForm: {word: '', topic: '', kind: 0},
        searchResult: Array,
        resultNum: 0
      }
    },
    components: {
      vHead,
      vFooter,
      vNotice
    },
    mounted() {
      this.getTopic();
      this.getSearchResult()
    },
    watch: {
      "$route": "getSearchResult"
    },
    methods: {
      getTopic() {
        this.axios.get('/topic/getTopicList')
          .then((res) => {
            this.topics = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      },
      click() {
        this.dataForm.kind = 0
        if (this.filterType.length == 1) {
          this.dataForm.kind = this.filterType[0] == '1' ? 1 : 2
        }
        this.dataForm.topic = ''
        for (var i = 0; i < this.filterTopic.length; i++) {
          this.dataForm.topic = this.dataForm.topic + this.filterTopic[i].toString() + "-"
        }
        this.dataForm.topic = this.dataForm.topic.slice(0, -1)
        this.$router.push({
          query: {
            word: this.dataForm.word,
            kind: this.dataForm.kind,
            topic: this.dataForm.topic
          }
        }).catch(err => {err})
      },
      getSearchResult() {
        this.dataForm.word = this.$route.query.word
        this.dataForm.kind = this.$route.query.kind
        this.filterType = this.dataForm.kind == 1 ? [1] : this.dataForm.kind == 2 ? [2] : [1, 2]
        this.dataForm.topic = this.$route.query.topic
        this.filterTopic = []
        if (this.dataForm.topic != '') {
          var t = this.dataForm.topic.split('-')
          for (var i = 0; i < t.length; i++)
            this.filterTopic.push(parseInt(t[i]))
        }
        this.axios.get('/search', {params: this.dataForm})
          .then((res) => {
            this.searchResult = res.data.notice
            this.resultNum = res.data.notice.length
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
  }
</script>
<style>
  .el-main {
    background-color: #F4F4F5;
    color: #333;
  }
  .filter {
    margin-bottom: 20px;
  }
</style>
