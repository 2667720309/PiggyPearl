<!--页面边框-->
<template>
  <div id="frame">
    <!--    顶栏-->
    <div
      style="display: flex; align-items: center; justify-content: space-between; width: 100%; height: 60px; top: 5px; position: relative;">
      <div style="display: flex; position: relative; height: 60px; align-items: center; margin-left: 10px;">
        <img src="./assets/logo.png" class="logo" alt="">
        <div style="font-size: 2em; margin-left: 10px; cursor: default; white-space: nowrap; color: var(--theme);">掌上明猪
        </div>
      </div>
      <div style="display: flex; height: 60px; align-items: center; margin-right: 40px;">
        <img src="static/icon/personalCenter.PNG" alt="" class="personalImg">
        <div class="setting" @click="clickSetting">
          <img src="static/icon/setting.PNG" alt="" class="settingImg">
        </div>
      </div>
    </div>
    <!--    导航栏-->
    <div class="navigationBar">
      <div class="navigationBg" style="transform: scale(1, 1);"></div>
      <div class="navigation" v-for="(i, n) in list" @click="jump(i.path, n)"
           @mouseenter="navEnter(i.name)" @mouseleave="navLeave(i.name)" @mousedown="navDown(i.name)"
           @mouseup="navUp(i.name)">
        <img :src="'static/icon/' + i.iconUrl" alt="" class="navigationImg"
             :style="i.name === $route.name ? 'filter: invert(100%)' : ''">
        <div class="navigationName">{{ i.name }}</div>
      </div>
    </div>
    <router-view/>
    <settingPage/>
    <personalCenter/>
    <about></about>

    <!--    右侧覆盖区-->
    <div
      style="position: fixed; z-index: 9; top: 0; right: 0; width: 40px; height: 100%; background: white;"/>
    <!--    圆角覆盖-->
    <div style="position: fixed; z-index: 9; top: 65px; right: 40px; width: 20px; height: 20px; pointer-events: none;
    background-image: radial-gradient(circle at bottom left, rgba(255, 255, 255, 0) 0, rgba(255, 255, 255, 0) 20px, white 20px);"/>
    <div style="position: fixed; z-index: 9; bottom: 20px; right: 40px; width: 20px; height: 20px; pointer-events: none;
    background-image: radial-gradient(circle at top left, rgba(255, 255, 255, 0) 0, rgba(255, 255, 255, 0) 20px, white 20px);"/>
  </div>
</template>

<script>
import settingPage from "./components/settingPage";
import personalCenter from "./components/personalCenter";
import about from "./components/about";
import {typesetting} from "./js/cardController";
import {widthControl} from "./js/weather";

