import store from "./store";

// 控制每个页面进入时的放大动画
export function mainPageInter(order) {
  let setPage = store.state.isSettingOpen ? 300 + 20 : 0

  let home = document.querySelector('#home')
  home.style.transform = 'scale(1， 1)'
  home.style.top = 65 + 60 * (order - 1) + 'px'
  home.style.height = '0'
  home.style.width = '0'
  home.style.borderRadius = 62 * order + 'px 20px 20px 90%'

  // 从以上位置到下列位置
  setTimeout(() => {
    home.style.top = '65px'
    home.style.height = window.innerHeight - 65 - 20 + 'px'
    home.style.width = window.innerWidth - 75 - 40 - setPage + 'px'
    home.style.borderRadius = '20px'
  }, 20)
  return {height: window.innerHeight - 65 - 20, width: window.innerWidth - 75 - 40 - setPage}
}
