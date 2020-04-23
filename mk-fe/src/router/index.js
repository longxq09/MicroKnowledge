import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import UserInfo from '@/components/UserInfo'

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
		}
  ]
})
