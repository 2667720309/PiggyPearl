<template>
  <div>
    <!--    宽屏遮罩层-->
    <div class="mask">
      <div>请降低浏览器宽度或使用移动端打开</div>
      <loading style="width: 100px; height: 100px;"></loading>
    </div>
  </div>
</template>

<script>
import loading from "./loading";

export default {
  name: "widthLimitMask",
  components: {
    loading
  }
}
</script>

<style scoped>
.mask {
  position: fixed;
  display: none;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 5em;
  color: var(--black);
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, .3);
  -webkit-backdrop-filter: blur(20px);
  backdrop-filter: blur(20px);
  transition: 0.4s;
  z-index: 100000;
}

@media (min-width: 1025px) {
  .mask {
    display: flex;
  }
}
</style>
