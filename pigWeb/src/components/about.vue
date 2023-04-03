<!--关于掌上明猪的居中小卡片-->
<template>
  <div id="about">
    <div style="display: flex; position: relative; align-items: flex-start;">
      <img src="static/logo.png" class="logo" alt="">
      <div style="display: flex; flex-direction: column; align-items: flex-start; width: 100%;
           cursor: default; margin-left: 10px; text-align: left; color: #555555;">
        <div style="font-size: 2em; white-space: nowrap; color: var(--theme);">
          掌上明猪v1.0
        </div>
        <div style="font-size: .8em; color: var(--theme);">内部版本号&nbsp;#PC-22.1214.43，2022年12月14日 构建</div>
        <div style="margin-top: 10px;">账户：管理员1</div>
        <div>订阅有效期至 2099年12月31日</div>
        <div>限中小型生猪养殖户及散户使用</div>
        <div style="margin-top: 10px;">构建框架：VUE</div>
        <div>Copyright © 2020-2022 掌上明猪</div>
        <div style="text-align: right; width: 100%; font-size: 1.5em;">
          <div style="margin-left: 262px; background: #888888; color: black; cursor: pointer;
               padding: 0 10px; border-radius: 20px; box-shadow: 0 0 4px gold;" @click="hide">确定</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "about",
  methods: {
    // 点击确定后隐藏卡片
    hide() {
      this.$el.style.opacity = '0'
      setTimeout(() => {
        this.$el.style.pointerEvents = 'none'
      }, 400)
    }
  }
}
</script>

<style scoped>
#about {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 400px;
  height: 230px;
  border-radius: 20px;
  padding: 20px;
  background: rgba(255, 255, 255, .4);
  backdrop-filter: blur(8px);
  box-shadow: 1px 1px 4px var(--shadow);
  overflow: hidden;
  transition: .4s;
  pointer-events: none;
  opacity: 0;
  z-index: 100;
}

.logo {
  height: 60px;
  transform: translate(-100px);
  border-radius: 5px;
  animation: reLogo linear infinite;
  animation-duration: 1s;
  filter: drop-shadow(100px 0 0 var(--theme));
  box-shadow: 0 0 8px var(--shadow);
}
</style>
