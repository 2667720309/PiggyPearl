<template>
  <div>
    <topBar></topBar>
    <div
      style="position: fixed; top: 0; left: 100%; width: 100%; z-index: -1; transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
      <img src="static/dianwei.png" alt="" style="width: 100%; height: 260px; filter: var(--clear); transition: .4s;">
    </div>
    <div class="card"
         style="position: absolute; left: 100%; top: 250px; width: 100%; transition: .8s cubic-bezier(.32,-0.03,0,1.09);">
      <div
        style="position: relative; width: 90%; margin: 20px auto 0; display: flex; flex-wrap: wrap; justify-content: space-between;">
        <div class="assembly" v-for="i in 12"
             style="width: 280px; max-width: 45%; position: relative;">
          <div class="card" style="position: relative; width: 100%; overflow: hidden; padding-bottom: 10px;">
            <div style="position: relative;">
              <img :src="'static/video/pig' + i + '.jpg'" alt="视频"
                   style="position: relative; width: 100%; max-height: 150px;">
              <img src="static/play.png" alt="播放"
                   style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); width: 30%;">
            </div>
            <h4 style="margin-top: 4px;">IPC236{{ i * 184 - 26 }}</h4>
          </div>
        </div>
      </div>
      <div style="position: relative; height: 80px; margin-top: 60px; width: 2px;"></div>
    </div>
    <div
      style="position: fixed; top: 0; left: 0; width: 100%; height: 100%; z-index: -2; background: var(--white); transition: .4s;"></div>
  </div>
</template>

<script>
import topBar from "../../components/topBar";

export default {
  name: "monitoringSites",
  components: {
    topBar
  },
  mounted() {
    let background = this.$el.children[1]
    let content = this.$el.children[2]

    setTimeout(() => {
      background.style.left = '0'
      content.style.left = '0'
    }, 20)
  }
}
</script>

<style scoped>

</style>
