<template>
  <div>
    <img src="static/loginBackground.jpeg" alt="" class="background">
    <div class="title card">掌上明猪</div>
    <div class="login card" @click="open" @keydown.enter="keyDownJudge">
      <div v-if="!isOpen"><img src="static/logo.png" alt="" style="width: 80%; transition: .4s;"></div>
      <div v-else class="loginBox">
        <div class="loginInput antiCard">
          <input type="text" class="username"
                 style="position: relative; width: 100%; height: 100%; padding: 0 10px; color: black;"
                 placeholder="请输入账号" v-model="loginForm.accountNumber">
        </div>
        <div class="loginInput antiCard">
          <input type="password" class="password"
                 style="position: relative; width: 100%; height: 100%; padding: 0 10px; color: black;"
                 placeholder="请输入密码" v-model="loginForm.password">
        </div>
        <div @click="goToHome(); loginClick($event)" class="card"
             style="height: 30px; display: flex; align-items: center; justify-content: center; width: 60px; font-weight: bold; cursor: pointer; transition: .2s;">
          登录
        </div>
        <div @click="openTips(); loginClick($event)" class="card"
             style="position: absolute; right: 20px; bottom: 32px; transition: .2s; height: 30px; width: 60px; display: flex; align-items: center; justify-content: center; cursor: pointer; font-weight: bold;">
          注册
        </div>
      </div>
    </div>
    <div class="card"
         style="position: absolute; width: 600px; top: 90px; overflow: hidden; left: 50%; transform: translate(-50%); border-top-left-radius: 0; border-top-right-radius: 0;">
      <div class="tips">
        <div>&nbsp;</div>
        <div>{{ tips }}</div>
        <div class="loginInput antiCard" style="margin-top: 10px;">
          <input type="password" class="password"
                 style="position: relative; width: 100%; height: 100%; padding: 0 10px"
                 placeholder="请再次输入密码" v-model="loginForm.passwordConfirmation">
        </div>
        <div @click="register(); registerClick($event)" class="card"
             style="height: 30px; display: flex; align-items: center; justify-content: center; width: 100px; font-weight: bold; cursor: pointer; margin-top: 10px; transition: .2s;">
          注册并登录
        </div>
      </div>
      <div
        style="position: absolute; top: 0; left: 0; width: 100%; height: 200px; backdrop-filter: blur(8px); z-index: -1;"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      isOpen: false,
      loginForm: {
        accountNumber: '',
        password: '',
        passwordConfirmation: ''
      },
      tips: '',
      oldTips: ''
    }
  },
  mounted() {
    // token判断
    const tokenStr = localStorage.getItem('token')
    if (tokenStr === 'ABDUENR@5435lNGE(*)&(*&^') {
      const user = JSON.parse(localStorage.getItem('user'))
      this.loginForm.accountNumber = user.account
      this.loginForm.password = user.password
      this.goToHome()
    }
  },
  methods: {
    // 打开登陆窗口动画
    open() {
      let background = document.getElementsByClassName('background')
      let title = document.getElementsByClassName('title')
      let login = document.getElementsByClassName('login')

      background[0].style.bottom = '-120px'
      title[0].style.top = '20px'
      login[0].style.width = '600px'
      login[0].style.borderRadius = '10px'
      login[0].style.height = '200px'

      this.isOpen = true
    },

    // 打开提示窗口
    openTips() {
      let tips = this.$el.querySelector('.tips')

      tips.style.height = '130px'
    },

    // 按下回车后检查输入内容
    keyDownJudge() {
      let username = this.$el.querySelector('.username')
      let password = this.$el.querySelector('.password')

      if (this.loginForm.accountNumber === '') {
        username.focus()
      } else if (this.loginForm.password === '') {
        password.focus()
      }
      this.goToHome()
    },

    // 点击动画
    loginClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.width = '40px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.width = '60px'
      }, 200)
    },

    // 登陆判断
    goToHome() {
      let tips = this.$el.querySelector('.tips')

      if (this.loginForm.accountNumber === '') {
        this.tips = '请输入账号'
        tips.style.height = '50px'
        this.fontVibration()
        return
      }
      if (this.loginForm.password === '') {
        this.tips = '请输入密码'
        tips.style.height = '50px'
        this.fontVibration()
        return
      }
      if (!/^[\dA-Za-z]{6,18}$/.test(this.loginForm.accountNumber)) {
        this.tips = '账号仅由数字和字母组成,且在6-18位之间'
        tips.style.height = '50px'
        this.fontVibration()
        return
      }
      if (!/^[\dA-Za-z]{6,18}$/.test(this.loginForm.password)) {
        this.tips = '密码仅由数字和字母组成,且在6-18位之间'
        tips.style.height = '50px'
        this.fontVibration()
        return
      }

      tips.style.height = '50px'
      if (this.loginForm.accountNumber !== '123456') {
        this.tips = '用户不存在，是否注册？'
        tips.style.height = '130px'
        this.fontVibration()
        return
      }
      if (this.loginForm.password !== '123456') {
        this.tips = '密码错误！'
        this.fontVibration()
        return
      }
      this.tips = '登录成功'
      this.fontVibration()
      // localStorage.setItem('token', 'ABDUENR@5435lNGE(*)&(*&^')
      // localStorage.setItem('user', JSON.stringify(res.user)) // JSON.parse(localStorage.getItem('user'))
      this.$router.push("/home")
      // this.$http({
      //   url: 'user/login',
      //   method: 'POST',
      //   params: {
      //     account: this.loginForm.accountNumber,
      //     password: this.loginForm.password
      //   }
      // }).then(res => {
      //   this.tips = res.state
      //   tips.style.height = '45px'
      //
      //   if (this.tips === '用户不存在') {
      //     this.tips += '，是否注册？'
      //     tips.style.height = '130px'
      //     this.fontVibration()
      //   }
      //
      //   if (this.tips === '登录成功') {
      //     this.fontVibration()
      //     localStorage.setItem('token', 'ABDUENR@5435lNGE(*)&(*&^')
      //     localStorage.setItem('user', JSON.stringify(res.user)) // JSON.parse(localStorage.getItem('user'))
      //     this.$router.push({
      //       path: '/app',
      //       query: {
      //         page: this.$route.name
      //       }
      //     })
      //   }
      // })
    },

    // 点击动画
    registerClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.width = '80px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.width = '100px'
      }, 200)
    },

    // 注册判断
    register() {
      let tips = this.$el.querySelector('.tips')

      if (this.loginForm.accountNumber === '' ||
        this.loginForm.password === '' ||
        this.loginForm.passwordConfirmation === '') {
        this.tips = '账号或密码为空'
        this.fontVibration()
        return
      }
      if (!/^[\dA-Za-z]{6,18}$/.test(this.loginForm.accountNumber)) {
        this.tips = '账号仅由数字和字母组成,且在6-18位之间'
        tips.style.height = '45px'
        this.fontVibration()
        return
      }
      if (!/^[\dA-Za-z]{6,18}$/.test(this.loginForm.password)) {
        this.tips = '密码仅由数字和字母组成,且在6-18位之间'
        tips.style.height = '45px'
        this.fontVibration()
        return
      }
      if (this.loginForm.password !== this.loginForm.passwordConfirmation) {
        this.tips = '两次密码不一致'
        tips.style.height = '130px'
        this.fontVibration()
        return
      }
      // this.$http({
      //   url: 'user/register',
      //   method: 'POST',
      //   params: {
      //     account: this.loginForm.accountNumber,
      //     password: this.loginForm.password
      //   }
      // }).then(res => {
      //   this.tips = res.state
      //   if (this.tips === '注册成功') {
      //     this.fontVibration()
      //     this.goToHome()
      //   }
      // })
      this.tips = '注册失败'
      this.fontVibration()
    },

    // 提示窗口震动动画
    fontVibration() {
      if (this.oldTips === this.tips) {
        let tips = this.$el.querySelector('.tips')

        const range = 3
        tips.style.transform = 'translate(' + range + 'px)'
        setTimeout(() => {
          tips.style.transform = 'translate(-' + range + 'px)'
        }, 100)
        setTimeout(() => {
          tips.style.transform = 'translate(' + range + 'px)'
        }, 200)
        setTimeout(() => {
          tips.style.transform = 'translate(0)'
        }, 300)
      }
      this.oldTips = this.tips
    }
  }
}
</script>

