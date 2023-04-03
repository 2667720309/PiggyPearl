<template>
  <div style="position: relative;">
    <div class="frame">
      <div class="row">
        <p></p>
      </div>
    </div>
    <div class="frame">
      <div class="row">
        <p></p>
      </div>
    </div>
    <div class="frame">
      <div class="row">
        <p></p>
      </div>
    </div>
    <div class="frame">
      <div class="row">
        <p></p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "loading"
}
</script>

<style scoped>
.frame {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}

.frame:nth-child(1) {
  transform: rotate(0deg);
}

.frame:nth-child(2) {
  transform: rotate(90deg);
}

.frame:nth-child(3) {
  transform: rotate(180deg);
}

.frame:nth-child(4) {
  transform: rotate(270deg);
}

.row {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  transform: rotate(45deg);
  animation: rotate linear infinite;
  animation-duration: 4.8s;
}

p {
  position: absolute;
  width: 33.3%;
  height: 33.3%;
  top: 33.3%;
  left: 33.3%;
  border: black 2px solid;
  animation: move linear infinite;
  animation-duration: 4.8s;
}

@keyframes rotate {
  0% {
    transform: rotate(45deg);
  }
  23%, 27% {
    transform: rotate(135deg);
  }
  50% {
    transform: rotate(225deg);
  }
  73%, 77% {
    transform: rotate(315deg);
  }
  100% {
    transform: rotate(405deg);
  }
}

@keyframes move {
  0%, 2%, 48%, 52%, 98%, 100% {
    top: 33.3%;
  }
  23%, 27%, 73%, 77% {
    top: 0;
  }
}
</style>
