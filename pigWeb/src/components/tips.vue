<!--封装的提示窗口-->
<template>
  <div id="tips">
  </div>
</template>

<script>
export default {
  name: "tips"
}
</script>

<style scoped>
#tips {
  position: fixed;
  top: 15px;
  left: 50%;
  min-width: 160px;
  max-width: 600px;
  min-height: 20px;
  border-radius: 20px;
  padding: 10px 20px;
  color: var(--theme);
  background: white;
  box-shadow: 1px 1px 4px var(--shadow);
  transform: translate(-50%, -200px);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 20;
  transition: .4s ease-in-out;
}
</style>
