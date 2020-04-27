import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import UserInfo from '@/components/UserInfo'
import HomePage from '@/components/HomePage.vue'
import NewMGuess from "../components/NewMGuess";
import NewMEvid from "../components/NewMEvid";

Vue.use(Router)

export default new Router({
  routes: [{
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
    // {
    //   path: '/new_notice',
    //   name: 'NewNotice',
    //   component: NewNotice
    // },
    {
      path: '/new_mguess',
      name: 'NewMGuess',
      component: NewMGuess
    },
    {
      path: '/new_mevid',
      name: 'NewMEvid',
      component: NewMEvid
    },
    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage
    },
  ]
})
