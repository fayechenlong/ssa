import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import Router from 'vue-router'
import store from './store'

import '@/assets/index.less'
Vue.use(ElementUI)
Vue.use(Router)

import App from './App.vue'

Vue.config.productionTip = false

import Layout from "@/components/Layout";
import Login from "@/components/Login";
import Index from "@/views/Index";

const router = new Router({
  routes: [
    { path: '/', component: Layout, children: [
      ]
    },{
      path: '/login', component: Login
      },{
      path: '/index', component: Index
      },
      {
          path: '*',
          name: 'err404',
          component: () => import('@/components/Err404')
      }
  ]
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
