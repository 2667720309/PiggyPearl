// axios请求封装
import axios from 'axios'

// 初始化基本请求地址
const service = axios.create({
  baseURL: '/api',
  // baseURL: 'http://localhost:3000'
  // baseURL: 'http://101.43.250.201:3000'
})

// 请求拦截器
service.interceptors.request.use(config => {
  config.headers['Access-Control-Allow-Origin'] = '*'
  return config
}, error => {
  // 对请求错误做些什么
  return Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(function (response) {
  response.headers['Access-Control-Allow-Origin'] = '*'
  return response.data
})

export default service
