<template>
  <div>
    <div v-for="index in str.split('-')" :key="index">
      <el-tag v-if="topics[parseInt(index)]!==undefined">{{topics[parseInt(index)].topicName}}</el-tag>
    </div>
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
      }
    }
  }
</script>
