<!--个人中心-->
<template>
  <div class="personalCenter" @mouseenter="enterPersonalCenter" @mouseleave="outPersonalCenter">
    <img src="" class="centerImg" alt=""/>
    <div class="centerPage">
      <div style="font-size: 1.4em; color: var(--theme); margin-top: 50px;">{{ user.username }}</div>
      <div style="padding: 20px;">
        <div style="position: relative; width: 100%; height: 1px; background: #555555; margin-bottom: 10px;"></div>
        <div class="option">
          <div>账号</div>
          <div>{{ user.account }}</div>
        </div>
        <div class="option">
          <div>手机号</div>
          <div>{{ user.mobile }}</div>
        </div>
        <div class="option">
          <div>邮箱</div>
          <div>{{ user.email }}</div>
        </div>
        <div style="position: relative; width: 100%; height: 1px; background: #555555; margin: 10px 0;"></div>
        <div class="option" style="cursor: pointer;" @click="exit">
          退出登录
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "personalCenter",
  data() {
    return {
      isMouseIn: false,
      user: {
        username: '管理员 1',
        account: '332116469',
        mobile: '17623503976',
        email: '332116469@qq.com'
      }
    }
  },
  mounted() {
    this.login()
  },
  methods: {
    // 登陆请求用户信息
    login() {
      let headImg = document.querySelector('.personalImg')
      let centerImg = this.$el.getElementsByClassName('centerImg')[0]
      headImg.src = 'static/headImg/head1.jpeg'
      centerImg.src = 'static/headImg/head1.jpeg'
      headImg.style.filter = 'invert(0)'
    },

    // 进入个人中心动画
    enterPersonalCenter() {
      this.isMouseIn = true
      let img = document.querySelector('.personalImg')
      img.style.opacity = '0'

      this.$el.style.opacity = '1'
      this.$el.children[0].style.width = '80px'
      this.$el.children[0].style.height = '80px'
      this.$el.children[0].style.right = '120px'
      this.$el.children[0].style.border = 'white solid 3px'
      this.$el.children[0].style.transition = '.4s'

      this.$el.children[1].style.height = '305px'
      this.$el.children[1].style.transition = '.4s .1s ease-in-out'
    },

    // 离开个人中心动画
    outPersonalCenter() {
      this.isMouseIn = false
      let img = document.querySelector('.personalImg')
      setTimeout(() => {
        if (!this.isMouseIn) {
          img.style.opacity = '1'
          this.$el.style.opacity = '0'
        }
      }, 800)

      this.$el.children[0].style.width = '40px'
      this.$el.children[0].style.height = '40px'
      this.$el.children[0].style.right = '100px'
      this.$el.children[0].style.border = 'white solid 0'
      this.$el.children[0].style.transition = '.4s .3s'

      this.$el.children[1].style.height = '0'
      this.$el.children[1].style.transition = '.4s ease-in-out'
    },

    // 退出登陆
    exit() {
      localStorage.setItem('user', '')
      this.$router.push("/");
    }
  }
}
</script>

<style scoped>
.personalCenter {
  opacity: 0;
  z-index: 10;
}

.centerImg {
  position: fixed;
  width: 40px;
  height: 40px;
  top: 15px;
  right: 100px;
  border: white solid 0;
  border-radius: 50%;
  transition: .4s;
  cursor: pointer;
  z-index: 11;
}

.centerPage {
  position: fixed;
  width: 246px;
  height: 0;
  top: 58px;
  right: 40px;
  background: white;
  border-radius: 20px;
  box-shadow: var(--shadow) 1px 1px 4px;
  transition: .4s .1s ease-in-out;
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
</style>
