// 封装的提示窗口
let isReady = true
let enterLoop = false
let cache = []

// 显示提示
// 可自定义显示时间
export function showTips(text, showTime=1000) {
  cache.push(text) // 文本添加到缓存区
  if (!enterLoop) { // 只激活一个循环，节省性能
    enterLoop = true
    loop(showTime)
    enterLoop = false
  }
}

function loop(showTime) {
  if (undefined !== cache[0]) { // 缓存区有文本才继续循环
    if (isReady) { // 上一段文本显示结束后才能显示下一段文本，目前该算法有bug
      isReady = false
      addTipsText()
      tipsIn()

      setTimeout(() => {
        tipsOut()
      }, 400 + showTime)
      setTimeout(() => {
        isReady = true
      }, 400 + showTime + 400)
    }

    // 用while循环网页会卡住，直到while循环结束，所以用异步循环
    setTimeout(() => {
      loop()
    }, 200)
  }
}

// 从缓存中添加提示文本并删除缓存
function addTipsText() {
  const tips = document.querySelector('#tips')
  tips.textContent = cache[0]
  cache.shift()
}

// 显示提示动画
function tipsIn() {
  const tips = document.querySelector('#tips')
  tips.style.transform = 'translate(-50%)'
}

// 提示结束动画
function tipsOut() {
  const tips = document.querySelector('#tips')
  tips.style.transform = 'translate(-50%, -200px)'
}
