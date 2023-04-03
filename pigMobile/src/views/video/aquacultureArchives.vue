<template>
  <div style="transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
    <topBar></topBar>
    <div class="assembly" v-for="i in 5">
      <div class="card"
           style="width: 100%; display: flex; align-items: center; justify-content: space-between; padding: 10px 40px;">
        <div style="height: 150px; width: 150px; border-radius: 50%; overflow: hidden;">
          <img :src="'static/pigsHead/pig.' + i + '.png'" alt="猪脸" style="width: 100%;">
        </div>
        <div class="header" style="display: flex; align-items: center;">
          <div>第{{ i }}号猪</div>
          <img src="static/向右箭头.png" alt=">" style="height: 18px;">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import topBar from "../../components/topBar";
import {pageEnter} from "../../assets/js/main";

export default {
  name: "aquacultureArchives",
  data() {
    return {}
  },
  components: {
    topBar
  },
  mounted() {
    pageEnter(this.$el, this.$route.name, this.$route.query.page)
  }
}
</script>

<style scoped>

</style>
