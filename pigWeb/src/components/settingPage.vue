<!--设置页面-->
<template>
  <div class="settingPage">
    <div style="margin: 20px;">
      <div class="option" @click="accountSecurity">
        <div>账号安全</div>
        <div>></div>
      </div>
      <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
      <div class="option" style="pointer-events: none;">
        <div>更改主题</div>
      </div>
      <div style="width: 240px; display: flex; justify-content: space-between; flex-wrap: wrap;
           position: relative; padding: 0 10px;">
        <div class="card theme"
             :style="'background: ' + i"
             @click="switchTheme(i, n)" v-for="(i, n) in theme"></div>
      </div>
      <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
      <div class="option" @click="userAgreement">
        <div>用户协议</div>
        <div>></div>
      </div>
      <div class="option" @click="privacyPolicy">
        <div>隐私政策</div>
        <div>></div>
      </div>
      <div class="option" @click="versionUpdate">
        <div>版本更新</div>
        <div>v1.0</div>
      </div>
      <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
      <div class="option" @click="abouts">
        <div>关于掌上明猪</div>
        <div>></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "settingPage",
  data() {
    return {
      theme: [ // 主题色
        '#6E7FF3', '#cc2e63', '#aa96da', '#a8d8ea',
        '#ffc7c7', '#69EACB', '#cce800', '#F5A25D'
      ],
      isNum: null
    }
  },
  mounted() {
    let set = this.$el
    set.style.height = window.innerHeight - 65 - 20 + 'px'

    this.activeTheme(this.theme.indexOf(document.documentElement.style.getPropertyValue('--theme')))
  },
  methods: {
    // 切换主题色
    switchTheme(color, i) {
      this.activeTheme(i)
      document.documentElement.style.setProperty('--theme', color)
      document.documentElement.style.setProperty('--shadow', color + '50')
      localStorage.setItem('theme', color)
    },

    // 显示激活的主题色的颜色，其他为灰色
    activeTheme(i) {
      let themes = this.$el.getElementsByClassName('theme')
      themes[i].classList.toggle('activeTheme')
      if (this.isNum !== null) {
        themes[this.isNum].classList.toggle('activeTheme')
      }
      this.isNum = i
    },

    // 点击词条后出现提示
    accountSecurity() { this.$tip('手机号、邮箱已绑定，账号状态安全') },
    userAgreement() { this.$tip('暂无用户协议') },
    privacyPolicy() { this.$tip('暂无隐私政策') },
    versionUpdate() { this.$tip('当前已为最新版本') },

    // 显示关于慧眼识猪卡片
    abouts() {
      let about = document.querySelector('#about')
      about.style.pointerEvents = 'auto'
      about.style.opacity = '1'
    }
  }
}
</script>

<style scoped>
.settingPage {
  position: fixed;
  background: rgb(250, 250, 250);
  right: 40px;
  transform: translate(20px);
  top: 65px;
  height: 783px;
  width: 0;
  border-radius: 20px;
  box-shadow: var(--shadow) 1px 1px 4px inset;
  transition: .6s ease-in-out;
  overflow-x: hidden;
  z-index: 10;
}

.option {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 20px;
  padding: 10px;
  height: 20px;
  transition: .2s;
  cursor: pointer;
}

.option:hover {
  background: var(--theme);
}

.theme {
  height: 52px;
  width: 52px;
  border-radius: 20px;
  margin-bottom: 10px;
  cursor: pointer;
  filter: grayscale(1);
  transition: .4s;
}

.theme:hover {
  filter: grayscale(0);
}

.activeTheme {
  filter: grayscale(0);
}
</style>
