<!--生猪档案-->
<template>
  <div id="home">
    <div style="display: flex; justify-content: space-between; margin: 20px; align-items: center;">
      <div style="font-size: 1.8em; cursor: default; white-space: nowrap;">生猪档案</div>

      <!--搜索框-->
      <div style="display: flex; align-items: center;">
        <img src="static/icon/search.PNG" alt="" class="searchImg">
        <input type="text" class="search" placeholder="筛选..." v-model="screen" @keyup="listFilter">

        <!--搜索下拉框-->
        <div class="proposal">
          <div style="margin: 50px 0 0 40px;">搜索建议</div>
          <div class="option" @click="screen += ' 第4号猪 '; listFilter()">
            <div style="margin-left: 40px;">第4号猪</div>
          </div>
          <div class="option" @click="screen += ' 日龄204、208 '; listFilter()">
            <div style="margin-left: 40px;">日龄204、208</div>
          </div>
          <div class="option" @click="screen += ' 体重365-370 '; listFilter()">
            <div style="margin-left: 40px;">体重365-370</div>
          </div>
          <div class="option" @click="screen += ' 饮水3 '; listFilter()">
            <div style="margin-left: 40px;">饮水3</div>
          </div>
          <div class="option" @click="screen += ' 进食2.2-2.6 '; listFilter()">
            <div style="margin-left: 40px;">进食2.2-2.6</div>
          </div>
        </div>
      </div>
    </div>

    <!--卡片组-->
    <div style="overflow-y: auto;">
      <div class="card" v-for="i in originList" style="opacity: 1;" @click="shows(i)">
        <img :src="'static/pigFace/pig' + i + '.jpg'" alt=""
             style="height: 100%; width: 100%; object-fit: cover; position: relative; left: 50%; top: 50%; transform: translate(-50%, -50%);">
        <div class="cardText">
          <div style="margin-top: 30px; position: relative; font-size: 1.6em;">
            第{{ i }}号猪
          </div>
          <div class="message">
            <div>日龄：{{ pigs[i - 1].age }}天</div>
            <div>体重：{{ pigs[i - 1].weight }}Kg</div>
          </div>
        </div>
      </div>

      <!--空div，解决布局bug-->
      <div class="IDB3-7HY5-H6R7"></div>
    </div>

    <!--点击展示卡片-->
    <div class="show">
      <img src="" alt="" style="height: 100%; object-fit: cover; border-radius: 20px; width: 250px;">
      <div style="height: 100%; width: 100%; position: relative; text-align: left; margin-left: 20px;">
        <div style="position: relative; font-size: 1.8em;">
          编号：i
        </div>
        <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
        <div>
          <div>日龄：pigs[i - 1].age天</div>
          <div>体重：pigs[i - 1].weightKg</div>
          <div>今日饮水：pigs[i - 1].drink次</div>
          <div>今日进食：pigs[i - 1].eatKg</div>
        </div>
        <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
        <div>
          <div>疫苗已接种情况</div>
          <div>· 猪瘟疫苗</div>
          <div>· O型口蹄疫苗</div>
          <div>· 蓝耳病疫苗</div>
          <div>· 伪狂犬病疫苗</div>
        </div>
        <div style="text-align: right; width: 100%; font-size: 1.5em; position: absolute; bottom: 0;">
          <div style="margin-left: 162px; background: #888888; color: black; cursor: pointer; width: 48px;
               padding: 0 10px; border-radius: 20px; box-shadow: 0 0 4px gold;" @click="hide">确定
          </div>
        </div>
      </div>
    </div>

    <div class="mask"></div>
  </div>
</template>

<script>
import {mainPageInter} from "../js/mainPageInter";
import {typesetting, hideCard, showCard, initCard} from "../js/cardController";
import {pigs} from "../js/pigArchives";

