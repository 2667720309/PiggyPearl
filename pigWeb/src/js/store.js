// 全局的参数仓库
// 无论页面如何跳转，state里的参数修改后不会被初始化
// 刷新页面后初始化
// 如果想刷新后不初始化，可以使用localStorage，主题色切换就是用的这种方法
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    isSettingOpen: false
  }
})
