<template>
  <div>
    <v-page v-bind:display="exhibition"></v-page>
<!--    <v-notice :key="value.id" v-for="(value,index) in exhibition"-->
<!--              v-bind:id="value.id"-->
<!--              v-bind:type="value.type"-->
<!--              v-bind:authorId="value.authorID"-->
<!--              v-bind:authorName="value.authorName"-->
<!--              v-bind:keywords="value.keywords"-->
<!--              v-bind:title="value.title"-->
<!--              v-bind:summary="value.summary">-->
<!--    </v-notice>-->
  </div>
</template>

<script>
  import vNotice from './Notice.vue';
  import vPage from './Page.vue'
  export default {
    name: "Hot",
    props: {
      user: {
        type: Boolean,
        default: false
      },
    },
    data() {
      return {
        exhibition: Array,
      }
    },
    components: {
      vNotice,
      vPage
    },
    mounted() {
      this.getNoticeList()
    },
    methods: {
      getNoticeList() {
        this.axios.get('/mNotice/getHotTemp')
          .then((res) => {
            this.exhibition = res.data
          })
          .catch((error) => {
            console.log(error)
          });
      },
    }
  }
</script>
