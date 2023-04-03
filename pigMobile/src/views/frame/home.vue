<template>
  <div style="transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
    <top-bar></top-bar>
    <!--    欢迎框-->
    <div class="show card">
      <img :src="imgUrl" alt="欢迎图片">
      <div style="position: absolute;">
        <p class="hello">Hi,Welcome</p>
        <p class="introduce">生猪全周期检测系统</p>
      </div>
    </div>
    <!--    三个主要功能-->
    <div class="function">
      <div class="card" style="position: relative; overflow: hidden; width: 30%; cursor: pointer;">
        <div class="DianWei" @click="toItem('video/monitoringSites')"
             style="background:linear-gradient(90deg,rgba(129,251,184,.8),rgba(88,227,151,.8) 50%,rgba(40,199,111,.8) 100%);">
          <div class="circle_1" style="background: rgba(88, 227, 151, .8);">
            <img :src="circle_img_1" style="filter: var(--dark); transition: .4s;" alt="监控点位" width="70%">
          </div>
          <p style="color: var(--black); transition: 0.4s;">监控点位</p>
        </div>
      </div>

      <div class="card" style="position: relative; overflow: hidden; width: 30%;">
        <div class="DianWei" @click="toItem('video/pigDiagnosis')"
             style="background:linear-gradient(90deg,rgba(171,220,255,.8),rgba(89,186,255,.8)50%,rgba(3,150,255,.8));">
          <div class="circle_1" style="background: rgba(89, 186, 255, .8);">
            <img :src="circle_img_2" style="filter: var(--light); transition: .4s;" alt="视频监控" width="70%">
          </div>
          <p style="color: var(--black); transition: 0.4s;">生猪诊断</p>
        </div>
      </div>

      <div class="card" style="position: relative; overflow: hidden; width: 30%; cursor: pointer;">
        <div class="DianWei" @click="toItem('video/aquacultureArchives')"
             style="background:linear-gradient(90deg,rgba(249,212,35,.8),rgba(239,151,17,.8) 50%,rgba(230,92,0,.8) 100%);">
          <div class="circle_1" style="background: rgba(239, 151, 17, .8);">
            <img :src="circle_img_3" style="filter: var(--dark); transition: .4s;" alt="养殖档案" width="70%">
          </div>
          <p style="color: var(--black); transition: 0.4s;">养殖档案</p>
        </div>
      </div>
    </div>

    <div class="assembly">
      <div class="header">每日数据分析</div>
      <div class="map card">
        <div id="main" style="height: 90%"></div>
      </div>
    </div>
  </div>
</template>

<script>
import TopBar from "../../components/topBar";
import * as echarts from 'echarts'
import {pageEnter} from "../../assets/js/main";

export default {
  name: "home",
  components: {TopBar},
  data() {
    return {
      imgUrl: 'static/Huanying.png',
      circle_img_1: 'static/pc.png',
      circle_img_2: 'static/icon/doctor.png',
      circle_img_3: 'static/par.png',
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
    },
    drawLine(id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          show: true,
          trigger: 'axis'
        },
        legend: {
          data: ['实际温度', '标准温度'],
          textStyle: {
            fontSize: 14,//字体大小
            color: '#000000'//字体颜色
          },
        },
        color: ['#0066FF', '#FF3333'],
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ["0点", "2点", "4点", "6点", "8点", "10点", "12点", "14点", "16点", "18点", "20点", "22点", "24点"],
          name: '时间',
          nameTextStyle: {        //坐标轴名称的文字样式
            color: '#FF3333',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
        },
        yAxis: {
          min: 30,
          max: 33,
          splitNumber: 3,
          type: 'value',
          name: '温度'
        },

        series: [
          {
            name: '标准温度',
            type: 'line',
            smooth: true,
            areaStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#0066FF'},
                    {offset: 0.5, color: '#99BBFF'},
                    {offset: 1, color: '#ddd'}
                  ]
                )
              }
            },
            data: ["31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31"],
            lineStyle: {                // 线条样式 => 必须使用normal属性
              normal: {
                color: '#0066FF',
              }
            }
          },
          {
            name: '实际温度',
            type: 'line',
            smooth: true,
            areaStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    {offset: 0, color: '#FF3333'},
                    {offset: 0.5, color: 'pink'},
                    {offset: 1, color: '#ddd'}
                  ]
                )
              }
            },
            data: ["30", "30.2", "30.5", "31.2", "31.5", "31.7", "31.9", "32.5", "32.6", "32.5", "31.4", "30.8", "30.3"]
          }],
        lineStyle: {
          normal: {
            color: '#FF3333',
          }
        },
      })
    }
  },
  mounted() {
    this.$nextTick(function () {
      this.drawLine('main')
    })
    pageEnter(this.$el, this.$route.name, this.$route.query.page)
  }
}
</script>

<style scoped>
.show {
  margin-top: 25px;
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 20%;
  display: flex;
  align-items: center;
}

.show > img {
  width: 100%;
  filter: var(--dark);
  transition: 0.4s;
}

.hello {
  font-size: 1.4em;
  font-weight: bold;
  text-align: left;
  color: var(--black);
  position: relative;
  left: 10px;
  transition: 0.4s;
}

.introduce {
  font-size: 1.4em;
  font-weight: bold;
  text-align: left;
  left: 10px;
  color: var(--black);
  position: relative;
  transition: 0.4s;
}

.function {
  margin-top: 30px;
  display: flex;
  width: 100%;
  height: 100px;
  position: relative;
  justify-content: space-between;
}

.DianWei {
  justify-content: center;
  align-items: center;
  position: absolute;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 10px;
  width: 100%;
  transition: 0.4s;
}

.circle_1 {
  height: 58px;
  width: 58px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.map {
  height: 250px;
  width: 100%;
  margin-top: 10px;
  position: relative;
  padding: 5px;
}

</style>
