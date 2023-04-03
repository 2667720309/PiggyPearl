<template>
  <div>
    <div class="ring card">
      <div class="innerRing antiCard"></div>
      <div class="circular" style="top: -80%; left: 45%;"></div>
      <div class="rotate">
        <div class="circular card"></div>
      </div>
    </div>
    <div class="ring" v-if="ringData[1]" style="position: relative; top: -90%; left: 10%; width: 80%; height: 80%;">
      <div class="innerRing antiCard"></div>
      <div class="circular" style="top: -80%; left: 45%;"></div>
      <div class="rotate">
        <div class="circular card"></div>
      </div>
    </div>
    <div class="ring" v-if="ringData[2]" style="position: relative; top: -162%; left: 18%; width: 64%; height: 64%;">
      <div class="innerRing antiCard"></div>
      <div class="circular" style="top: -80%; left: 45%;"></div>
      <div class="rotate">
        <div class="circular card"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "movableRing",
  mounted() {
    let ring = this.$el.getElementsByClassName('ring')
    let rotate = this.$el.getElementsByClassName('rotate')
    let circular = this.$el.getElementsByClassName('circular')
    for (let i = 0; i < 3; i++) {
      let proportion = this.ringData[i].value / this.ringData[i].max * 100
      ring[i].style.background = 'conic-gradient(' + this.ringData[i].color + ' 0%,' +
        '' + this.ringData[i].color + ' ' + proportion + '%,' +
        '' + this.ringData[i].vacuityColor + ' ' + proportion + '%,' +
        '' + this.ringData[i].vacuityColor + ' 100%)'

      circular[i * 2].style.background = this.ringData[i].color
      circular[i * 2 + 1].style.background = this.ringData[i].color

      rotate[i].style.transform = 'rotate(' + proportion * 3.65 + 'deg)'
    }
  },
  props: {
    ringData: {}
  }
}
</script>

<style scoped>
.ring {
  height: 100%;
  width: 100%;
  border-radius: 50%;
  transition: 0.4s;
}

.innerRing {
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 80%;
  height: 80%;
  background: var(--white);
  border-radius: 50%;
  transition: 0.4s;
}

.circular {
  position: relative;
  border-radius: 50%;
  width: 10%;
  height: 10%;
  overflow: hidden;
  transition: 0.4s;
}

.rotate {
  position: relative;
  width: 100%;
  height: 100%;
  top: -90%;
  display: flex;
  flex-direction: column;
  align-items: center;
  transition: 1s ease;
}
</style>
