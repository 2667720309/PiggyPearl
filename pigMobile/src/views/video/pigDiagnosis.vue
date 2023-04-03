<template>
  <div style="transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
    <topBar></topBar>

    <!--    背景球-->
    <div class="ball"
         style="background: #f9d909; width: 300px; height: 300px; top: -80%; left: 14%; animation-delay: 1.8s;"></div>
    <div class="ball"
         style="background: #ea0b0b; width: 240px; height: 240px; top: -12%; left: 174%; animation-delay: 2.6s;"></div>
    <div class="ball"
         style="background: #b509f9; width: 120px; height: 120px; top: 108%; left: 149%; animation-delay: 2.1s;"></div>
    <div class="ball"
         style="background: #09acf9; width: 180px; height: 180px; top: 113%; left: -26%; animation-delay: 2.9s;"></div>
    <div class="ball"
         style="background: #09f95e; width: 260px; height: 260px; top: 20%; left: -89%; animation-delay: 4.1s;"></div>

    <!--    上传按钮-->
    <div
      style="position: fixed; top: 40%; left: 0; transform: translate(0, -50%); width: 100%; display: flex; justify-content: center;">
      <div class="card upload" @click="uploadClick($event)">
        <input type="file" style="display: none;" @change="uploadPictures($event)" accept="image/*">
        <div
          style="margin-top: 20px; display: flex; align-items: center; justify-content: center; font-weight: bold; transition: .4s; transition-delay: .4s; pointer-events: none;">
          上传猪正脸
        </div>
        <div
          style="position: relative; top: 0; left: 50%; transform: translate(-50%, 0); width: 0; transition: .4s; transition-delay: .4s; overflow: hidden; pointer-events: none;">
          <img src="static/icon/check_solid.png" alt=""
               style="position: relative; top: 0; left: 0; width: 50px; transition: .4s; z-index: 2;">
        </div>
      </div>
      <div style="position: relative; top: 0; height: 100%; width: 40px;"></div>
      <div class="card upload" @click="uploadClick($event)">
        <input type="file" style="display: none;" @change="uploadAudio($event)" accept="audio/*">
        <div
          style="margin-top: 20px; display: flex; align-items: center; justify-content: center; font-weight: bold; transition: .4s; transition-delay: .4s; pointer-events: none;">
          上传猪叫声
        </div>
        <div
          style="position: relative; top: 0; left: 50%; transform: translate(-50%, 0); width: 0; transition: .4s; transition-delay: .4s; overflow: hidden; pointer-events: none;">
          <img src="static/icon/check_hollow.png" alt=""
               style="position: relative; top: 0; left: 0; width: 50px; transition: .4s;">
        </div>
      </div>
    </div>

    <!--    加载框-->
    <div class="card loading">
      <div class="rotator"></div>
      <div
        style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border-radius: 50%; z-index: 5; overflow: hidden; transition: .4s;">
        <img src="" alt="" style="position: relative; width: 100%;">
      </div>
    </div>

    <!--    结果框-->
    <div class="result">
      <div class="assembly">
        <div class="header" style="position: relative; margin-left: 5%; text-align: end; width: 90%;">识别结果</div>
        <div class="card zoom" style="width: 100%; margin-top: 10px; padding: 10px 5%; text-align: start; overflow: hidden;">
          <h4>&nbsp;</h4>
          <h4>此猪状态良好，暂无明显生理疾病表现。</h4>
        </div>
      </div>
      <div class="assembly" style="margin-top: 20px;">
        <div class="card zoom" style="width: 100%; margin-top: 10px; padding: 10px 5%; text-align: start; overflow: hidden;">
          <h4>发情概率：</h4>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <h4 style="color: rgba(255, 48, 0, 1);">13.6%</h4>
            <movableRing v-if="true"
                         :ringData="[{value: 13.6, max: 100, color: 'rgba(255, 48, 0, 1)', vacuityColor: 'rgba(255, 48, 0, .3)'},]"
                         style="height: 2em; width: 2em;"></movableRing>
          </div>
          <h4 style="color: rgba(255, 48, 0, 1);">有发情可能，请对其保持观察。</h4>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <h4>呼吸状态：</h4>
            <h4 style="color: rgba(19, 228, 0, 1);">正常</h4>
          </div>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <h4>面部状态：</h4>
            <h4 style="color: rgba(19, 228, 0, 1);">正常</h4>
          </div>
        </div>
      </div>
      <div class="assembly" style="margin-top: 20px;">
        <div class="card zoom" style="width: 100%; margin-top: 10px; padding: 10px 5%; text-align: start; overflow: hidden;">
          <div style="display: flex; justify-content: flex-start; align-items: center;">
            <h4>识别到此猪为本猪场第317号猪</h4>
            <div style="position: relative; height: 1em; margin-left: 2px;">
              <img src="static/icon/question.png"
                   style="position: absolute; height: 1em; transform: translateZ(0); filter: var(--light); z-index: 12;"
                   alt="?">
              <img src="static/icon/question.png"
                   style="position: absolute; transform: translate(1px, 1px) translateZ(0); height: 1em; filter: var(--dark) blur(1px); z-index: 11;"
                   alt="?">
            </div>
          </div>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <div style="display: flex; justify-content: space-between; align-items: center;">
            <div>
              <div v-for="i in healthRecordsData"
                   style="display: flex; flex-direction: column; align-items: flex-start;">
                <h4>{{ i.name }}</h4>
                <div :style="i.color">{{ i.value }}/{{ i.max }} {{ i.company }}</div>
              </div>
            </div>
            <div style="margin-right: 30px;">
              <movableRing :ringData="ringData" style="height: 8.2em; width: 8.2em;"></movableRing>
            </div>
          </div>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <h4>今日活动时长正常，饮水量达标，饮食量过少。</h4>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <h4>今日已休息10.4h，休息状态极佳。</h4>
          <div style="position: relative; border: rgb(128, 128, 128) 1px solid; width: 110%; margin: 5px 0;"></div>
          <h4>此猪综合表现高于平均水准，请继续保持。</h4>
        </div>
      </div>

      <div style="position: relative; height: 80px; margin-top: 60px; width: 2px;"></div>
    </div>

    <div
      style="position: fixed; top: 0; left: 0; width: 100%; height: 100%; z-index: -2; background: var(--white); transition: .4s;"></div>
  </div>
