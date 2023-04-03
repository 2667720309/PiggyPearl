<template>
  <div id="home" @scroll="scrollCheck">
    <div class="topBar" style="opacity: 0">
      <div style="font-size: 1.8em; cursor: default;">图库</div>
      <div style="font-size: 1.4em;">{{ barDate }}</div>
    </div>

    <div style="overflow-y: auto; top: 54px; position: relative;">
      <div v-for="i in list">
        <div class="date">{{ i.date.year }}年{{ i.date.month }}月</div>
        <div style="display: flex; margin: 0 15px; flex-wrap: wrap;">
          <div class="image" v-for="j in i.data" @click="show(j)" style="cursor: pointer;">
            <img :src="'static/cloudImg/' + j" alt=""
                 style="width: 100%; height: 100%; object-fit: cover; position: relative; left: 50%; top: 50%; transform: translate(-50%, -50%);">
          </div>
        </div>
      </div>
      <div style="position: relative; width: 100%; height: 40px;"></div>
    </div>

    <div class="distinguish">
      <img src="" alt="" style="height: 280px; width: 500px; object-fit: cover; border-radius: 20px;">
      <div class="borders"></div>
      <div style="display: flex; justify-content: space-around; width: 100%; margin-top: 20px;">
        <div style="text-align: right; font-size: 1.5em;">
          <div style="background: #888888; color: black; cursor: pointer; width: 48px;
               padding: 0 10px; border-radius: 20px; box-shadow: 0 0 4px gold;" @click="dist">识别</div>
        </div>
        <div style="text-align: right; font-size: 1.5em;">
          <div style="background: #888888; color: black; cursor: pointer; width: 48px;
               padding: 0 10px; border-radius: 20px; box-shadow: 0 0 4px gold;" @click="hide">关闭</div>
        </div>
      </div>
      <div style="position: absolute; pointer-events: none; width: 0; height: 0; opacity: 0;"></div>
    </div>
    <loading id="loading"></loading>
    <div class="mask"></div>
  </div>
</template>

<script>
import {mainPageInter} from "../js/mainPageInter";
import loading from "../components/loading";
import {pigCloud, getIndex} from "../js/pigCloud";

export default {
  name: "cloud",
  data() {
    return {
      list: [],
      barDate: '',
      borders: []
    }
  },
  created() {
    this.list = this.date2Month(pigCloud)
    this.barDate = this.list[0].date.year + '年' + this.list[0].date.month + '月'
  },
  mounted() {
    let window = mainPageInter(4)
    document.querySelector('.topBar').style.width = window.width - 40 + 'px'
    setTimeout(() => {
      document.querySelector('.topBar').style.opacity = '1'
    }, 600)
    this.scrollCheck()

    let mask = this.$el.querySelector('.mask')
    mask.style.width = window.width + 'px'
    mask.style.height = window.height + 'px'
  },
  methods: {
    // 将图片按照月份分类
    date2Month(list) {
      let res = [], pointer = 0
      let year = list[0].lastModifiedDate.year
      let month = list[0].lastModifiedDate.month
      res.push({date: {year: year, month: month}, data: []})
      for (let i = 0; i < list.length; i++) {
        if (list[i].lastModifiedDate.year === year && list[i].lastModifiedDate.month === month) {
          res[pointer].data.push(list[i].url)
        } else {
          year = list[i].lastModifiedDate.year
          month = list[i].lastModifiedDate.month
          res.push({date: {year: year, month: month}, data: []})
          pointer += 1
        }
      }
      return res
    },

    // 控制top-bar里显示的月份
    scrollCheck() {
      let dates = this.$el.getElementsByClassName('date')
      let barDate = ''
      for (let i = 0; i < dates.length; i++) {
        if (dates[i].getBoundingClientRect().top < 140) {
          dates[i].style.opacity = '0'
          barDate = dates[i].textContent
        } else {
          dates[i].style.opacity = '1'
        }
      }
      this.barDate = barDate
    },

    // 显示聚焦卡片
    show(url) {
      this.$el.querySelector('.mask').style.opacity = '1'
      this.$el.querySelector('.mask').style.pointerEvents = 'auto'

      let show = this.$el.querySelector('.distinguish')
      show.style.opacity = '1'
      show.style.pointerEvents = 'auto'

      show.children[0].src = 'static/cloudImg/' + url
      show.children[3].textContent = url

      this.borders.forEach((item) => {
        item.remove()
      })
    },

    // 隐藏聚焦卡片
    hide() {
      this.$el.querySelector('.mask').style.opacity = '0'
      this.$el.querySelector('.mask').style.pointerEvents = 'none'

      let show = this.$el.querySelector('.distinguish')
      show.style.opacity = '0'
      show.style.pointerEvents = 'none'

      this.borders.forEach((item) => {
        item.remove()
      })
    },

    // 根据识别结果显示红框
    dist() {
      let loading = this.$el.querySelector('#loading')

      loading.style.opacity = '1'
      setTimeout(() => {
        loading.style.opacity = '0'
        let text = this.$el.querySelector('.distinguish').children[3].textContent
        let father = this.$el.querySelector('.distinguish').children[1]
        // console.log(getIndex(text))
        pigCloud[getIndex(text)].border.forEach((item) => {
          let newDiv = document.createElement('div')
          newDiv.classList.add('border')
          newDiv.style.position = 'absolute'
          newDiv.style.border = '2px solid #ff2e63'
          newDiv.style.top = item.y + 'px'
          newDiv.style.left = item.x + 'px'
          newDiv.style.height = item.h + 'px'
          newDiv.style.width = item.w + 'px'
          this.borders.push(newDiv)
          father.appendChild(newDiv)
        })
      }, 1340)
    }
  },
  components: {
    loading
  }
}
</script>

<style scoped>
#home {
  position: absolute;
  background: rgb(250, 250, 250);
  top: 65px;
  left: 75px;
  width: 1400px;
  height: 800px;
  border-radius: 20px;
  color: black;
  overflow-x: hidden;
  box-shadow: var(--shadow) 1px 1px 4px inset;
  transition: .6s ease-in-out;
}

.topBar {
  position: fixed;
  text-align: left;
  top: 60px;
  padding: 20px;
  width: 100%;
  background: linear-gradient(rgba(255, 255, 255, .8), rgba(255, 255, 255, 0));
  pointer-events: none;
  z-index: 2;
  transition: opacity .4s;
}

.date {
  text-align: left;
  margin-left: 20px;
  font-size: 1.4em;
  transition: .4s;
}

.image {
  overflow: hidden;
  height: 200px;
  width: 200px;
  padding: 5px;
  transition: .2s;
}

.image:hover {
  transform: scale(1.1, 1.1);
}

.distinguish {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 500px;
  height: 340px;
  border-radius: 20px;
  padding: 20px;
  background: rgba(255, 255, 255, .4);
  backdrop-filter: blur(8px);
  box-shadow: 1px 1px 4px var(--shadow);
  overflow: hidden;
  transition: .4s;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  pointer-events: none;
  opacity: 0;
  z-index: 10;
}

.borders {
  position: absolute;
  height: 280px;
  width: 500px;
  z-index: 11;
}

#loading {
  opacity: 0;
}

.mask {
  z-index: 9;
  backdrop-filter: blur(20px);
  opacity: 0;
  position: fixed;
  left: 75px;
  top: 65px;
  width: 100px;
  height: 100px;
  border-radius: 20px;
  pointer-events: none;
  transition: .4s;
}
</style>
