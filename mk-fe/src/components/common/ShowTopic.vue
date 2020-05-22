<template>
  <div>
    <el-button v-for="index in str.split('-')"
               :key="index"
               v-if="topics[parseInt(index)]!==undefined"
               @click="toSearch(index)"
               type="success"
               plain
               round
               size="mini"
               style="margin: 4px">{{topics[parseInt(index)].topicName}}
    </el-button>

  </div>
</template>

<script>
  export default {
    name: "ShowTopic",
    props: {
      str: {
        type: String,
        default: ''
      },
    },
    data() {
      return {
        topics: []
      }
    },
    created() {
      this.getTopic()
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
      toSearch(index) {
        this.$router.push({
          path: '/search',
          query: {
            word: '',
            kind: 0,
            topic: index,
          }
        })
      }
    }
  }
</script>