</template>

<script>
import topBar from "../../components/topBar";
import movableRing from "../../components/movableRing";

const url = 'http://127.0.0.1:5000/'

export default {
  name: "surveillanceVideo",
  data() {
    return {
      isPictureLoaded: false,
      isAudioLoaded: false,
      rotator: '',
      healthRecordsData: [
        {name: '活动', value: 4.8, max: 6, color: 'color: #FF3000;', company: '小时'},
        {name: '饮水次数', value: 35, max: 20, color: 'color: #13E400;', company: '次'},
        {name: '饮食量', value: 20, max: 100, color: 'color: #00E5D4;', company: 'kg'}
      ],
      ringData: [
        {value: 4.8, max: 6, color: 'rgba(255, 48, 0, 1)', vacuityColor: 'rgba(255, 48, 0, .3)'},
        {value: 35, max: 20, color: 'rgba(19, 228, 0, 1)', vacuityColor: 'rgba(19, 228, 0, .3)'},
        {value: 20, max: 100, color: 'rgba(0, 229, 212, 1)', vacuityColor: 'rgba(0, 229, 212, .3)'}
      ]
    }
  },
  components: {
    topBar, movableRing
  },
  mounted() {
    let yellow = this.$el.children[1]
    let red = this.$el.children[2]
    let purple = this.$el.children[3]
    let blue = this.$el.children[4]
    let green = this.$el.children[5]

    setTimeout(() => {
      yellow.style.top = '-20%'
      red.style.top = '8%'
      red.style.left = '76%'
      purple.style.top = '55%'
      purple.style.left = '70%'
      blue.style.top = '55%'
      blue.style.left = '20%'
      green.style.left = '-18%'
    }, 20)
  },
  methods: {
    uploadClick(e) {
      let self = e.target
      let t = self.children[0]
      t.click()
    },
    uploadPictures(e) {
      let self = e.target
      let file = self.files

      self.nextElementSibling.nextElementSibling.style.width = '50px'
      self.nextElementSibling.style.marginTop = '0'

      let picture = new FileReader()
      picture.readAsDataURL(file[0])

      let img = this.$el.querySelector('.loading').children[1].children[0]
      picture.onload = function (res) {
        img.src = res.target.result
      }

      this.isPictureLoaded = true
      this.loading()
    },
    uploadAudio(e) {
      let self = e.target
      let file = self.files

      self.nextElementSibling.nextElementSibling.style.width = '50px'
      self.nextElementSibling.style.marginTop = '0'

      let audio = new FileReader()
      audio.readAsDataURL(file[0])

      this.isAudioLoaded = true
      this.loading()
    },
    loading() {
      if (this.isPictureLoaded === false || this.isAudioLoaded === false) {
        return
      }
      let color = []
      color.push(this.$el.children[1])
      color.push(this.$el.children[2])
      color.push(this.$el.children[3])
      color.push(this.$el.children[4])
      color.push(this.$el.children[5])
      color.forEach(function (item) {
        item.style.animation = 'none'
      })
      this.alwaysRotate()
      this.$el.querySelector('.loading').style.display = 'block'

      setTimeout(() => {
        this.$el.querySelector('.loading').style.opacity = '1'
        color.forEach(function (item) {
          item.style.top = '40%'
          item.style.left = '50%'
          item.style.transform = 'translate(-50%, -50%)'
        })
      }, 1200)
      setTimeout(() => {
        this.$el.querySelector('.loading').style.opacity = '1'
        color.forEach(function (item) {
          item.style.display = 'none'
        })
      }, 3000)

      // 发送请求
      setTimeout(() => {
        this.stopRotate()

        let loading = this.$el.querySelector('.loading')
        loading.style.transition = '.8s cubic-bezier(.32, -0.03, 0, 1.09)'
        loading.style.boxShadow = 'var(--outShadow)'
        loading.style.top = loading.getBoundingClientRect().top + 'px'
        loading.style.zIndex = '12'
        loading.style.transform = 'translate(0, 0)'
        setTimeout(() => {
          loading.style.top = '80px'
          loading.style.left = '5%'
          loading.style.width = 'var(--rectangle)'
          loading.style.height = 'var(--rectangle)'
        }, 20)

        let result = this.$el.querySelector('.result')
        result.style.position = 'absolute'
        result.style.transform = 'translate(0, 50px)'

        this.runZoom()
      }, 1200 + 5500)
    },
    alwaysRotate() {
      let rotationStyle = this.$el.querySelector('.rotator')
      let x = 0, y = 0, parameter = 0
      const PI = 3.1416, magnification = 100, r = 12
      this.rotator = setInterval(() => {
        parameter = (parameter * magnification + 2) % (2 * PI * magnification) / magnification
        x = r * Math.cos(parameter)
        y = r * Math.sin(parameter)
        rotationStyle.style.transform = 'translate(' + x + 'px, ' + y + 'px)'
      }, 8.3333)
    },
    stopRotate() {
      let upload = this.$el.getElementsByClassName('upload')
      upload[0].style.display = 'none'
      upload[1].style.display = 'none'

      clearInterval(this.rotator)
      this.$el.querySelector('.rotator').style.display = 'none'
    },
    runZoom() {
      let zoom = this.$el.getElementsByClassName('zoom')

      for (let i = 0; i < zoom.length; i++) {
        zoom[i].style.transition = '.25s cubic-bezier(1,0,.5,.5)'
        setTimeout(() => {
          zoom[i].style.transform = 'scale(.95)'
        }, 550 + i * 90)
        setTimeout(() => {
          zoom[i].style.transition = '.25s cubic-bezier(.5,.5,0,1)'
          zoom[i].style.transform = 'scale(1)'
        }, 800 + i * 90)
      }
    }
  }
}
</script>

