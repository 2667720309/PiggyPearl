<template>
  <div>
    <div class="topBar">
      <div>{{ $route.name }}</div>
      <img class="headPortrait card" :src="headUrl" alt="头像" @click="openCenter">
    </div>

    <personalCenter class="personalCenter card"></personalCenter>
  </div>
</template>

<script>
import personalCenter from "./personalCenter";

export default {
  name: "topBar",
  data() {
    return {
      headUrl: 'static/head.png'
    }
  },
  methods: {
    openCenter() {
      let personalCenter = document.querySelector('.personalCenter')
      let app = document.querySelector('#app')

      personalCenter.style.top = '3%'
      app.style.width = '80%'
      app.style.margin = '40px auto 0'
    }
  },
  components: {
    personalCenter
  }
}
</script>

<style scoped>
.topBar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: var(--black);
  font-size: 2em;
  font-weight: bold;
  transition: 0.4s;
}

.headPortrait {
  height: 44px;
  width: 44px;
  border-radius: 22px;
  cursor: pointer;
}

.personalCenter {
  position: fixed;
  left: 0;
  top: 110%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 97%;
  z-index: 110;
  transition: .4s;
}
</style>
