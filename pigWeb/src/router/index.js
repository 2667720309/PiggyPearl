import Vue from 'vue'
import Router from 'vue-router'
import frame from "../frame";
import home from "../views/home";
import archives from "../views/archives";
import test5 from "../views/test5";
import monitor from "../views/monitor";
import cloud from "../views/cloud";
import login from "../views/login";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: frame,
      redirect: '/login',
      children: [
        {
          path: '/home',
          name: '摘要',
          component: home,
        },
        {
          path: '/archives',
          name: '档案',
          component: archives
        },
        {
          path: '/monitor',
          name: '监控',
          component: monitor
        },
        {
          path: '/cloud',
          name: '云盘',
          component: cloud
        },
        {
          path: '/test5',
          name: '测试2',
          component: test5
        },
      ]
    },
    {
      path: '/login',
      component: login
    }
  ]
})