<style scoped>
.background {
  position: fixed;
  bottom: 0;
  left: 50%;
  transform: translate(-50%);
  width: 130%;
  /*filter: var(--clear);*/
  transition: .4s;
}

.card {
  border-radius: 10px;
  text-shadow: 1px 1px 2px black;
  box-shadow: -2px -2px 5px #eee, 2px 2px 5px var(--shadow);
  color: var(--theme);
  background: rgba(255, 255, 255, .3);
  -webkit-backdrop-filter: blur(8px);
  backdrop-filter: blur(8px);
  transition: 0.4s;
}

.antiCard {
  border-radius: 10px;
  box-shadow: -2px -2px 5px #eee inset, 2px 2px 5px var(--shadow) inset;
  color: var(--theme);
  -webkit-backdrop-filter: blur(8px);
  backdrop-filter: blur(8px);
  transition: 0.4s;
}

.title {
  transition: .4s;
  font-weight: bold;
  position: relative;
  width: 600px;
  left: 50%;
  transform: translate(-50%);
  top: -100px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2.4em;
  z-index: 2;
}

.login {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*height: 40px;*/
  height: 80px;
  border-radius: 50%;
  width: 80px;
  /*width: 25%;*/
  margin: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;
  transition: .4s;
}

.loginBox {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 100%;
  padding: 20px;
  overflow: hidden;
}

.loginInput {
  width: 100%;
  height: 30px;
  transition: .4s;
}

.tips {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
  width: 560px;
  padding: 0 20px;
  height: 0;
  font-weight: bold;
  color: #ff2e63;
  transition: height .4s, transform .1s;
  z-index: 1;
}

input {
  background: none;
  outline: none;
  border: none;
  font-size: 1em;
}
</style>
