<!--封装的加载动画-->
<template>
  <div class="body">
    <div class="loader">
      <span></span>
      <span></span>
      <span></span>
      <h2>Loading...</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "loading"
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.body {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  margin-top: -30px;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  pointer-events: none;
  z-index: 50;
  transition: 0.5s;
}

.loader {
  position: relative;
  width: 200px;
  height: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.loader span {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  box-shadow: 0 0 4px white;
  border: 2px solid black;
  pointer-events: none;
  animation: animate 8s linear infinite;
}

.loader span:nth-child(1) {
  border-radius: 38% 62% 64% 36% / 43% 35% 65% 57%;
}

.loader span:nth-child(2) {
  animation-direction: reverse;
  border-radius: 41% 59% 40% 60% / 65% 66% 34% 35%;
}

.loader span:nth-child(3) {
  animation-duration: 5s;
  border-radius: 73% 27% 56% 44% / 57% 74% 26% 43%;
}

@keyframes animate {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.loader h2 {
  color: black;
  font-family: consolas, serif;
  font-weight: 500;
  display: inline-block;
  text-shadow: 1px 1px 4px white;
  overflow: hidden;
  animation: textTyping 1.2s linear infinite;
}

@keyframes textTyping {
  0% {
    width: 80px;
  }
  80%, 100% {
    width: 98px;
  }
}
</style>
