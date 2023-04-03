export {
  pageEnter
}

const names = ['登录', '猪场信息', '生猪信息', '新闻资讯']

// 进入页面滑动
function pageEnter(body, name, oldName) {
  name = names.indexOf(name)
  oldName = names.indexOf(oldName)
  if (name === -1) name = 999
  if (oldName === -1) oldName = 999
  if (oldName <= name) {
    body.style.transform = 'translate(110%)'
  } else {
    body.style.transform = 'translate(-110%)'
  }
  setTimeout(() => {
    body.style.transform = 'translate(0)'
  }, 20)
  setTimeout(() => {
    body.style.transform = ''
  }, 800)
}
