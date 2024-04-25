import Vue from 'vue'
import VueRouter from 'vue-router'
import QQLogin from '@/views/QQLogin.vue'
import WechatLogin from '@/views/WechatLogin.vue'
import Login from '../views/login.vue'
import AliLogin from '@/views/AliLogin.vue'
import Register from'../views/Register.vue'
import Home from '@/views/home.vue'
import Forget from '@/views/forget.vue'
import ChangePwd from '@/views/changePwd.vue'
import UserManage from '@/views/userManage.vue'
import DocManage from '@/views/docManage.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/qqlogin',
    name: 'qqLogin',
    component:QQLogin
  },
  {
    path: '/wechatlogin',
    name: 'WechatLogin',
    component:WechatLogin
  },
  {
    path: '/alilogin',
    name: 'aliLogin',
    component:AliLogin
  },
  {
    path: '/register',
    name: 'Register',
    component:Register
  },
  {
    path: '/home',
    name: 'Home',
    component:Home,
    meta: { requiresAuth: true } // 添加一个meta字段来标记需要认证的路由  
  },
  {
    path: '/forget',
    name: 'Forget',
    component:Forget
  },
  {
    path: '/changepwd',
    name: 'changepwd',
    component:ChangePwd
  },
  {
    path: '/usermanage',
    name: 'userManage',
    component:UserManage,
    meta: { requiresAuth: true } // 添加一个meta字段来标记需要认证的路由  
  },
  {
    path: '/docmanage',
    name: 'docManage',
    component:DocManage,
    meta: { requiresAuth: true } // 添加一个meta字段来标记需要认证的路由  
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// // 全局前置守卫  
// router.beforeEach((to, from, next) => {  
//   const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';  // 检查用户是否已登录的逻辑，例如从Vuex或全局变量中获取状态  
//   const requiresAuth = to.matched.some(record => record.meta.requiresAuth)  
  
//   if (requiresAuth && !isLoggedIn) {  
//     // 如果需要认证且用户未登录，则重定向到登录页面  
//     next({ name: 'login' })  
//   } else {  
//     // 否则，继续导航到目标路由  
//     next()  
//   }  
// })

export default router
