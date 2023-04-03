<template>
  <div id="home">
    <!--    欢迎条-->
    <div class="welcome">
      <div
        style="display: flex; flex-direction: column; height: 300px; justify-content: space-between; align-items: flex-start;">
        <div class="card" style="height: 190px; width: 380px;">
          <div style="display: flex; justify-content: flex-start; margin: 20px;">
            <img src="static/icon/personalCenter.PNG" alt="" class="head"
                 style="width: 100px; object-fit: cover; border-radius: 50%;">
            <div
              style="display: flex; flex-direction: column; margin-left: 20px; justify-content: center; align-items: flex-start; text-align: left;">
              <div style="font-size: 1.6em;">欢迎　管理员1</div>
              <div style="color: #888;">昨日猪场情况良好，暂未察觉到异常。</div>
            </div>
          </div>
          <div style="text-align: left; margin-left: 20px; color: #555;">
            今日已完成事项<b>{{
              toDos.filter((item) => {
                return item.down
              }).length
            }}</b>件，剩余<b>{{
              toDos.filter((item) => {
                return !item.down
              }).length
            }}</b>件待处理。
          </div>
        </div>
        <div style="margin-top: 10px; font-size: 1.2em; color: #888">猪场人员</div>
        <div style="height: 60px; width: 380px; display: flex; justify-content: space-between;">
          <div class="card" style="height: 60px; width: 60px;">
            <img style="width: 100%; object-fit: cover;"
                 src="static/headImg/head2.jpeg"
                 alt="">
          </div>
          <div class="card" style="height: 60px; width: 60px;">
            <img style="width: 100%; object-fit: cover;"
                 src="static/headImg/head3.jpeg"
                 alt="">
          </div>
          <div class="card" style="height: 60px; width: 60px;">
            <img style="width: 100%; object-fit: cover;"
                 src="static/headImg/head4.jpeg"
                 alt="">
          </div>
          <div class="card" style="height: 60px; width: 60px;">
            <img style="width: 100%; object-fit: cover;"
                 src="static/headImg/head5.jpg"
                 alt="">
          </div>
          <div class="card" style="height: 60px; width: 60px; cursor: pointer;
          display: flex; align-items: center; justify-content: center; font-size: 2em; color: #666;">
            ···
          </div>
        </div>
      </div>

      <div class="toDoList card">
        <div
          style="text-align: left; font-size: 1.6em; position: absolute; z-index: 2; background: white; width: 360px; top: 20px; padding-top: 20px;">
          待办事项
        </div>
        <div
          style="position: absolute; border: 1px solid rgba(150, 150, 150, .8); width: 360px; top: 78px; margin-bottom: 6px; z-index: 1; box-shadow: var(--shadow) 0 1px 4px;"></div>
        <div style="height: 46px;"></div>
        <div style="position: relative;" v-for="(t, i) in toDos">
          <div class="toDos"
               style="display: flex; align-items: center; position: relative; transition: .4s; margin-bottom: 6px;">
            <div @click="downToDos(i)"
                 :style="'height: 40px; width: 40px; background: ' + (t.down ? 'var(--theme)' : '#888') + '; border-radius: 50%; position: relative; cursor: pointer;'">
              <div style="position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);
                height: 34px; width: 34px; background: white; border-radius: 50%;">
                <div :style="'position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%);' +
                  'height: 30px; width: 30px; background: ' + (t.down ? 'var(--theme)' : 'white') + '; border-radius: 50%;'"></div>
              </div>
            </div>
            <div style="text-align: left; margin-left: 5px;">
              <div>{{ t.mes }}</div>
              <div style="font-size: .8em; color: #888;">{{
                  t.date ? t.date.hours + '时' + t.date.minutes + '分' : '今天'
                }}
              </div>
            </div>
          </div>
          <div style="border: 1px solid rgba(200, 200, 200, .8); margin-bottom: 6px;"></div>
        </div>
        <div class="addToDoCard">
          <input type="text" placeholder="请键入事项" v-model="addToDoMes.mes"
                 style="box-shadow: var(--shadow) 1px 1px 4px; border-radius: 20px; width: 328px; height: 35px;
                 padding: 0 20px; font-size: 1.2em; border: none; outline: none; background: white; margin-top: 10px;"/>
          <div style="display: flex; align-items: center; justify-content: flex-start; margin: 10px;">
            <div style="font-size: 1.2em;">选填：</div>
            <input type="text" v-model="addToDoMes.date.hours"
                   style="box-shadow: var(--shadow) 1px 1px 4px; border-radius: 20px; width: 35px; height: 35px;
                   padding: 0 20px; font-size: 1.2em; border: none; outline: none; background: white;"/>
            <div style="font-size: 1.2em; margin: 0 10px;">时</div>
            <input type="text" v-model="addToDoMes.date.minutes"
                   style="box-shadow: var(--shadow) 1px 1px 4px; border-radius: 20px; width: 35px; height: 35px;
                   padding: 0 20px; font-size: 1.2em; border: none; outline: none; background: white;"/>
            <div style="font-size: 1.2em; margin: 0 10px;">分</div>
          </div>
        </div>
        <div class="addToDo" @click="addToDo()">
          <div class="add">+</div>
        </div>
      </div>

      <!--      天气-->
      <div class="card weather" v-if="weather">
        <div style="height: 190px; display: flex; justify-content: space-between; margin: 0 20px; align-items: center;">
          <div style="display: flex; flex-direction: column; align-items: center; z-index: 2; transition: .4s;
            text-shadow: 0 0 10px gray;">
            <div style="font-size: 1.6em; transform: translate(0, 15px);">{{ weather[0].city }}</div>
            <div style="font-size: 5em; transform: translate(10px);">{{ weather[0].temp }}°</div>
            <div style="transform: translate(0, -15px);">{{ weather[0].weather }}</div>
            <div style="transform: translate(0, -15px);">最高{{ weather[0].high }}° 最低{{ weather[0].low }}°</div>
          </div>
          <img :src="'static/icon/weather/' + weather2url(weather[0].weather) + '.png'"
               style="height: 190px; object-fit: cover; transition: .4s;" alt="">
        </div>
        <div style="width: 100%; height: 1px; background: rgba(200, 200, 200, .4); margin-left: 20px;"></div>
        <div style="display: flex;">
          <div style="width: 20px; height: 110px;"></div>
          <div
            style="width: 100%; height: 110px; display: flex; align-items: center; justify-content: space-between; flex-wrap: wrap;">
            <div v-for="w in weather" style="margin: 4px 20px 10px 0;">
              <div>{{
                  w.lastUpdateTime.slice(7, 9) === w.date.slice(7, 9) ? '今天' : w.date.slice(7, 9) + '日'
                }}
              </div>
              <img :src="'static/icon/weather/' + weather2url(w.weather) + '.png'"
                   style="height: 50px; object-fit: cover;" alt="">
              <div>{{ w.low }}°/{{ w.high }}°</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="font-size: 1.8em; text-align: left; margin-left: 20px;">今日摘要</div>
    <div
      style="display: flex; justify-content: flex-start; position: relative; flex-wrap: wrap; padding: 10px; margin-bottom: 10px;">
      <div style="width: 33.333333%; height: 260px;">
        <div style="display: flex; height: 100%;">
          <div style="width: 10px; height: 100%;"></div>
          <div class="card"
               style="height: 100%; width: 100%; position: relative;
                      display: flex; align-items: flex-start;">
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
            <div style="height: 100%; width: 100%; position: relative;
                        display: flex; flex-direction: column; align-items: flex-start; text-align: left;">
              <div style="color: #ff2e63; margin-top: 20px;">猪场内气温</div>
              <div>在过去7天中，猪场内部气温适宜，无较大变化。</div>
              <div style="width: 100%; height: 1px; background: rgb(200, 200, 200); margin: 3px 0;"></div>
              <div id="chart1" style="width: 100%; height: 100%; position: relative; margin-bottom: 20px;"></div>
            </div>
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
          </div>
          <div style="width: 10px; height: 100%;"></div>
        </div>
      </div>
      <div style="width: 33.333333%; height: 260px;">
        <div style="display: flex; height: 100%;">
          <div style="width: 10px; height: 100%;"></div>
          <div class="card"
               style="height: 100%; width: 100%; position: relative;
                      display: flex; align-items: flex-start;">
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
            <div style="height: 100%; width: 100%; position: relative;
                        display: flex; flex-direction: column; align-items: flex-start; text-align: left;">
              <div style="color: #20a0ff; margin-top: 20px;">猪场内空气湿度</div>
              <div>在过去7天中，猪场内部较为潮湿，注意通风排气。</div>
              <div style="width: 100%; height: 1px; background: rgb(200, 200, 200); margin: 6px 0;"></div>
              <div id="chart2" style="width: 100%; height: 100%; position: relative; margin-bottom: 20px;"></div>
            </div>
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
          </div>
          <div style="width: 10px; height: 100%;"></div>
        </div>
      </div>
      <div style="width: 33.333333%; height: 260px;">
        <div style="display: flex; height: 100%;">
          <div style="width: 10px; height: 100%;"></div>
          <div class="card"
               style="height: 100%; width: 100%; position: relative;
                      display: flex; align-items: flex-start;">
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
            <div style="height: 100%; width: 100%; position: relative;
                        display: flex; flex-direction: column; align-items: flex-start; text-align: left;">
              <div style="color: #21ba80; margin-top: 20px;">饲料消耗量</div>
              <div>在过去7天中，饲料平均消耗量为{{ 763.3 }}kg，没有太大的波动。</div>
              <div style="width: 100%; height: 1px; background: rgb(200, 200, 200); margin: 6px 0;"></div>
              <div id="chart3" style="width: 100%; height: 100%; position: relative; margin-bottom: 20px;"></div>
            </div>
            <div style="min-width: 20px; max-width: 20px; height: 100%;"></div>
          </div>
          <div style="width: 10px; height: 100%;"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mainPageInter} from "../js/mainPageInter";
