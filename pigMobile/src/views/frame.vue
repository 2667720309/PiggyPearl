<template>
  <div>
    <router-view></router-view>
    <div style="position: absolute; height: 80px; margin-top: 60px; width: 2px;"></div>
    <div class="card tabBar">
      <div class="item" v-for="item in items" @click="toItem(item.path)">
        <img :src="item.url" :alt="item.name">
        <h6>{{ item.name }}</h6>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "frame",
  data() {
    return {
      items: [
        {
          url: 'static/icon/home.png',
          name: '首页',
          path: '/app/home'
        },
        {
          url: 'static/icon/pigInformation.png',
          name: '猪脸',
          path: '/app/pigInformation'
        },
        {
          url: 'static/icon/newsInformation.png',
          name: '新闻',
          path: '/app/newsInformation'
        }
      ]
    }
  },
  methods: {
    toItem(path) {
      this.$router.push({
        path: path,
        query: {
          page: this.$route.name
        }
      })
    }
  },
  mounted() {
    let tabBar = document.getElementsByClassName('tabBar')
    setTimeout(() => {
      tabBar[0].style.bottom = '20px'
    }, 20)
  }
}
</script>

<style scoped>
.tabBar {
  height: 80px;
  width: 90%;
  display: flex;
  position: fixed;
  justify-content: space-around;
  align-items: center;
  left: 5%;
  bottom: -100px;
  cursor: pointer;
  transition: 0.8s cubic-bezier(.17, .67, .46, 1.22);
  z-index: 100;
  /*background: white;*/
}

.item {
  height: 90%;
  width: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: var(--black);
}

.item > img {
  height: 70%;
  /*width: 100%;*/
}

.item > h6 {
  left: 19%;
  position: relative;
}

</style>
