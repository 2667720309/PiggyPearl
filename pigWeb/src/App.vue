<template>
  <div id="app">
    <router-view/>
    <tips></tips>
  </div>
</template>

<script>
import tips from "./components/tips";

export default {
  name: 'App',
  created() {
    // 获取保存的主题色进行更换
    if (localStorage.getItem('theme')) {
      document.documentElement.style.setProperty('--theme', localStorage.getItem('theme'))
      document.documentElement.style.setProperty('--shadow', localStorage.getItem('theme') + '50')
    } else {
      localStorage.setItem('theme', '#6E7FF3')
    }
  },
  components: {
    tips
  }
}
</script>

<style>
:root {
  --theme: #6E7FF3;
  /*--theme: rgb(105, 86, 246);*/
  --shadow: #6E7FF350;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
}
</style>
