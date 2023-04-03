// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./js/store";

Vue.config.productionTip = false

import service from './api/request'
Vue.prototype.$http = service

import * as echarts from 'echarts'
Vue.prototype.$ch = echarts

import {showTips} from "./js/tips";
Vue.prototype.$tip = showTips

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
