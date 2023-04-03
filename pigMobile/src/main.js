import Vue from 'vue'
import App from './App'
import router from './router'
import './components/darkMode/darkMode.css'
import './assets/css/main.css'

import * as echarts from 'echarts'

Vue.prototype.$echarts = echarts
Vue.config.productionTip = false

import service from './api/request'

Vue.prototype.$http = service

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
