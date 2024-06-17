import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/views/login'
import center from '@/views/center'
import img from '../views/img.vue'
import buyer from '../views/buyer.vue'
import member from '../views/member.vue'
import factory from '@/views/factory.vue'
import account from '../views/account.vue'
import location from '../views/location.vue'

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
        },
        {
          path: '/center/account',
          name: 'account',
          component: account
        },
        {
          path: '/center/location',
          name: 'location',
          component: location
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
