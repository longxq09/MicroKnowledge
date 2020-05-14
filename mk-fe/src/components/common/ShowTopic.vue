<template>
  <div>
    <div v-for="topic in topicTags" :key="topic">
      <el-tag>{{topic}}</el-tag>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue';
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
        topicTags: []
      }
    },
    created() {
      this.getTopic()
    },
    methods: {
      getTopic() {
        this.axios.get('/topic/getTopicList')
          .then((res) => {
            let tags = this.str.split('-')
            for(let i = 0; i < tags.length; i++)
              this.topicTags.push(res.data[parseInt(tags[i])].topicName)
            this.$forceUpdate()
          })
          .catch((error) => {
            console.log(error)
          })
      }
    }
  }
</script>
