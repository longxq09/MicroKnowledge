<template>
  <div style="margin: 40px 0 40px 80px; width: 940px">
    <div class="filter">
      <span>关键字：</span>
      <el-input
        style="width: 180px; margin-right: 16px"
        v-model="dataForm.word"
        placeholder="请输入内容">
      </el-input>
      <span>类型：</span>
      <el-select
        style="width: 200px; margin-right: 16px"
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
      <span>分类：</span>
      <el-select
        style="width: 250px"
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
        style="width: 80px; float: right; text-align: center"
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
              v-bind:authorId="value.authorID"
              v-bind:keywords="value.keywords"
              v-bind:title="value.title"
              v-bind:summary="value.summary">
    </v-notice>
  </div>
</template>

<script>
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
      vNotice
    },
    created() {
      this.getTopic();
      this.getSearchResult()
    },
    watch: {
      $route() {
        this.getSearchResult()
      },
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
        if (this.filterType.length === 1) {
          this.dataForm.kind = this.filterType[0] === '1' ? 1 : 2
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
        this.filterType = this.dataForm.kind === 1 ? [1] : this.dataForm.kind === 2 ? [2] : [1, 2]
        this.dataForm.topic = this.$route.query.topic
        if (this.dataForm.topic !== '') {
          let t = this.dataForm.topic.split('-')
          for (let i = 0; i < t.length; i++)
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
  .filter {
    margin-bottom: 20px;
  }
</style>
