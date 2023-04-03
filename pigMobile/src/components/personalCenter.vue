<template>
  <div>
    <div style="display: flex; margin-top: 20px; position: relative; width: 90%;">
      <div class="return" @click="closeCenter">返回</div>
    </div>

    <div id="personalCenter"
         style="position: relative; transform: translate(25%); width: 200%; display: flex; transition: .8s cubic-bezier(.32,-0.03,0,1.09); overflow: auto;">
      <div class="otherCenter">
        <div class="card" @click="enterPersonalInformation"
             style="display: flex; width: 100%; padding: 10px 20px; align-items: center; justify-content: space-between; cursor: pointer;">
          <div style="display: flex;">
            <div style="height: 80px; min-width: 80px; max-width: 80px; border-radius: 50%; overflow: hidden;">
              <img :src="headUrl" alt="头像" style="width: 100%;">
            </div>
            <div
              style="margin-left: 20px; display: flex; flex-direction: column; justify-content: center; align-items: flex-start;">
              <div class="header">{{ personalInformationData[0].value }}</div>
              <div>用户账户管理、个人信息</div>
            </div>
          </div>
          <img src="static/向右箭头.png" alt=">" style="height: 18px;">
        </div>

        <div class="card" style="width: 100%; margin-top: 30px; overflow: hidden;">
          <div
            style="display: flex; justify-content: space-between; position: relative; margin: 10px 10px 0 10px; align-items: center;">
            <h4>打开深色模式</h4>
            <toggle @click.native="dayNightToggle" :isClose="!isDark"></toggle>
            <div style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; bottom: -7px;"></div>
          </div>
          <div class="isAutoDark"
               style="height: 0; overflow: hidden; display: flex; justify-content: space-between; position: relative; margin: 0 10px; align-items: center; transition: height .4s, margin .1s;">
            <h4>在日落后自动切换深色模式</h4>
            <toggle @click.native="autoDayNight" :isClose="!isAutoDark"></toggle>
          </div>
          <div
            style="display: flex; justify-content: space-between; position: relative; margin: 10px; align-items: center;">
            <div style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; top: -5px;"></div>
            <h4>test</h4>
            <toggle :isClose="true"></toggle>
          </div>
        </div>
        <div class="assembly" style="align-items: center;">
          <div @click="loginOut" class="card"
               style="height: 30px; display: flex; align-items: center; justify-content: center; padding: 10px; font-weight: bold; cursor: pointer;">
            退出登录
          </div>
        </div>
      </div>
      <div class="otherCenter">
        <div class="card"
             style="height: 200px; width: 200px; border-radius: 50%; overflow: hidden; position: relative; left: 50%; transform: translate(-50%);">
          <img :src="headUrl" alt="头像" style="position: absolute; left: 0; top: 0; width: 100%;">
          <div
            style="position: absolute; left: 0; width: 100%; bottom: 0; padding: 4px; background: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, .7));">
            <div style="color: var(--black); cursor: pointer;">
              更换
            </div>
          </div>
        </div>
        <div class="assembly">
          <div class="header" style="position: relative; left: 50%; transform: translate(-50%);">
            个人信息
          </div>
          <div class="card" style="width: 100%; margin-top: 10px; overflow: hidden;">
            <div v-for="(i, n) in personalInformationData" class="modifyWindow"
                 style="display: flex; justify-content: space-between; position: relative; margin: 10px; transition: transform .1s;">
              <div v-if="n"
                   style="position: absolute; border: rgb(128, 128, 128) 1px solid; width: 110%; top: -5px;"></div>
              <div style="display: flex; flex-wrap: wrap;">
                <h4>{{ i.key }}&nbsp;&nbsp;</h4>
                <h4
                  :style="'text-align: start; cursor: default; color: #ff2e63; transition: .4s; opacity: ' + (i.regExp.test(i.value) ? 0 : 1)">
                  {{ i.tips }}</h4>
              </div>
              <div class="antiCard" v-if="i.key !== '账号'">
                <input type="text" v-model="i.value"
                       style="color: var(--black); width: 100%; height: 100%; text-align: end; padding: 0 10px;"/>
              </div>
              <div v-else style="padding: 0 10px;">{{ i.value }}</div>
            </div>
          </div>
        </div>
        <div class="assembly">
          <div class="card" style="width: 100%; margin-top: 10px; overflow: hidden; z-index: 2;">
            <div @click="openPassword()" class="openPassword"
                 style="display: flex; justify-content: space-between; align-items: center;  position: relative; margin: 10px; cursor: pointer;">
              <div style="display: flex; pointer-events: none; flex-wrap: wrap;" disabled="ture">
                <h4>修改密码&nbsp;&nbsp;</h4>
                <h4
                  v-if="passwordForm[0].value !== '' && (passwordForm[1].value === '' || !passwordForm[0].regExp.test(passwordForm[0].value))"
                  :style="'text-align: start; color: #ff2e63; opacity: ' + (passwordForm[0].regExp.test(passwordForm[0].value) ? 0 : 1)">
                  {{ passwordForm[0].tips }}
                </h4>
                <div
                  v-else-if="passwordForm[1].value !== '' && (passwordForm[2].value === '' || !passwordForm[1].regExp.test(passwordForm[1].value))">
                  <h4
                    v-if="passwordForm[0].value !== passwordForm[1].value || !passwordForm[1].regExp.test(passwordForm[1].value)"
                    :style="'text-align: start; color: #ff2e63; opacity: ' + (passwordForm[1].regExp.test(passwordForm[1].value) ? 0 : 1)">
                    {{ passwordForm[1].tips }}
                  </h4>
                  <h4 v-else
                      :style="'text-align: start; color: #ff2e63; opacity: ' + (passwordForm[0].value !== passwordForm[1].value ? 0 : 1)">
                    新旧密码不可一致
                  </h4>
                </div>
                <h4 v-else-if="(passwordForm[2].value !== '') || !passwordForm[1].value === passwordForm[2].value"
                    :style="'text-align: start; color: #ff2e63; opacity: ' + (passwordForm[1].value === passwordForm[2].value ? 0 : 1)">
                  {{ passwordForm[2].tips }}
                </h4>
                <h4 style="color: chartreuse;">{{ passwordTips }}</h4>
              </div>
              <img src="static/向右箭头.png" alt=">"
                   style="height: 18px; transition: .4s; transform: rotate(0); pointer-events: none; z-index: 2;"
                   disabled="true">
            </div>
          </div>
          <div class="card"
               style="position: relative; width: 100%; top: -10px; overflow: hidden; border-top-left-radius: 0; border-top-right-radius: 0;">
            <div class="changePassword">
              <p>&nbsp;</p>
              <div v-for="(i, n) in passwordForm"
                   style="display: flex; margin-top: 10px; width: 100%; height: 30px; transition: .4s;">
                <div class="antiCard" style="position: relative; width: 100%; height: 100%;">
                  <input type="password" class="password"
                         style="position: relative; width: 100%; height: 100%; padding: 10px; color: var(--black);"
                         :placeholder="i.placeholder" v-model="i.value">
                </div>
                <div v-if="n === passwordForm.length - 1"
                     :style="'min-width: ' + (i.value !== '' && passwordForm[1].value === passwordForm[2].value && passwordForm[0].value !== passwordForm[1].value ? '90px' : 0) +
                     '; display: flex; justify-content: flex-end; align-items: center; transition: .4s;'">
                  <div class="card" @click="changePassword(); changePasswordClick($event)"
                       :style="'min-width: ' + (i.value !== '' && passwordForm[1].value === passwordForm[2].value && passwordForm[0].value !== passwordForm[1].value ? '80px' : 0) +
                       '; max-width: ' + (i.value !== '' && passwordForm[1].value === passwordForm[2].value && passwordForm[0].value !== passwordForm[1].value ? '80px' : 0) +
                       '; position: relative; display: flex; align-items: center; overflow: hidden; height: 100%; justify-content: center; cursor: pointer;'">
                    确认修改
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="assembly" style="align-items: center;">
          <div @click="preservation(); preservationClick($event)" class="card"
               style="height: 30px; width: 100px; display: flex; align-items: center; justify-content: center; font-weight: bold; cursor: pointer;">
            {{ tips }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import toggle from "./toggle";
import {dayNightSwitch} from "./darkMode/darkMode";

export default {
  name: "personalCenter",
  data() {
    return {
      headUrl: 'static/head.png',
      tips: '保存信息',
      isDark: false,
      isAutoDark: false,
      isModify: false,
      isOpenPassword: false,
      personalInformationData: [
        {key: '用户名', tips: '用户名只能在2-18位之间', value: '', regExp: /^[\u4e00-\u9fa5A-Za-z\d]{2,18}$/},
        {key: '账号', tips: '', value: '', regExp: /^[\dA-Za-z]{6,18}$/},
        {
          key: '手机号',
          tips: '手机号不符合规范',
          value: '',
          regExp: /^1(3\d|4[5-8]|5[0-35-9]|6[567]|7[01345-8]|8\d|9[025-9])\d{8}$/
        },
        {key: '邮箱', tips: '邮箱不符合规范', value: '', regExp: /^([A-Za-z\d_\-.])+@([A-Za-z\d_\-.])+\.([A-Za-z]{2,4})$/}
      ],
      passwordForm: [
        {
          placeholder: '请输入旧密码',
          tips: '旧密码错误',
          value: '',
          regExp: new RegExp('^' + JSON.parse(localStorage.getItem('user')).password + '$')
        },
        {placeholder: '请输入新密码', tips: '密码仅由数字和字母组成,且在6-18位之间', value: '', regExp: /^[\dA-Za-z]{6,18}$/},
        {placeholder: '请再次输入新密码', tips: '两次输入的新密码不一致', value: '', regExp: /^[\dA-Za-z]{6,18}$/}
      ],
      passwordTips: ''
    }
  },
  created() {
    if (localStorage.getItem('isDark') === 'true') {
      this.isDark = true
    }
    if (localStorage.getItem('isAutoDark') === 'true') {
      this.isAutoDark = true
    }
  },
  mounted() {
    let isAutoDark = document.querySelector('.isAutoDark')

    if (this.isDark) {
      isAutoDark.style.margin = '10px'
      isAutoDark.style.height = '26px'
    }

    let user = JSON.parse(localStorage.getItem('user'))
    this.personalInformationData[0].value = user.username
    this.personalInformationData[1].value = user.account
    this.personalInformationData[2].value = user.mobile
    this.personalInformationData[3].value = user.email
  },
  methods: {
    openPassword() {
      let openPassword = this.$el.querySelector('.openPassword')
      let changePassword = this.$el.querySelector('.changePassword')

      if (this.isOpenPassword) {
        openPassword.children[1].style.transform = 'rotate(0)'
        changePassword.style.height = '0'
      } else {
        openPassword.children[1].style.transform = 'rotate(90deg)'
        changePassword.style.height = '148px'
      }
      this.isOpenPassword = !this.isOpenPassword
    },
    loginOut() {
      let app = document.querySelector('#app')

      app.style.width = '90%'
      app.style.margin = '20px auto 0'

      localStorage.setItem('token', '')
      localStorage.setItem('user', '')
      this.$router.push('/login')
    },
    closeCenter() {
      if (this.isModify) return

      let personalCenter = document.querySelector('.personalCenter')
      let app = document.querySelector('#app')

      personalCenter.style.top = '110%'
      app.style.width = '90%'
      app.style.margin = '20px auto 0'

      this.$el.querySelector('#personalCenter').style.transform = 'translate(25%)'
    },
    enterPersonalInformation() {
      let ret = this.$el.querySelector('.return')

      ret.style.opacity = '0'
      ret.style.cursor = 'default'

      this.isModify = true

      this.$el.querySelector('#personalCenter').style.transform = 'translate(-25%)'
    },
    changePasswordClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.minWidth = '70px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.minWidth = '80px'
      }, 200)
    },
    changePasswordShake() {
      let changePassword = this.$el.querySelector('.changePassword')
      const range = 3
      changePassword.style.transform = 'translate(' + range + 'px)'
      setTimeout(() => {
        changePassword.style.transform = 'translate(-' + range + 'px)'
      }, 100)
      setTimeout(() => {
        changePassword.style.transform = 'translate(' + range + 'px)'
      }, 200)
      setTimeout(() => {
        changePassword.style.transform = 'translate(0)'
      }, 300)
    },
    changePassword() {
      if (this.passwordForm[0].value === this.passwordForm[1].value ||
        this.passwordForm[1].value !== this.passwordForm[2].value) {
        this.changePasswordShake()
        return
      }
      let error = 0
      this.passwordForm.forEach(function (item) {
        if (!item.regExp.test(item.value)) {
          error = 1
        }
      })
      if (error) {
        this.changePasswordShake()
        return
      }
      this.$http({
        url: 'user/changePassword',
        method: 'POST',
        params: {
          account: this.personalInformationData[1].value,
          password: this.passwordForm[1].value,
          oldPassword: this.passwordForm[0].value
        }
      }).then(res => {
        this.passwordTips = res.state
        if (this.passwordTips === '修改成功') {
          let user = JSON.parse(localStorage.getItem('user'))
          user.password = this.passwordForm[1].value
          localStorage.setItem('user', JSON.stringify(user))
          this.passwordForm[0].regExp = new RegExp('^' + JSON.parse(localStorage.getItem('user')).password + '$')
          this.passwordForm[0].value = ''
          this.passwordForm[1].value = ''
          this.passwordForm[2].value = ''
          this.openPassword()
        }
        setTimeout(() => {
          this.passwordTips = ''
        }, 1000)
      })
    },
    preservationClick(e) {
      let self = e.target
      self.style.height = '20px'
      self.style.width = '80px'
      setTimeout(() => {
        self.style.height = '30px'
        self.style.width = '100px'
      }, 200)
    },
    preservation() {
      let error = 0
      this.personalInformationData.forEach(function (data) {
        if (!data.regExp.test(data.value)) error = 1
      })
      if (error) {
        let modifyWindow = this.$el.getElementsByClassName('modifyWindow')

        for (let i = 0; i < modifyWindow.length; i++) {
          const range = 3
          modifyWindow[i].style.transform = 'translate(' + range + 'px)'
          setTimeout(() => {
            modifyWindow[i].style.transform = 'translate(-' + range + 'px)'
          }, 100)
          setTimeout(() => {
            modifyWindow[i].style.transform = 'translate(' + range + 'px)'
          }, 200)
          setTimeout(() => {
            modifyWindow[i].style.transform = 'translate(0)'
          }, 300)
        }

        return
      }

      let user = JSON.parse(localStorage.getItem('user'))
      if (user.username === this.personalInformationData[0].value &&
        user.mobile === this.personalInformationData[2].value &&
        user.email === this.personalInformationData[3].value) {
        let ret = this.$el.querySelector('.return')

        ret.style.opacity = '1'
        ret.style.cursor = 'pointer'

        this.isModify = false

        this.$el.querySelector('#personalCenter').style.transform = 'translate(25%)'
      }

      this.$http({
        url: 'user/update',
        method: 'POST',
        params: {
          account: this.personalInformationData[1].value,
          username: this.personalInformationData[0].value,
          mobile: this.personalInformationData[2].value,
          email: this.personalInformationData[3].value
        }
      }).then(res => {
        this.tips = res.state
        if (this.tips === '修改成功') {
          let user = JSON.parse(localStorage.getItem('user'))
          user.username = this.personalInformationData[0].value
          user.mobile = this.personalInformationData[2].value
          user.email = this.personalInformationData[3].value
          localStorage.setItem('user', JSON.stringify(user))
          setTimeout(() => {
            let ret = this.$el.querySelector('.return')

            ret.style.opacity = '1'
            ret.style.cursor = 'pointer'

            this.isModify = false

            this.$el.querySelector('#personalCenter').style.transform = 'translate(25%)'
          }, 1000)
        }
        setTimeout(() => {
          this.tips = '保存信息'
        }, 1200)
      })
    },
    dayNightToggle() {
      let isAutoDark = document.querySelector('.isAutoDark')

      dayNightSwitch()
      this.isDark = !this.isDark
      this.isDark ? localStorage.setItem('isDark', 'true') : localStorage.setItem('isDark', 'false')

      if (this.isDark) {
        isAutoDark.style.margin = '10px'
        isAutoDark.style.height = '26px'
      } else {
        isAutoDark.style.height = '0'
        isAutoDark.style.margin = '0 10px'
      }

      let myDate = new Date();
      let h = myDate.getHours();

      if (this.isAutoDark && (h > 6 && h < 18)) {
        dayNightSwitch()
      }
    },
    autoDayNight() {
      let myDate = new Date();
      let h = myDate.getHours();

      this.isAutoDark = !this.isAutoDark
      this.isAutoDark ? localStorage.setItem('isAutoDark', 'true') : localStorage.setItem('isAutoDark', 'false')

      if (h > 6 && h < 18) {
        dayNightSwitch()
      }
    }
  },
  components: {
    toggle
  }
}

</script>

<style scoped>
*::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}

* {
  -ms-overflow-style: none; /* IE 10+ */
}

.return {
  color: #2585e3;
  cursor: pointer;
  opacity: 1;
  font-weight: bold;
  font-size: 1.4em;
  transition: .4s;
}

.otherCenter {
  text-align: center;
  position: relative;
  width: 100%;
  padding: 20px 5%;
  overflow: auto;
}

.changePassword {
  position: relative;
  display: flex;
  transition: height .4s, transform .1s;
  transform: translate(0);
  flex-direction: column;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
  width: 100%;
  height: 0;
  padding: 0 10px;
}
</style>
