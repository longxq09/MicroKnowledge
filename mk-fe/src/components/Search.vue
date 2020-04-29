<template>
	<el-container>
		<el-header>
			<v-head></v-head>
		</el-header>
		<el-main>
		  <div class="filter">
		    <span>关键字：</span>
		    <el-input
		      style="width: 30%; margin-right: 20px"
		      v-model="content"
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
          @click="getSearchResult">
          高级搜索
        </el-button>
      </div>
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
        content: ''
      }
    },
		components: {
			vHead,
			vFooter,
			vNotice
		},
		mounted() {
      this.getTopic();
      this.content = this.$route.query.content
    },
		methods: {
		  getTopic() {
		    this.axios.get('/topic')
          .then((res) => {
            this.topics = res.data
          })
          .catch((error) => {
            console.log(error)
          })
		  },
		  getSearchResult() {
		    var params = new URLSearchParams()
		    params.append('word', this.content)
		    params.append('kind', 0)
		    params.append('topic', '')
		    this.axios.get('/search', params)
		      .then((res) => {
		        console.log(res.data)
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
  }
</style>
