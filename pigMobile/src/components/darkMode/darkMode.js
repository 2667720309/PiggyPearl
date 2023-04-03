export {
  dayNightSwitch, autoDayNightSwitch
}

// 深色模式切换
function dayNightSwitch() {
  let root = document.querySelector('body')
  root.classList.toggle('dark')
}

// 日落时自动切换深色模式
function autoDayNightSwitch() {
  let myDate = new Date();
  let h = myDate.getHours();

  if (h <= 6 || h >= 18) {
    dayNightSwitch()
  }
}