<style scoped>
.ball {
  position: fixed;
  border-radius: 50%;
  filter: blur(4px);
  transition: 1.8s cubic-bezier(.32, -0.03, 0, 1.09);
  animation: wobble 4s infinite linear;
  z-index: -1;
}

@keyframes wobble {
  0%, 100% {
    transform: translate(0, 0);
  }
  25% {
    transform: translate(0, 3%);
  }
  75% {
    transform: translate(0, -3%);
  }
}

.upload {
  position: relative;
  top: 0;
  width: 100px;
  height: 100px;
  padding: 20px 0;
  background: rgba(20, 20, 20, .05);
  border-radius: 50%;
  cursor: pointer;
  transition: .4s;
}

.loading {
  --rectangle: 65px;
  position: absolute;
  display: none;
  top: 40%;
  left: 50%;
  opacity: 0;
  box-shadow: none;
  backdrop-filter: none;
  transform: translate(-50%, -50%);
  width: 354px;
  height: 354px;
  border-radius: 50%;
  transition: .4s;
  z-index: 4;
}

.rotator {
  position: absolute;
  top: 0;
  left: 0;
  width: 354px;
  height: 354px;
  border-radius: 50%;
  z-index: 4;
  filter: blur(4px);
  background: conic-gradient(#fab900, #fa5d00, #fa0100, #d900fa,
  #1e00fa, #00adfa, #00faaa, #34fa00, #cefa00, #fab900);
}

.result {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  transform: translate(0, 1550px);
  overflow: auto;
  z-index: 10;
  transition: .8s cubic-bezier(.32, -0.03, 0, 1.09);
}
</style>
