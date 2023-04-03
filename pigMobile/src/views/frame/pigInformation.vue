<template>
  <div style="transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
    <topBar></topBar>

    <div class="title">
      <div style="color: rgb(128, 128, 128);">
        {{ date.getMonth() + 1 }}月{{ date.getDate() }}日 星期{{ '日一二三四五六'.charAt(date.getDay()) }}
      </div>
      <div style="color: var(--black); font-weight: bold; font-size: 1.6em;">
        第317号猪
      </div>
    </div>

    <div class="assembly">
      <div class="header">
        健康记录
      </div>
      <div class="card"
           style="display: flex; align-items: center; justify-content: space-between; margin-top: 10px; padding: 10px; width: 100%;">
        <div>
          <div v-for="i in healthRecordsData" style="display: flex; flex-direction: column; align-items: flex-start;">
            <h4>{{ i.name }}</h4>
            <div :style="i.color">{{ i.value }}/{{ i.max }} {{ i.company }}</div>
          </div>
        </div>
        <div style="margin-right: 30px;">
          <movableRing :ringData="ringData" style="height: 8.2em; width: 8.2em;"></movableRing>
        </div>
      </div>
    </div>

    <div class="assembly">
      <div class="header">
        趋势
      </div>
      <div class="card" style="width: 100%; margin-top: 10px; overflow: hidden;">
        <div style="display: flex; justify-content: space-between; margin: 10px; color: rgb(128, 128, 128);">
          此猪未来7天将保持健康，暂无趋势变化。
        </div>
        <div
          style="display: flex; justify-content: space-between; align-items: center;  position: relative; margin: 10px;">
          <div style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; top: -5px;"></div>
          <h4>查看健康趋势</h4>
          <img src="static/向右箭头.png" alt=">" style="height: 18px;">
        </div>
      </div>
    </div>

    <div class="assembly">
      <div class="header">
        发情状态
      </div>
      <div class="card" style="width: 100%; margin-top: 10px; overflow: hidden;">
        <div style="display: flex; justify-content: space-between; margin: 10px;">
          此猪13天后将有发情行为。
        </div>
        <div
          style="display: flex; justify-content: space-between; align-items: center; position: relative; margin: 10px;">
          <div style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; top: -5px;"></div>
          <h4>查看发情状态</h4>
          <img src="static/向右箭头.png" alt=">" style="height: 18px;">
        </div>
      </div>
    </div>

    <div class="assembly">
      <div class="header">
        健康信息
      </div>
      <div class="card" style="width: 100%; margin-top: 10px; overflow: hidden;">
        <div v-for="(i, n) in healthInformationData"
             style="display: flex; justify-content: space-between; position: relative; margin: 10px;">
          <div v-if="n" style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; top: -5px;"></div>
          <h4>{{ i.key }}</h4>
          <div>{{ i.value }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar";
import movableRing from "../../components/movableRing";
import {pageEnter} from "../../assets/js/main";

export default {
  name: "pigInformation",
  data() {
    return {
      date: new Date(),
      healthRecordsData: [
        {name: '活动', value: 4.8, max: 6, color: 'color: #FF3000;', company: '小时'},
        {name: '饮水次数', value: 35, max: 20, color: 'color: #13E400;', company: '次'},
        {name: '饮食量', value: 20, max: 100, color: 'color: #00E5D4;', company: 'kg'}
      ],
      ringData: [
        {value: 4.8, max: 6, color: 'rgba(255, 48, 0, 1)', vacuityColor: 'rgba(255, 48, 0, .3)'},
        {value: 35, max: 20, color: 'rgba(19, 228, 0, 1)', vacuityColor: 'rgba(19, 228, 0, .3)'},
        {value: 20, max: 100, color: 'rgba(0, 229, 212, 1)', vacuityColor: 'rgba(0, 229, 212, .3)'}
      ],
      healthInformationData: [
        {key: '出生日期', value: '2001年6月5日'},
        {key: '性别', value: '母'},
        {key: '本月血液化验情况', value: '正常'},
        {key: '毛皮状态', value: '健康'}
      ]
    }
  },
  mounted() {
    pageEnter(this.$el, this.$route.name, this.$route.query.page)
  },
  components: {
    topBar,
    movableRing
  }
}
</script>

<style scoped>
.title {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 25px;
  transition: 0.4s;
}
</style>
