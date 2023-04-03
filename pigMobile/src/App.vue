<template>
  <div id="app">
    <router-view/>
    <widthLimitMask></widthLimitMask>
  </div>
</template>

<script>
import widthLimitMask from "./components/widthLimitMask";
import {dayNightSwitch, autoDayNightSwitch} from "./components/darkMode/darkMode";

export default {
  name: 'App',
  created() {
    if (localStorage.getItem('isDark') === 'true') {
      if (localStorage.getItem('isAutoDark') === 'true') {
        autoDayNightSwitch()
      } else {
        dayNightSwitch()
      }
    }
  },
  components: {
    widthLimitMask
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin: 20px auto 0;
  width: 90%;
  transition: .4s;
}
</style>
