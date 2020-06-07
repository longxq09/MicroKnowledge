import Vue from 'vue'
import Router from 'vue-router'
import Visitor from '../components/Visitor'
import User from '../components/User'
import UserInfo from '../components/UserInfo'
import NewMEvid from '../components/NewMEvid.vue'
import NewMGuess from '../components/NewMGuess.vue'
import HomePage from '../components/HomePage.vue'
import ModifyMEvid from '../components/ModifyMEvid.vue'
import ModifyMGuess from '../components/ModifyMGuess'
import Search from '../components/Search'
import VisitorSearch from "../components/VisitorSearch";
import Review from '../components/Review.vue'
import Feedback from '../components/Feedback.vue'
import AdminHomePage from '../components/AdminHomePage'
import TopicManage from '../components/TopicManage'
import AdminMessage from '../components/AdminMessage.vue'
import VueRouter from 'vue-router'

const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Visitor',
      component: Visitor,
      meta: {
        visitor: true
      }
    },
    {
      path: '/user',
      name: 'User',
      component: User,
      meta: {
        user: true
      }
    },
    {
      path: '/userinfo',
      name: 'UserInfo',
      component: UserInfo,
      meta: {
        user: true
      }
    },
    {
      path: '/new_mevid',
      name: 'NewMEvid',
      component: NewMEvid,
      meta: {
        user: true
      }
    },
    {
      path: '/new_mguess',
      name: 'NewMGuess',
      component: NewMGuess,
      meta: {
        user: true
      }
    },

    {
      path: '/homepage',
      name: 'HomePage',
      component: HomePage,
      meta: {
        user: true
      }
    },
    {
      path: '/modify_mevid',
      name: 'ModifyMEvid',
      component: ModifyMEvid,
      meta: {
        user: true
      }
    },
    {
      path: '/modify_mguess',
      name: 'ModifyMGuess',
      component: ModifyMGuess,
      meta: {
        user: true
      }
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      meta: {
        user: true
      }
    },
    {
      path: '/visitor_search',
      name: 'VisitorSearch',
      component: VisitorSearch,
      meta: {
        visitor: true
      }
    },
    {
      path: '/review',
      name: 'Review',
      component: Review,
      meta: {
        user: true
      }
    },
    {
      path: '/feedback',
      name: 'Feedback',
      component: Feedback,
      meta: {
        user: true
      }
    },
    {
      path: '/admin',
      name: 'Admin',
      component: AdminHomePage,
      meta: {
        admin: true
      }
    },
    {
      path: '/admin/topic',
      name: 'TopicManage',
      component: TopicManage,
      meta: {
        admin: true
      }
    },
    {
      path: '/admin/message',
      name: 'AdminMessage',
      component: AdminMessage,
      meta: {
        admin: true
      }
    }
  ]
})
