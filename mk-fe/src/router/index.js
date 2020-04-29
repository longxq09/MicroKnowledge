import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import UserInfo from '@/components/UserInfo'
import NewMEvid from '@/components/NewMEvid.vue'
import NewMGuess from '@/components/NewMGuess.vue'
import HomePage from '@/components/HomePage.vue'
import ModifyMEvid from '@/components/ModifyMEvid.vue'
import ModifyMGuess from '@/components/ModifyMGuess'
import Search from '@/components/Search'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/user',
      name: 'User',
      component: User
    },
    {
      path: '/userinfo',
      name: 'UserInfo',
      component: UserInfo
    },
    {
      path: '/new_mevid',
      name: 'NewMEvid',
      component: NewMEvid
    },
    {
      path: '/new_mguess',
      name: 'NewMGuess',
      component: NewMGuess
    },

    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage
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
      component: Search
    }
  ]
})
