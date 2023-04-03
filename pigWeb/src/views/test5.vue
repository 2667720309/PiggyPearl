<template>
  <div id="home">
<!--    写页面代码-->
    <div></div>
<!--    布局 https://www.ruanyifeng.com/blog/2015/07/flex-grammar.html-->
<!--    https://vuejs.org/api/-->
<!--    v-text-->
<!--    v-html-->
<!--    v-show-->
<!--    v-if-->
<!--    v-else-->
<!--    v-else-if-->
<!--    v-for-->
<!--    v-on-->
<!--    v-bind-->
<!--    v-model-->
<!--    请求 axios-->
  </div>
</template>

<script>
import {mainPageInter} from "../js/mainPageInter";

export default {
  name: "test5", // name和文件名一致
  data() {
    return {
      // 私有常变量位置
      count: 0
    }
  },
  created() {
    // 进入页面时执行这段代码块
  },
  mounted() {
    // dom树加载完毕后执行这段代码块
    mainPageInter(5)
  },
  updated() {
    // 有私有变量被改变时执行这段代码块
  },
  destroyed() {
    // 页面退出时执行这段代码块
  },
  methods: {
    // 定义私有函数
  },
  components: {
    // 添加其他vue组件页面
  }
}
</script>

<style scoped>
/*私有布局class位置*/
#home {
  position: absolute;
  background: rgb(250, 250, 250);
  top: 65px;
  left: 75px;
  width: 1400px;
  height: 800px;
  border-radius: 20px;
  color: black;
  box-shadow: var(--shadow) 1px 1px 4px inset;
  transition: .6s ease-in-out;
}
</style>
