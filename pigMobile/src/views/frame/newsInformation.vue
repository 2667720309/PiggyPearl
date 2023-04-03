<template>
  <div style="transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
    <topBar></topBar>
    <div class="assembly" v-for="i in news">
      <div class="card"
           style="width: 100%; display: flex; align-items: center; justify-content: space-between; padding: 10px 20px;">
        <div style="height: 150px; width: 200px; overflow: hidden;">
          <img :src="i.url" alt="图片" style="width: 100%; position: relative; top: 50%; transform: translate(0, -50%);">
        </div>
        <div style="text-align: end;">
          <div class="header">
            {{ i.title }}
          </div>
          <div class="header" style="color: rgb(128, 128, 128);">
            {{ i.time }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar";
import {pageEnter} from "../../assets/js/main";

export default {
  name: "newsInformation",
  data() {
    return {
      news: [
        {
          title: '农业农村部：推进生猪产业转型升级，养殖、屠宰、冷链物流等成为主要内容',
          time: '2021-04-27 21:03:53',
          url: 'static/news/img.png'
        },
        {
          title: '如何理解中央一号文件生猪产业政策取向',
          time: '2021-03-12 10:23:35',
          url: 'static/news/img_1.png'
        },
        {
          title: '最高奖3万!官方设非瘟假疫苗有奖举报!严查养殖场户违法行为',
          time: '2021-03-09 10:18:40',
          url: 'static/news/img_2.png'
        },
        {
          title: '关于生猪期货2109合约价格的逻辑梳理',
          time: '2021-01-29 09:51:34',
          url: 'static/news/img_3.png'
        },
        {
          title: '生猪也能进行抵押贷款?企业融资难，重庆这样试点',
          time: '2021-01-27 09:55:46',
          url: 'static/news/img_4.png'
        },
        {
          title: '母猪发情延迟、发情不明显、不发情怎么处理？',
          time: '2021-04-28 20:40:14',
          url: 'static/news/img_5.png'
        },
        {
          title: '三大“猪王”环境违法，产能超6500头',
          time: '2022-04-07 10:33:09',
          url: 'static/news/img_6.png'
        },
        {
          title: '全员撤出隔离，家里的猪怎么办？官方政策出台应对！',
          time: '2021-01-22 10:10:51',
          url: 'static/news/img_7.png'
        },
        {
          title: '广东：关于进一步推进中南区生猪“点对点”调运工作的通知',
          time: '2020-12-04 13:08:38',
          url: 'static/news/img_8.png'
        },
        {
          title: '猪肉价格七连降背后：猪业复兴压力仍大！',
          time: '2020-10-28 10:51:01',
          url: 'static/news/img_9.png'
        },
        {
          title: '我们为什么要接种疫苗？',
          time: '2020-05-11 11:42:00',
          url: 'https://n.sinaimg.cn/baby/transform/500/w300h200/20200509/2888-itmiwry1423630.jpg'
        },
        {
          title: '文旅部：旅游发展解决贫困户脱贫占整个扶贫总任务的17%-20%',
          time: '2020-05-11 11:23:00',
          url: 'https://n.sinaimg.cn/travel/transform/266/w640h426/20200501/d017-isyparf8021392.jpg'
        },
        {
          title: '习近平谈防灾减灾：从源头上防范',
          time: '2020-05-11 16:15:00',
          url: 'https://n.sinaimg.cn/default/2fb77759/20151125/320X320.png'
        }
      ]
    }
  },
  mounted() {
    pageEnter(this.$el, this.$route.name, this.$route.query.page)
  },
  components: {
    topBar
  }
}
</script>

<style scoped>

</style>
