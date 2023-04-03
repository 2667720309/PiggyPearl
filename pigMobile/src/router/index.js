import Vue from 'vue'
import Router from 'vue-router'
import frame from "../views/frame";
import home from "../views/frame/home";
import pigInformation from "../views/frame/pigInformation";
import newsInformation from "../views/frame/newsInformation";
import login from "../views/login";
import video from "../views/video";
import monitoringSites from "../views/video/monitoringSites";
import pigDiagnosis from "../views/video/pigDiagnosis";
import aquacultureArchives from "../views/video/aquacultureArchives";

// 解决重复点击本页面报错问题
const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err)
}
const originalPush = Router.prototype.push;
Router.prototype.replace = function replace(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/app',
      component: frame,
      redirect: '/app/home',
      children: [
        {
          path: 'home',
          name: '猪场信息',
          component: home,
        },
        {
          path: 'pigInformation',
          name: '生猪信息',
          component: pigInformation
        },
        {
          path: 'newsInformation',
          name: '新闻资讯',
          component: newsInformation
        },
        {
          path: 'video',
          name: '监控信息',
          component: video,
          children: [
            {
              path: 'monitoringSites',
              component: monitoringSites,
              name: '监控点位',
            },
            {
              path: 'pigDiagnosis',
              component: pigDiagnosis,
              name: '生猪诊断',
            },
            {
              path: 'aquacultureArchives',
              component: aquacultureArchives,
              name: '养殖档案',
            }
          ]
        }
      ]
    },
    {
      path: '/login',
      component: login,
      name: '登录',
    },
    {
      path: '/share',
      component: {
        template: '<div style="position: absolute; top: 50%; left: 0; width: 100%; transform: translate(0, -50%);">' +
          '<img src="static/QRCode.png" alt="" style="position: relative; top: 0; left: 0; width: 80%;">' +
          '</div>'
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/share') return next();
  const tokenStr = localStorage.getItem('token');
  if (tokenStr !== 'ABDUENR@5435lNGE(*)&(*&^') return next('/login');
  next();
})

export default router
