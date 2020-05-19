import Vue from 'vue'
import Router from 'vue-router'
import Visitor from '@/components/Visitor'
import User from '@/components/User'
import UserInfo from '@/components/UserInfo'
import NewMEvid from '@/components/NewMEvid.vue'
import NewMGuess from '@/components/NewMGuess.vue'
import HomePage from '@/components/HomePage.vue'
import ModifyMEvid from '@/components/ModifyMEvid.vue'
import ModifyMGuess from '@/components/ModifyMGuess'
import Search from '@/components/Search'
import Review from '@/components/Review.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Visitor',
      component: Visitor,
      meta: {
        keepAlive: false
      }
    },
    {
      path: '/user',
      name: 'User',
      component: User,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/userinfo',
      name: 'UserInfo',
      component: UserInfo,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/new_mevid',
      name: 'NewMEvid',
      component: NewMEvid,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/new_mguess',
      name: 'NewMGuess',
      component: NewMGuess,
      meta: {
        keepAlive: true
      }
    },

    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/modify_mevid',
      name: 'ModifyMEvid',
      component: ModifyMEvid
    },
    {
      path: '/modify_mguess',
      name: 'ModifyMGuess',
      component: ModifyMGuess
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/review',
      name: 'Review',
      component: Review,
      meta: {
        keepAlive: true
      }
    },
  ]
})
