import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/views/login'
import center from '@/views/center'
import test from '@/views/test'

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
          path: '/factory',
          name: 'factory',
          component: factory
        }
      ]
    },

    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/test',
      name: 'test',
      component: test
    }
  ]
})
