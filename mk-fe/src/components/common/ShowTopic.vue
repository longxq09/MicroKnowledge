<template>
  <div>
    <el-tag v-for="index in str.split('-')"
            :key="index"
            v-if="topics[parseInt(index)]!==undefined"
            type="success"
            style="margin: 4px">{{topics[parseInt(index)].topicName}}
    </el-tag>
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
