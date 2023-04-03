// 生猪档案的卡片移动动画
import store from "./store";

let cards
let oldList
const defaultParameter = { // 默认属性
  top: 84,
  bottom: 40,
  marginRow: 20, // 卡片行间距
  marginCol: 20, // 卡片列间距
  width: 240, // 卡片宽
  height: 320 // 卡片高
}

// 初始化，获取dom上所有卡片
function initCard() {
  cards = document.getElementsByClassName('card')
}

// 卡片排版
function typesetting(list) {
  list = list ? list : oldList // 将list作为可选参数，按照list给定顺序排版，如果不选参数则按照历史顺序排版
  oldList = list

  // 按照屏幕宽度计算一行的卡片数
  let setPage = store.state.isSettingOpen ? 300 + 20 : 0
  let width = window.innerWidth - 75 - 40 - setPage
  let rowNum = Math.floor(width / (defaultParameter.width + defaultParameter.marginRow))

  // let realMarginRow = (width - rowNum * defaultParameter.width) / (rowNum + 1)
  // 按照均分计算实际的行间距
  let realMarginRow = (width - defaultParameter.marginRow * 2 - defaultParameter.width * rowNum) / (rowNum - 1)
  defaultParameter.marginCol = realMarginRow

  // 设置每张卡片的位置
  for (let i = 0; i < list.length; i++) {
    // let offsetX = realMarginRow + (defaultParameter.width + realMarginRow) * (i % rowNum)
    // 计算卡片位置
    let offsetX = defaultParameter.marginRow + (defaultParameter.width + realMarginRow) * (i % rowNum)
    let offsetY = defaultParameter.top + (defaultParameter.height + defaultParameter.marginCol) * Math.floor(i / rowNum)

    cards[list[i] - 1].style.transform = 'translate(' + offsetX + 'px, ' + offsetY + 'px)'
    cards[list[i] - 1].style.setProperty('--translate', 'translate(' + offsetX + 'px, ' + offsetY + 'px) scale(1.1)')
  }

  // 更新优化布局样式的底边位置
  createFloor(defaultParameter.top + (defaultParameter.height + defaultParameter.marginCol) * Math.ceil(list.length / rowNum) - defaultParameter.marginCol)
}

// 隐藏卡片
function hideCard(index) {
  // console.log('hide:' + index)
  cards[index - 1].style.opacity = '0'

  // 加延迟是因为设置display后布局会直接崩(卡片会有瞬移)，所以在动画执行完毕后再隐藏卡片
  setTimeout(() => {
    if ('0' === cards[index - 1].style.opacity) {
      cards[index - 1].style.display = 'none'
    }
  }, 600)
}

// 显示卡片
function showCard(index) {
  // console.log('show:' + index)
  cards[index - 1].style.display = 'block'
  setTimeout(() => {
    cards[index - 1].style.opacity = '1'
  }, 20)
}

// 创建底边
// 底边是一个空div，没有任何东西，仅为了优化布局bug
function createFloor(offset) {
  let floor = document.querySelector('.IDB3-7HY5-H6R7')
  floor.style.height = defaultParameter.bottom + 'px'
  floor.style.transform = 'translate(0, ' + offset + 'px)'
}

export {
  typesetting, hideCard, showCard, initCard
}