export default {
  name: "archives",
  data() {
    return {
      list: [ // 卡片显示顺序
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30
      ],
      originList: [
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
        11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
        21, 22, 23, 24, 25, 26, 27, 28, 29, 30
      ],
      screen: '',
      cards: document.getElementsByClassName('card'),
      pigs: pigs
    }
  },
  mounted() {
    let size = mainPageInter(2)
    let mask = this.$el.querySelector('.mask')
    mask.style.width = size.width + 'px'
    mask.style.height = size.height + 'px'

    initCard()
    typesetting(this.originList)
  },
  methods: {
    // 显示聚焦卡片时添加数据
    shows(i) {
      this.$el.querySelector('.mask').style.opacity = '1'
      this.$el.querySelector('.mask').style.pointerEvents = 'auto'

      let show = this.$el.querySelector('.show')
      show.style.opacity = '1'
      show.style.pointerEvents = 'auto'

      show.children[0].src = 'static/pigFace/' + pigs[i - 1].url
      show.children[1].children[0].textContent = '编号：' + i
      show.children[1].children[2].children[0].textContent = '日龄：' + pigs[i - 1].age + '天'
      show.children[1].children[2].children[1].textContent = '体重：' + pigs[i - 1].weight + 'Kg'
      show.children[1].children[2].children[2].textContent = '今日饮水：' + pigs[i - 1].drink + '次'
      show.children[1].children[2].children[3].textContent = '今日进食：' + pigs[i - 1].eat + 'Kg'
    },

    // 隐藏聚焦卡片
    hide() {
      this.$el.querySelector('.mask').style.opacity = '0'
      this.$el.querySelector('.mask').style.pointerEvents = 'none'

      let show = this.$el.querySelector('.show')
      show.style.opacity = '0'
      show.style.pointerEvents = 'none'
    },

    // 档案筛选的过滤算法
    listFilter() {
      let res = [[], [], []] // 根据输入内容划分3个优先级
      if ('' === this.screen) {
        res = [
          1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
          11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
          21, 22, 23, 24, 25, 26, 27, 28, 29, 30
        ]
      } else {
        for (let i = 0; i < this.originList.length; i++) {
          let fr = this.filterRules(this.originList[i])
          if (0 !== fr) {
            res[fr - 1].push(this.originList[i])
          }
        }
        res = res[0].concat(res[1]).concat(res[2])
      }
      // console.log(res)
      typesetting(res)
      this.difference(res, this.list)
      this.list = res
    },

    // 过滤规则，并确定优先级
    filterRules(item) {
      let num = ''
      let screen = this.screen

      for (let i = 0; i < screen.length; i++) {
        if ('日' === screen[i] && '龄' === screen[i + 1]) {
          i = i + 2
          let tmp = '', isAge = false, isRange = false, range = []
          while (' ' !== screen[i] && i < screen.length) {
            if ('、' === screen[i]) {
              if (isRange) {
                range.push(tmp)
                if (-1 !== this.InRangeIndex('age', range).indexOf(item - 1)) isAge = true
              } else {
                if (-1 !== this.getIndex('age', tmp).indexOf(item - 1)) isAge = true
              }
              tmp = ''
              range = []
              isRange = false
            } else if ('-' === screen[i]) {
              isRange = true
              range.push(tmp)
              tmp = ''
            } else {
              tmp += screen[i]
            }
            i++
          }
          if (isRange) {
            range.push(tmp)
            if (-1 !== this.InRangeIndex('age', range).indexOf(item - 1)) isAge = true
          } else {
            if (-1 !== this.getIndex('age', tmp).indexOf(item - 1)) isAge = true
          }
          if (isAge) {
            if (i === screen.length) return 1
          } else {
            return 0
          }
        }
        if ('体' === screen[i] && '重' === screen[i + 1]) {
          i = i + 2
          let tmp = '', isAge = false, isRange = false, range = []
          while (' ' !== screen[i] && i < screen.length) {
            if ('、' === screen[i]) {
              if (isRange) {
                range.push(tmp)
                if (-1 !== this.InRangeIndex('weight', range).indexOf(item - 1)) isAge = true
              } else {
                if (-1 !== this.getIndex('weight', tmp).indexOf(item - 1)) isAge = true
              }
              tmp = ''
              range = []
              isRange = false
            } else if ('-' === screen[i]) {
              isRange = true
              range.push(tmp)
              tmp = ''
            } else {
              tmp += screen[i]
            }
            i++
          }
          if (isRange) {
            range.push(tmp)
            if (-1 !== this.InRangeIndex('weight', range).indexOf(item - 1)) isAge = true
          } else {
            if (-1 !== this.getIndex('weight', tmp).indexOf(item - 1)) isAge = true
          }
          if (isAge) {
            if (i === screen.length) return 1
          } else {
            return 0
          }
        }
        if ('饮' === screen[i] && '水' === screen[i + 1]) {
          i = i + 2
          let tmp = '', isAge = false, isRange = false, range = []
          while (' ' !== screen[i] && i < screen.length) {
            if ('、' === screen[i]) {
              if (isRange) {
                range.push(tmp)
                if (-1 !== this.InRangeIndex('drink', range).indexOf(item - 1)) isAge = true
              } else {
                if (-1 !== this.getIndex('drink', tmp).indexOf(item - 1)) isAge = true
              }
              tmp = ''
              range = []
              isRange = false
            } else if ('-' === screen[i]) {
              isRange = true
              range.push(tmp)
              tmp = ''
            } else {
              tmp += screen[i]
            }
            i++
          }
          if (isRange) {
            range.push(tmp)
            if (-1 !== this.InRangeIndex('drink', range).indexOf(item - 1)) isAge = true
          } else {
            if (-1 !== this.getIndex('drink', tmp).indexOf(item - 1)) isAge = true
          }
          if (isAge) {
            if (i === screen.length) return 1
          } else {
            return 0
          }
        }
        if ('进' === screen[i] && '食' === screen[i + 1]) {
          i = i + 2
          let tmp = '', isAge = false, isRange = false, range = []
          while (' ' !== screen[i] && i < screen.length) {
            if ('、' === screen[i]) {
              if (isRange) {
                range.push(tmp)
                if (-1 !== this.InRangeIndex('eat', range).indexOf(item - 1)) isAge = true
              } else {
                if (-1 !== this.getIndex('eat', tmp).indexOf(item - 1)) isAge = true
              }
              tmp = ''
              range = []
              isRange = false
            } else if ('-' === screen[i]) {
              isRange = true
              range.push(tmp)
              tmp = ''
            } else {
              tmp += screen[i]
            }
            i++
          }
          if (isRange) {
            range.push(tmp)
            if (-1 !== this.InRangeIndex('eat', range).indexOf(item - 1)) isAge = true
          } else {
            if (-1 !== this.getIndex('eat', tmp).indexOf(item - 1)) isAge = true
          }
          if (isAge) {
            if (i === screen.length) return 1
          } else {
            return 0
          }
        }
        // 号数检测
        if ('0' <= screen[i] && screen[i] <= '9') {
          num += screen[i]
        }
      }

      if (String(item) === num) return 1
      else if (String(item).indexOf(num) !== -1) return 2
      else if (num.indexOf(String(item)) !== -1) return 3
      return 0
    },

    // 列表中元素差值检测, b为原始数组
    difference(now, old) {
      // console.log(a, b)
      // 深拷贝
      let a = JSON.parse(JSON.stringify(now))
      let b = JSON.parse(JSON.stringify(old))

      a.sort(function (a, b) {
        return a - b
      })
      b.sort(function (a, b) {
        return a - b
      })
      let pointA = 0, pointB = 0
      while (pointA < a.length && pointB < b.length) {
        if (a[pointA] === b[pointB]) {
          pointA += 1
          pointB += 1
        } else {
          if (a[pointA] < b[pointB]) {
            showCard(a[pointA])
            pointA += 1
          } else {
            hideCard(b[pointB])
            pointB += 1
          }
        }
      }

      // 根据列表差距显隐卡片
      if (pointA !== a.length) {
        for (let i = pointA; i < a.length; i++) {
          showCard(a[i])
        }
      }
      if (pointB !== b.length) {
        for (let i = pointB; i < b.length; i++) {
          hideCard(b[i])
        }
      }
    },

    // 数组查找
    // 判断下标为key的元素是否为value
    getIndex(key, value) {
      let i = 0
      let res = []
      for (; i < pigs.length; i++) {
        if (value === String(pigs[i][key])) {
          res.push(i)
        }
      }
      return res
    },

    // 范围查找
    // 判断下标为key的元素是否在range范围
    InRangeIndex(key, range) {
      let i = 0
      let res = []
      for (; i < pigs.length; i++) {
        if (range[0] <= String(pigs[i][key]) && String(pigs[i][key]) <= range[1]) {
          res.push(i)
        }
      }
      return res
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

.card {
  --translate: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 240px;
  height: 320px;
  border-radius: 20px;
  overflow: hidden;
  cursor: pointer;
  box-shadow: var(--shadow) 1px 1px 4px;
  transition: .6s ease-in-out;
}

.card:hover {
  transform: var(--translate) !important;
  transition: .2s;
}

.cardText {
  position: absolute;
  width: 100%;
  height: 100px;
  bottom: 0;
  border-radius: 0 0 20px 20px;
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-shadow: rgb(149, 149, 149) 1px 1px 4px;
  background: linear-gradient(rgba(255, 255, 255, 0), rgba(255, 255, 255, .8));
  transition: .2s;
}

.message {
  text-align: left;
  opacity: 0;
  transition: .2s;
}

.card:hover > .cardText {
  height: 130px;
}

.card:hover .message {
  opacity: 1;
}

.IDB3-7HY5-H6R7 {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}

.search {
  box-shadow: var(--shadow) 1px 1px 4px;
  border-radius: 20px;
  width: 160px;
  height: 40px;
  padding: 0 40px;
  font-size: 1.2em;
  border: none;
  outline: none;
  background: none;
  transition: .2s;
  z-index: 2;
}

.search:hover {
  box-shadow: var(--shadow) 1px 1px 8px;
}

.searchImg {
  position: absolute;
  filter: invert(100%) opacity(60%) drop-shadow(1px 0 0 var(--theme));
  margin-left: 10px;
  width: 20px;
  z-index: 3;
}

.search:focus {
  border: none;
  width: 300px;
}

.proposal {
  position: absolute;
  background: rgba(255, 255, 255, .5);
  box-shadow: none;
  backdrop-filter: blur(8px);
  width: 240px;
  top: 20px;
  /*padding: 0 40px;*/
  border-radius: 20px;
  height: 40px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  overflow: hidden;
  transition: .2s;
  z-index: 1;
}

.option {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
  width: 100%;
  transition: .2s;
  cursor: pointer;
}

.option:hover {
  box-shadow: 1px 1px 4px var(--shadow) inset;
  font-size: 1.2em;
}

.search:focus + .proposal {
  box-shadow: var(--shadow) 1px 1px 4px;
  width: 380px;
  height: 274px;
}

.show {
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
  justify-content: flex-start;
  pointer-events: none;
  opacity: 0;
  z-index: 10;
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
