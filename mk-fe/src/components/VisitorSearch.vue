<template>
  <div>
    <v-head style="top: 0"></v-head>
    <v-search style="position: absolute; top: 60px"></v-search>
  </div>
</template>
<script>
  import vHead from './common/Header'
  import vSearch from './Search.vue'
  export default {
    name: 'VisitorSearch',
    components: {
      vHead,
      vSearch
    }
  }
</script>
