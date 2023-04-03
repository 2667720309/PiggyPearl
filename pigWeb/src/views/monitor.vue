<template>
  <div id="home">
    <div style="display: flex; justify-content: space-between; padding: 20px; align-items: flex-end;
         position: fixed; z-index: 2; background: linear-gradient(rgba(255, 255, 255, .8), rgba(255, 255, 255, 0));">
      <div style="font-size: 1.8em; cursor: default; white-space: nowrap; margin-right: 20px;">视频监控</div>
      <div style="display: flex; align-items: center; font-size: 1.4em;">
        {{ this.date.getFullYear() }}年{{ this.date.getMonth() + 1 }}月{{ this.date.getDate() }}日
        {{ this.date.getHours() }}
        <div class="tick">:</div>
        {{ this.date.getMinutes() }}
        <div class="tick">:</div>
        {{ this.date.getSeconds() }}
      </div>
    </div>
    <!--    卡片组-->
    <div class="cards">
      <div class="card" v-for="(i, n) in originList" style="opacity: 1;">
        <video style="height: 100%; width: 100%; object-fit: cover; position: relative;
               left: 50%; top: 50%; transform: translate(-50%, -50%);" autoplay muted loop>
          <source :src="'static/mov/m' + i + '.MOV'">
        </video>
        <div class="cardText">
          <div style="top: 55px; left: 10px; position: relative; font-size: 1.6em;">
            {{ n + 1 }}号机位
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mainPageInter} from "../js/mainPageInter";

export default {
  name: "monitor",
  data() {
    return {
      originList: [1, 3, 4, 6, 7, 2, 4, 5, 3, 1, 4, 2],
      date: new Date()
    }
  },
  created() {
    // 设置循环器用于每秒更新时间
    clearInterval(timeDisplay)
    let timeDisplay = setInterval(() => {
      this.date = new Date()
      this.$el.getElementsByClassName('tick')[0].classList.toggle('act')
      this.$el.getElementsByClassName('tick')[1].classList.toggle('act')
    }, 1000)
  },
  mounted() {
    mainPageInter(3)
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

.cards {
  overflow-y: auto;
  display: flex;
  flex-wrap: wrap;
  padding: 0 10px;
  margin-top: 82px;
  justify-content: space-between;
}

.card {
  --translate: '';
  position: relative;
  width: 420px;
  height: 300px;
  margin: 0 10px 20px 10px;
  /*border-radius: 20px;*/
  overflow: hidden;
  box-shadow: var(--shadow) 1px 1px 4px;
  transition: .6s ease-in-out;
}

.cardText {
  position: absolute;
  width: 100%;
  height: 100px;
  bottom: 0;
  /*border-radius: 0 0 20px 20px;*/
  display: flex;
  color: #21ba80;
  flex-direction: column;
  align-items: flex-start;
  text-shadow: rgb(149, 149, 149) 1px 1px 4px;
  /*background: linear-gradient(rgba(255, 255, 255, 0), rgba(255, 255, 255, .8));*/
  transition: .2s;
}

.act {
  opacity: 0;
}

.BE39-B7S8-38WP {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}
</style>
