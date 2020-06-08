<template>
  <div class="topic-manage">
    <div class="drag-box">
      <div class="drag-box-item">
        <div>
          <div class="drag-title" style="display: inline-block">分类</div>
          <el-button icon="el-icon-circle-plus-outline"
                     type="primary"
                     @click="addTopic">
          </el-button>
        </div>
        <transition-group tag="div" id="enable" class="item-ul">
          <div v-for="(item, index) in usedTopic" class="drag-list" :key="item.id" @click="updateTopic(item.id)">
            {{item.topicName}}
            <el-button icon="el-icon-edit" style="border: none" @click="edit(0, index)"></el-button>
          </div>
        </transition-group>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'TopicManage',
    data() {
      return {
        usedTopic: [],
      }
    },
    mounted() {
      this.getTopic()
    },
    methods: {
      getTopic() {
        this.axios.get('/topic/getTopicList')
          .then((res) => {
            this.usedTopic = res.data
          })
          .catch((error) => {
            console.log(error)
          })
      },
      updateTopic(topic) {
        let params = new URLSearchParams()
        params.append('id', topic.id)
        params.append('topicName', topic.topicName)
        this.axios.post('/topic/updateTopic', params)
          .then((res)=>{
            console.log(res)
          })
          .catch((res)=>{
            console.log(res.message);
          });
      },
      edit(type, index) {
        this.$prompt('请输入新分类名称', '编辑分类', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          let topic = type === 0 ? this.usedTopic[index] : this.deleteTopic[index]
          topic.topicName = value
          this.updateTopic(topic)
          this.$message({
            type: 'success',
            message: '编辑成功'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      addTopic() {
        this.$prompt('请输入新分类名称', '新增分类', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          let params = new URLSearchParams()
          params.append('topicName', value)
          this.axios.post('/topic/addTopic', params)
            .then((res)=>{
              console.log(res)
            })
            .catch((res)=>{
              console.log(res.message);
            });
          this.$message({
            type: 'success',
            message: '新增成功'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      }
    }
  }
</script>
<style scoped>
  .topic-manage {
    margin: 20px;
  }
  .drag-box{
    display: flex;
    user-select: none;
  }
  .drag-box-item {
    flex: 1;
    width: 900px;
    background-color: #eff1f5;
    margin-right: 16px;
    border-radius: 6px;
    border: 1px #e1e4e8 solid;
  }
  .item-ul{
    padding: 0 8px 8px;
    height: 500px;
    overflow-y: scroll;
  }
  .item-ul::-webkit-scrollbar{
    width: 0;
  }
  .drag-list {
    display: inline-block;
    border: 1px #e1e4e8 solid;
    padding: 10px;
    margin: 5px 4px 10px;
    list-style: none;
    background-color: #fff;
    border-radius: 6px;
    cursor: pointer;
    -webkit-transition: border .3s ease-in;
    transition: border .3s ease-in;
  }
  .drag-list:hover {
    border: 1px solid #20a0ff;
  }
  .drag-title {
    padding: 8px 8px 8px 12px;
    font-size: 14px;
    line-height: 1.5;
    color: #24292e;
    font-weight: 600;
  }
  .ghost-style{
    display: block;
    color: transparent;
    border-style: dashed
  }
</style>

