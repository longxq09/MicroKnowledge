<template>
  <svg :width='w' :height='height' @mousemove='listener($event)'>
    <a v-for='tag in tags' @click="toSearch(tag.id)">
      <text :x='tag.x' :y='tag.y' :font-size='10 * (500/(500-tag.z))' :fill='tag.color'
            :fill-opacity='((300+tag.z)/400)'>{{tag.text}}
      </text>
    </a>
  </svg>
</template>
<script>
  export default {
    data() {
      return {
        w: 300,
        width: 200,
        height: 200,
        RADIUS: 100,
        speedX: Math.PI/360,
        speedY: Math.PI/360,
        tags: []
      }
    },
    computed:{
      CX(){
        return this.width / 2;
      },
      CY(){
        return this.height / 2;
      }
    },
    created(){//初始化标签位置
      this.getTags()
    },
    mounted(){//使球开始旋转
      this.tags = []
      setInterval(() => {
        this.rotateX(this.speedX);
        this.rotateY(this.speedY);
      }, 15)
    },
    methods: {
      getTags() {
        this.axios.get('/topic/getTopicList')
          .then((res) => {
            let keys = Object.keys(res.data)
            let len = keys.length
            let tags=[];
            for(let i = 0; i < len; i++){
              let tag = {};
              let k = -1 + (2 * (i + 1) - 1) / len;
              let a = Math.acos(k);
              let b = a * Math.sqrt(len * Math.PI);
              tag.text = res.data[keys[i]].topicName;
              tag.x = this.CX +  this.RADIUS * Math.sin(a) * Math.cos(b);//根据标签角度求出标签的x,y,z坐标
              tag.y = this.CY +  this.RADIUS * Math.sin(a) * Math.sin(b);
              tag.z = this.RADIUS * Math.cos(a);
              tag.id = res.data[keys[i]].id
              tag.color = this.randomColor();
              tags.push(tag);
            }
            this.tags = tags
          })
          .catch((error) => {
            console.log(error)
          })
      },
      randomColor() {
        let r, g, b;
        r = Math.floor(Math.random() * 256);
        g = Math.floor(Math.random() * 256);
        b = Math.floor(Math.random() * 256);
        return "rgb(" +r + ',' +g+ ',' +b+ ")";
      },
      rotateX(angleX){
        var cos = Math.cos(angleX);
        var sin = Math.sin(angleX);
        for(let tag of this.tags){
          var y1 = (tag.y- this.CY) * cos - tag.z * sin  + this.CY;
          var z1 = tag.z * cos + (tag.y- this.CY) * sin;
          tag.y = y1;
          tag.z = z1;
        }
      },
      rotateY(angleY){
        var cos = Math.cos(angleY);
        var sin = Math.sin(angleY);
        for(let tag of this.tags){
          var x1 = (tag.x - this.CX) * cos - tag.z * sin + this.CX;
          var z1 = tag.z * cos + (tag.x - this.CX) * sin;
          tag.x = x1;
          tag.z = z1;
        }
      },
      listener(event){
        let x = event.clientX - this.CX;
        let y = event.clientY - this.CY;
        this.speedX = x*0.0001>0 ? Math.min(this.RADIUS*0.00002, x*0.0001) : Math.max(-this.RADIUS*0.00002, x*0.0001);
        this.speedY = y*0.0001>0 ? Math.min(this.RADIUS*0.00002, y*0.0001) : Math.max(-this.RADIUS*0.00002, y*0.0001);
      },
      toSearch(id) {
        this.$router.push({
          path: '/search',
          query: {
            word: '',
            kind: 0,
            topic: id.toString()
          }
        }).catch(err => {err})
      }
    }
  }
</script>
<style>
  a:hover{
    cursor:pointer
  }
</style>
