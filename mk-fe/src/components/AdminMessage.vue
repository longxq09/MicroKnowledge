<template>
  <div style="width: 900px; margin: 5%;">
    <v-comment :key="value.id" v-for="(value,index) in message"
     v-bind:fromId="value.fromUserId" v-bind:fromName="value.fromUserName"
     v-bind:content="value.detail" v-bind:time="value.disTime"
     v-bind:id="value.id" v-bind:flag="value.flag"></v-comment>
  </div>
</template>
<script>
  import vComment from './common/AdminComment.vue';
  export default {
    name: 'adminMessage',
    data() {
      return {
        message: Array,
      }
    },
    components: {
      vComment,
    },
    mounted() {
      this.axios.get('/message/getAdminMessage').then((res) => {
          this.message = res.data
          console.log(this.message);
        })
        .catch((error) => {
          console.log(error)
        })
    }
  }
</script>