export default {
  name: "frame",
  data() {
    return {
      // 左侧所有导航栏
      list: [
        {path: '/home', iconUrl: 'summarize.PNG', name: '摘要'},
        {path: '/archives', iconUrl: 'archives.PNG', name: '档案'},
        {path: '/monitor', iconUrl: 'monitor.PNG', name: '监控'},
        {path: '/cloud', iconUrl: 'cloud.PNG', name: '云盘'},
        // {path: '/test5', iconUrl: 'default.PNG', name: '测试2'},
      ]
    }
  },
  mounted() {
    let index = this.list.findIndex((value) => {
      return value.name === this.$route.name
    })

    // 控制高亮动画
    document.querySelector('.navigationBg').style.top = -2 + 62 * index + 'px'
  },
  methods: {
    // 页面跳转
    jump(path, n) {
      if (path === this.$route.path) {
        return
      }

      // 控制高亮动画
      document.querySelector('.navigationBg').style.top = -2 + 62 * n + 'px'

      // 页面退出动画
      let home = document.querySelector('#home')
      home.style.transform = 'scale(.6, .6)'
      setTimeout(() => {
        this.$router.push({path: path})
      }, 200)
    },

    // 显示设置页面
    clickSetting() {
      let isCloud = 0
      setTimeout(() => {
        widthControl()
      }, 600)

      // 如果是云盘界面，开启设置时先隐藏图片后再显示
      // 否则图片会瞬移
      if (this.$route.name === '云盘') {
        isCloud = 100
        let image = this.$el.getElementsByClassName('image')
        for (let i = 0; i < image.length; i++) {
          image[i].style.opacity = '0'
        }
        setTimeout(() => {
          for (let i = 0; i < image.length; i++) {
            image[i].style.opacity = '1'
          }
        }, 600 + isCloud)
      }

      // 控制设置页面的载入载出动画
      setTimeout(() => {
        let set = this.$el.getElementsByClassName('settingImg')[0]
        let setPage = this.$el.getElementsByClassName('settingPage')[0]
        let home = document.querySelector('#home')
        if (this.$store.state.isSettingOpen) {
          set.style.transform = 'rotate(0)'
          setPage.style.width = '0'
          setPage.style.transform = 'translate(20px)'
          home.style.width = home.getBoundingClientRect().width + (300 + 20) + 'px'
        } else {
          set.style.transform = 'rotate(180deg)'
          setPage.style.width = '300px'
          setPage.style.transform = 'translate(0)'
          home.style.width = home.getBoundingClientRect().width - (300 + 20) + 'px'
        }
        this.$store.state.isSettingOpen = !this.$store.state.isSettingOpen
        if (this.$route.name === '档案') { // 如果是档案页，执行一次卡片排版
          typesetting()
        }
      }, isCloud)
    },

    // 控制导航栏上的高亮动画
    navEnter(name) {
      if (name === this.$route.name) {
        let navBg = document.querySelector('.navigationBg')
        navBg.style.width = '34px'
        navBg.style.height = '34px'
        navBg.style.left = '13px'
      }
    },
    navLeave(name) {
      if (name === this.$route.name) {
        let navBg = document.querySelector('.navigationBg')
        navBg.style.width = '44px'
        navBg.style.height = '44px'
        navBg.style.left = '8px'
      }
    },
    navDown(name) {
      if (name === this.$route.name) {
        document.querySelector('.navigationBg').style.transform = 'scale(.8, .8)'
      }
    },
    navUp(name) {
      if (name === this.$route.name) {
        document.querySelector('.navigationBg').style.transform = 'scale(1, 1)'
      }
    }
  },
  components: {
    settingPage, personalCenter, about
  }
}
</script>

<style scoped>
#frame {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: white;
  z-index: 5;
}

.logo {
  height: 60px;
  transform: translate(-100px);
  border-radius: 5px;
  animation: reLogo linear infinite;
  animation-duration: 1s;
  filter: drop-shadow(100px 0 0 var(--theme));
  box-shadow: 0 0 8px var(--shadow);
}

.navigationBar {
  position: relative;
  left: 10px;
  top: 15px;
  width: 60px;
  height: 500px;
}

.navigation {
  margin-top: 10px;
  height: 70px;
  margin-bottom: -18px;
  cursor: pointer;
  position: relative;
}

.navigation:hover > .navigationName {
  opacity: 1;
}

.navigation:hover > .navigationImg {
  width: 30px;
}

.navigation:active > .navigationImg {
  transform: scale(.8, .8);
}

.navigation:active > .navigationName {
  font-size: .8em;
}

.navigationName {
  transform: translate(0, -6px);
  opacity: 0;
  color: var(--theme);
  transition: opacity .4s, font-size .15s;
}

.navigationImg {
  position: relative;
  /*transform: translateX(-100px);*/
  filter: invert(50%);
  /*filter: drop-shadow(100px 0 0 var(--theme));*/
  width: 40px;
  transition: width .4s, transform .15s, filter .15s;
}

.navigationBg {
  position: absolute;
  background: var(--theme);
  border-radius: 5px;
  box-shadow: 1px 1px 4px var(--shadow);
  left: 8px;
  top: -2px;
  width: 44px;
  height: 44px;
  transition: width .4s, height .4s, transform .15s, left .4s, top .3s;
}

.setting {
  margin-left: 20px;
  width: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
}

.settingImg {
  height: 40px;
  width: 40px;
  filter: invert(50%);
  transform: rotate(0);
  transition: height .4s, width .4s, transform .6s ease-in-out;
}

.setting:hover > .settingImg {
  height: 32px;
  width: 32px;
}

.personalImg {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  filter: invert(100%);
}

/*logo刷新*/
@keyframes reLogo {
  0% {
    /*filter: drop-shadow(100px 0 0 var(--theme));*/
    opacity: 1;
  }
  50% {
    /*filter: drop-shadow(99px 0 0 var(--theme));*/
    opacity: .99;
  }
  100% {
    /*filter: drop-shadow(100px 0 0 var(--theme));*/
    opacity: 1;
  }
}
</style>
