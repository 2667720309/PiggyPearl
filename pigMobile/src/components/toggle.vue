<template>
  <div class="checkbox antiCard" @click="toggle">
    <div class="toggle card"></div>
  </div>
</template>

<script>
export default {
  name: "toggle",
  mounted() {
    let checkbox = this.$el
    let toggle = this.$el.children[0]

    if (this.isClose) {
      checkbox.style.background = 'rgba(255, 48, 0, 1)'
      toggle.style.left = '2px'
    } else {
      checkbox.style.background = 'rgba(19, 228, 0, 1)'
      toggle.style.left = '22px'
    }
  },
  methods: {
    toggle() {
      let checkbox = this.$el
      let toggle = this.$el.children[0]

      this.isClose = !this.isClose
      if (this.isClose) {
        checkbox.style.background = 'rgba(255, 48, 0, 1)'
        toggle.style.left = '2px'
      } else {
        checkbox.style.background = 'rgba(19, 228, 0, 1)'
        toggle.style.left = '22px'
      }
    }
  },
  props: {
    isClose: Boolean
  }
}
</script>

<style scoped>
.checkbox {
  display: flex;
  align-items: center;
  width: 46px;
  height: 26px;
  border-radius: 13px;
  background: rgba(255, 48, 0, 1);
  overflow: hidden;
  cursor: pointer;
  transition: .4s;
}

.toggle {
  position: relative;
  left: 2px;
  width: 22px;
  height: 22px;
  border-radius: 50%;
  transition: .4s ease;
}
</style>
