import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import User from '@/components/User'
import UserInfo from '@/components/UserInfo'
import New_notice from '@/components/New_notice.vue'
import HomePage from '@/components/HomePage.vue'

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
		{
      path: '/new_notice',
      name: 'New_notice',
      component: New_notice
    },

    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage
    },
  ]
})
