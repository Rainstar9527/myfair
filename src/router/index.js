import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/views/login'
import center from '@/views/center'
import img from '../views/img.vue'
import buyer from '../views/buyer.vue'
import member from '../views/member.vue'
import factory from '@/views/factory.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/center',
      name: 'center',
      component: center,
      children:[
        {
          path: '/center/factory',
          name: 'factory',
          component: factory
        },
        {
          path: '/center/buyer',
          name: 'buyer',
          component: buyer
        },
        {
          path: '/center/member',
          name: 'member',
          component: member
        }
      ]
    },
    {
      path: '/img',
      name: 'img',
      component: img
    },
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    
  ]
})
