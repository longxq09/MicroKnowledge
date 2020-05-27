<template>
  <div>
    <div class="drag-box">
      <div class="drag-box-item">
        <div class="item-title">todo</div>
        <draggable v-model="myArray" @remove="removeHandle" :options="dragOptions">
          <transition-group tag="div" id="todo" class="item-ul">
            <div v-for="item in myArray" class="drag-list" :key="item.id">
              {{item.name}}
            </div>
          </transition-group>
        </draggable>
      </div>
      <div class="drag-box-item">
        <div class="item-title">doing</div>
        <draggable v-model="deleteTopic" @remove="removeHandle" :options="dragOptions">
          <transition-group tag="div" id="doing" class="item-ul">
            <div v-for="item in deleteTopic" class="drag-list" :key="item.id">
              {{item.name}}
            </div>
          </transition-group>
        </draggable>
      </div>
    </div>
  </div>
</template>
<script>
  import draggable from 'vuedraggable'
  export default {
    name: 'TopicManage',
    data() {
      return {
        dragOptions:{
          animation: 120,
          scroll: true,
          group: 'sortlist',
          ghostClass: 'ghost-style'
        },
        myArray: [{name: 1, id: 1}, {name: 2, id: 2}],
        deleteTopic: [{name: 3, id: 3}, {name: 4, id: 4}]
      }
    },
    components: {
      draggable,
    },
    methods: {
      removeHandle(event){
        console.log(event);
        this.$message.success(`从 ${event.from.id} 移动到 ${event.to.id} `);
      }
    }
  }
</script>
<style>
  .drag-box{
    display: flex;
    user-select: none;
  }
  .drag-box-item {
    flex: 1;
    max-width: 330px;
    min-width: 300px;
    background-color: #eff1f5;
    margin-right: 16px;
    border-radius: 6px;
    border: 1px #e1e4e8 solid;
  }
</style>