import {widthControl, weather2url} from "../js/weather";

export default {
  name: "home",
  data() {
    return {
      weather: null,
      toDos: [
        {mes: '检查饲料食用情况', date: null, down: false},
        {mes: '给母猪投喂特殊饲料', date: {hours: '12', minutes: '0'}, down: true},
        {mes: '检查14号猪状态', date: null, down: true},
        {mes: '检查103号猪发情状态', date: null, down: true},
        {mes: '检查36号猪状态', date: {hours: '6', minutes: '30'}, down: false},
        {mes: '检查饮用水源', date: {hours: '8', minutes: '20'}, down: true},
        {mes: '检查猪舍温度', date: null, down: true},
      ],
      isAddToDo: false,
      addToDoMes: {
        mes: null,
        date: {hours: null, minutes: null},
        down: false
      }
    }
  },
  created() {
    // this.$http({
    //   url: 'csp/api/v2.1/weather',
    //   params: {
    //     openId: 'aiuicus',
    //     clientType: 'android',
    //     sign: 'android',
    //     city: '重庆',
    //     needMoreData: 'true',
    //     pageNo: '1',
    //     pageSize: '7'
    //   }
    // }).then(res => {
    //   this.weather = res.data.list
    //   setTimeout(() => {
    //     widthControl()
    //     this.$el.querySelector('.weather').style.opacity = '1'
    //   }, 600)
    // })
    this.weather = [
      {
        city: '重庆',
        temp: 12,
        weather: '晴',
        low: 7,
        high: 18,
        date: '2023年3月13日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '晴',
        low: 7,
        high: 18,
        date: '2023年3月14日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '多云',
        low: 7,
        high: 18,
        date: '2023年3月15日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '阴',
        low: 7,
        high: 18,
        date: '2023年3月16日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '多云',
        low: 7,
        high: 18,
        date: '2023年3月17日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '小雨',
        low: 7,
        high: 18,
        date: '2023年3月18日',
        lastUpdateTime: '2023年3月13日'
      },
      {
        city: '重庆',
        temp: 12,
        weather: '多云',
        low: 7,
        high: 18,
        date: '2023年3月19日',
        lastUpdateTime: '2023年3月13日'
      }
    ]
    setTimeout(() => {
      widthControl()
      this.$el.querySelector('.weather').style.opacity = '1'
    }, 1000)
    this.toDosSort()
  },
  mounted() {
    mainPageInter(1)
    // 切换头像
    let headImg = this.$el.getElementsByClassName('head')[0]
    headImg.src = 'static/headImg/head1.jpeg'

    // 表格添加
    let chart1 = this.$ch.init(document.querySelector('#chart1'))
    let data1 = {
      low: [20, 19, 23, 16, 22, 24, 21],
      high: [7, 6, 5, 11, 8, 6, 7]
    }
    chart1.setOption({
      grid: {
        top: '5px',
        left: '0',
        right: '0',
        bottom: '0',
        containLabel: true
      },
      xAxis: {
        data: ['4日', '5日', '6日', '7日', '8日', '9日', '今天'],
        axisLine: {
          show: false
        },
        axisTick: {
          alignWithLabel: true
        },
        splitLine: {
          show: false
        }
      },
      yAxis: {
        splitLine: {
          show: false,
          lineStyle: {
            type: 'dashed'
          }
        },
        splitNumber: 2
      },
      tooltip: {
        trigger: 'axis',
        formatter: function (params) {
          let htmlStr = '<div style="color: #888; display: flex; flex-direction: column; align-items: flex-start;">';
          htmlStr += '<div>温度</div>';
          htmlStr += '<div style="display: flex;">'
          htmlStr += '<div style="font-size: 1.6em; color: black;">' + params[0].value + '-' + (params[0].value + params[1].value) + '</div>';
          htmlStr += '<div>°C</div>'
          htmlStr += '</div>';
          htmlStr += params[0].name === '今天' ? '<div>今天</div>' : '<div>2022年11月' + params[0].name + '</div>'
          htmlStr += '</div>';
          return htmlStr;
        }
      },
      series: [
        {
          type: 'bar',
          stack: 'all',
          itemStyle: {
            normal: {
              barBorderColor: 'rgba(0,0,0,0)',
              color: 'rgba(0,0,0,0)'
            },
            emphasis: {
              barBorderColor: 'rgba(0,0,0,0)',
              color: 'rgba(0,0,0,0)'
            }
          },
          data: data1.low
        },
        {
          name: 'high',
          type: 'bar',
          stack: 'all',
          data: data1.high,
          barWidth: 10,
          itemStyle: {
            normal: {
              borderRadius: 5,
              color: '#ff2e63'
            }
          }
        }
      ]
    })
    let chart2 = this.$ch.init(document.querySelector('#chart2'))
    let data2 = {
      low: [55, 63, 72, 64, 57, 66, 60],
      high: [21, 17, 14, 9, 17, 11, 15]
    }
    chart2.setOption({
      grid: {
        top: '5px',
        left: '0',
        right: '0',
        bottom: '0',
        containLabel: true
      },
      xAxis: {
        data: ['4日', '5日', '6日', '7日', '8日', '9日', '今天'],
        axisLine: {
          show: false
        },
        axisTick: {
          alignWithLabel: true
        },
        splitLine: {
          show: false
        }
      },
      yAxis: {
        splitLine: {
          show: false,
          lineStyle: {
            type: 'dashed'
          }
        },
        splitNumber: 2
      },
      tooltip: {
        trigger: 'axis',
        formatter: function (params) {
          let htmlStr = '<div style="color: #888; display: flex; flex-direction: column; align-items: flex-start;">';
          htmlStr += '<div>湿度</div>';
          htmlStr += '<div style="display: flex;">'
          htmlStr += '<div style="font-size: 1.6em; color: black;">' + params[0].value + '</div>'
          htmlStr += '<div>%</div>'
          htmlStr += '<div style="font-size: 1.6em; color: black;">-' + (params[0].value + params[1].value) + '</div>';
          htmlStr += '<div>%</div>'
          htmlStr += '</div>';
          htmlStr += params[0].name === '今天' ? '<div>今天</div>' : '<div>2022年11月' + params[0].name + '</div>'
          htmlStr += '</div>';
          return htmlStr;
        }
      },
      series: [
        {
          type: 'bar',
          stack: 'all',
          itemStyle: {
            normal: {
              barBorderColor: 'rgba(0,0,0,0)',
              color: 'rgba(0,0,0,0)'
            },
            emphasis: {
              barBorderColor: 'rgba(0,0,0,0)',
              color: 'rgba(0,0,0,0)'
            }
          },
          data: data2.low
        },
        {
          name: 'high',
          type: 'bar',
          stack: 'all',
          data: data2.high,
          barWidth: 10,
          itemStyle: {
            normal: {
              borderRadius: 5,
              color: '#20a0ff'
            }
          }
        }
      ]
    })
    let chart3 = this.$ch.init(document.querySelector('#chart3'))
    let data3 = [738.6, 774.5, 789.2, 756.7, 763.1, 748.9, 771.8]
    chart3.setOption({
      grid: {
        top: '5px',
        left: '0',
        right: '0',
        bottom: '0',
        containLabel: true
      },
      xAxis: {
        data: ['4日', '5日', '6日', '7日', '8日', '9日', '今天'],
        axisLine: {
          show: false
        },
        axisTick: {
          alignWithLabel: true
        },
        splitLine: {
          show: false
        }
      },
      yAxis: {
        splitLine: {
          show: false,
          lineStyle: {
            type: 'dashed'
          }
        },
        splitNumber: 2
      },
      tooltip: {
        trigger: 'axis',
        formatter: function (params) {
          let htmlStr = '<div style="color: #888; display: flex; flex-direction: column; align-items: flex-start;">';
          htmlStr += '<div>消耗量</div>';
          htmlStr += '<div style="display: flex;">'
          htmlStr += '<div style="font-size: 1.6em; color: black;">' + params[0].value + '</div>';
          htmlStr += '<div>kg</div>'
          htmlStr += '</div>';
          htmlStr += params[0].name === '今天' ? '<div>今天</div>' : '<div>2022年11月' + params[0].name + '</div>'
          htmlStr += '</div>';
          return htmlStr;
        }
      },
      series: [
        {
          name: 'high',
          type: 'bar',
          stack: 'all',
          data: data3,
          barWidth: 20,
          itemStyle: {
            normal: {
              borderRadius: 5,
              color: '#21ba80'
            }
          }
        }
      ]
    })

    const resizeObserver = new ResizeObserver(entries => {
      chart1.resize()
      chart2.resize()
      chart3.resize()
    })
    resizeObserver.observe(document.querySelector('#home'))
  },
  updated() {
    // 添加代办事项时，将小时限定在0-23，分钟限定在0-59之间
    if (this.addToDoMes.date) {
      if (this.addToDoMes.date.hours) {
        if (isNaN(parseInt(this.addToDoMes.date.hours))) {
          this.addToDoMes.date.hours = null
        } else if (0 > parseInt(this.addToDoMes.date.hours)) {
          this.addToDoMes.date.hours = '0'
        } else if (23 < parseInt(this.addToDoMes.date.hours)) {
          this.addToDoMes.date.hours = '23'
        }
      }
      if (this.addToDoMes.date.minutes) {
        if (isNaN(parseInt(this.addToDoMes.date.minutes))) {
          this.addToDoMes.date.minutes = null
        } else if (0 > parseInt(this.addToDoMes.date.minutes)) {
          this.addToDoMes.date.minutes = '0'
        } else if (59 < parseInt(this.addToDoMes.date.minutes)) {
          this.addToDoMes.date.minutes = '59'
        }
      }
    }
  },
  methods: {
    weather2url(value) {
      return weather2url(value)
    },

    // 切换事项完成状态
    downToDos(index) {
      this.toDos[index].down = !this.toDos[index].down
      this.toDosSort()
    },

    // 代办事项按照时间顺序排序
    toDosSort() {
      this.toDos.sort(function (a, b) {
        let isDown = a.down && !b.down
        let isDate = a.date && b.date ?
          (parseInt(a.date.hours) < parseInt(b.date.hours) ?
            false : (parseInt(a.date.hours) > parseInt(b.date.hours) ?
              true : parseInt(a.date.minutes) > parseInt(b.date.minutes))) : !a.date && b.date
        return isDown || (a.down === b.down && isDate)
      })
    },

    // 添加代办事项
    addToDo() {
      let addToDo = this.$el.getElementsByClassName('addToDo')[0]
      let addToDoCard = this.$el.getElementsByClassName('addToDoCard')[0]
      if (this.isAddToDo) {
        addToDoCard.style.top = '314px'
        addToDoCard.style.height = '0'
        addToDoCard.style.left = '814px'
        addToDoCard.style.width = '0'
        addToDo.style.left = '769px'
        addToDo.style.top = '269px'
        addToDo.style.boxShadow = 'inset 1px 1px 4px var(--shadow)'

        // 数据添加
        if (this.addToDoMes.mes) {
          if (!this.addToDoMes.date.hours) {
            this.addToDoMes.date = null
          } else {
            if (!this.addToDoMes.date.minutes) {
              this.addToDoMes.date.minutes = '0'
            }
          }
          this.toDos.push(JSON.parse(JSON.stringify(this.addToDoMes)))
          this.toDosSort()
          this.addToDoMes.mes = null
          this.addToDoMes.date = {hours: null, minutes: null}
        } else {
          this.addToDoMes.date = {hours: null, minutes: null}
        }
      } else {
        addToDoCard.style.top = 314 - 100 + 'px'
        addToDoCard.style.height = 100 + 'px'
        addToDoCard.style.left = '426px'
        addToDoCard.style.width = '388px'
        addToDo.style.left = '765px'
        addToDo.style.top = '265px'
        addToDo.style.boxShadow = '0 0 0 #fff'
      }
      this.isAddToDo = !this.isAddToDo
    }
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

.welcome {
  display: flex;
  margin: 20px;
  justify-content: flex-start;
}

.card {
  border-radius: 20px;
  overflow: hidden;
  background: white;
  box-shadow: var(--shadow) 1px 1px 4px;
}

.toDoList {
  height: 260px;
  min-width: 360px;
  max-width: 360px;
  margin-left: 20px;
  padding: 20px;
  overflow: auto;
}

.addToDoCard {
  position: absolute;
  top: 314px;
  left: 814px;
  width: 0;
  height: 0;
  overflow: hidden;
  background: rgb(250, 250, 250);
  box-shadow: inset 1px 1px 4px var(--shadow);
  border-radius: 20px;
  transition: .2s;
  z-index: 1;
}

.addToDo {
  position: absolute;
  width: 45px;
  height: 45px;
  background: rgb(250, 250, 250);
  left: 769px;
  top: 269px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.2em;
  box-shadow: inset 1px 1px 4px var(--shadow);
  color: #666;
  cursor: pointer;
  transition: .2s;
  z-index: 1;
}

.add {
  transition: .2s;
}

.addToDo:active > .add {
  transform: scale(.8);
}

.weather {
  height: 300px;
  width: 100%;
  margin-left: 20px;
  color: white;
  opacity: 0;
  background: linear-gradient(-160deg, rgb(90, 69, 246), rgb(218, 111, 128));
  transition: .4s;
}
</style>
