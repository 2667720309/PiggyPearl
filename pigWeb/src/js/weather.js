// 将天气中文转换为英文，默认值cloudy
function weather2url(value) {
  let obj = {
    'slight': '晴',
    'cloudy': '多云',
    'partly_cloudy': '阴',
    'rainy': '小雨',
  }
  let res = Object.keys(obj).find(key => obj[key] === value)
  return res ? res : 'cloudy'
}

// 天气卡片宽度控制
// 根据电脑屏幕显示宽度应用两种布局效果
function widthControl() {
  let weather = document.querySelector('.weather')
  if (360 <= weather.getBoundingClientRect().width) {
    weather.children[0].style.justifyContent = 'space-between'
    weather.children[0].children[1].style.transform = 'translate(0)'
    weather.children[0].children[0].style.transform = 'translate(0)'
    weather.children[0].children[1].style.filter = ''
  } else {
    weather.children[0].style.justifyContent = 'center'
    weather.children[0].children[1].style.transform = 'translate(-66.5px)'
    weather.children[0].children[0].style.transform = 'translate(114.5px)'
    weather.children[0].children[1].style.filter = 'blur(8px)'
  }
}

export {
  weather2url,
  widthControl
}
