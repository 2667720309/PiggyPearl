<template>
  <div>
    <img src="static/loginBackground.jpg" alt="" class="background">
    <div class="title card">掌上明猪</div>
    <div class="login card" @click="open" @keydown.enter="keyDownJudge">
      <div v-if="!isOpen"><img src="static/logo.png" alt="" style="width: 80%; transition: .4s;"></div>
      <div v-else class="loginBox">
        <div class="loginInput antiCard">
          <input type="text" class="username"
                 style="position: relative; width: 100%; height: 100%; padding: 10px; color: var(--black);"
                 placeholder="请输入账号" v-model="loginForm.accountNumber">
        </div>
        <div class="loginInput antiCard">
          <input type="password" class="password"
                 style="position: relative; width: 100%; height: 100%; padding: 10px; color: var(--black);"
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
         style="position: relative; width: 100%; top: 22px; overflow: hidden; border-top-left-radius: 0; border-top-right-radius: 0;">
      <div class="tips">
        <p>&nbsp;</p>
        <p>{{ tips }}</p>
        <div class="loginInput antiCard" style="margin-top: 10px;">
          <input type="password" class="password"
                 style="position: relative; width: 100%; height: 100%; padding: 10px; color: var(--black);"
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
    const tokenStr = localStorage.getItem('token');
    if (tokenStr === 'ABDUENR@5435lNGE(*)&(*&^') {
      const user = JSON.parse(localStorage.getItem('user'))
      this.loginForm.accountNumber = user.account
      this.loginForm.password = user.password
      this.goToHome()
    }
  },
  methods: {
    open() {
      let background = document.getElementsByClassName('background')
      let title = document.getElementsByClassName('title')
      let login = document.getElementsByClassName('login')

      background[0].style.bottom = '-120px'
      title[0].style.top = '0'
      login[0].style.width = '100%'
      login[0].style.borderRadius = '10px'
      login[0].style.height = '200px'

      this.isOpen = true
    },
    openTips() {
      let tips = this.$el.querySelector('.tips')

      tips.style.height = '130px'
    },
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
    loginClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.width = '40px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.width = '60px'
      }, 200)
    },
    goToHome() {
      let tips = this.$el.querySelector('.tips')

      if (this.loginForm.accountNumber === '') {
        this.tips = '请输入账号'
        tips.style.height = '45px'
        this.fontVibration()
        return
      }
      if (this.loginForm.password === '') {
        this.tips = '请输入密码'
        tips.style.height = '45px'
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
      this.$http({
        url: 'user/login',
        method: 'POST',
        params: {
          account: this.loginForm.accountNumber,
          password: this.loginForm.password
        }
      }).then(res => {
        this.tips = res.state
        tips.style.height = '45px'

        if (this.tips === '用户不存在') {
          this.tips += '，是否注册？'
          tips.style.height = '130px'
          this.fontVibration()
        }

        if (this.tips === '登录成功') {
          this.fontVibration()
          localStorage.setItem('token', 'ABDUENR@5435lNGE(*)&(*&^')
          localStorage.setItem('user', JSON.stringify(res.user)) // JSON.parse(localStorage.getItem('user'))
          this.$router.push({
            path: '/app',
            query: {
              page: this.$route.name
            }
          })
        }
      })
    },
    registerClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.width = '80px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.width = '100px'
      }, 200)
    },
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
      this.$http({
        url: 'user/register',
        method: 'POST',
        params: {
          account: this.loginForm.accountNumber,
          password: this.loginForm.password
        }
      }).then(res => {
        this.tips = res.state
        if (this.tips === '注册成功') {
          this.fontVibration()
          this.goToHome()
        }
      })
    },
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
  filter: var(--clear);
  transition: .4s;
}

.title {
  transition: .4s;
  font-weight: bold;
  position: relative;
  top: -100px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 2.4em;
}

.login {
  position: relative;
  top: 30px;
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
  width: 100%;
  padding: 0 20px;
  height: 0;
  font-weight: bold;
  color: #ff2e63;
  transition: height .4s, transform .1s;
  z-index: 1;
}
</style>
