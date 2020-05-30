<template>
  <div>
        <v-notice :key="value.id" v-for="(value,index) in display.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                  v-bind:id="value.id"
                  v-bind:authorId="value.authorID"
                  v-bind:type="value.type"
                  v-bind:authorName="value.authorName"
                  v-bind:keywords="value.keywords"
                  v-bind:title="value.title"
                  v-bind:summary="value.summary"
                  v-bind:review="review"
                  v-bind:comment="comment"
                  v-bind:user="user"
                  v-bind:judge="value.judge">
        </v-notice>

    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[3, 5, 10, 20]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="display.length">
    </el-pagination>
  </div>
</template>
<script>
  import vNotice from './Notice.vue';
  export default {
    name: "Home",
    props: {
      display: {
        type: Array,
        default: null
      },
      modify: {
        type: Boolean,
        default: false
      },
      user: {
        type: Boolean,
        default: false
      },
      review: {
        type: Boolean,
        default: false
      },
      judge: {
        type: Number,
        default: 0
      },
      comment: {
        type: Boolean,
        default: true
      },
    },
    data() {
      return {
        currentPage: 1,
        pageSize: 5,

        tableData: []
      }
    },
    components: {
      vNotice,
    },
    mounted() {
    },
    methods:{

            handleSizeChange(val) {
              this.pageSize = val;
              console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
              this.currentPage = val;
              console.log(`当前页: ${val}`);
            }

    }

  }
</script>

<style>
</style>
