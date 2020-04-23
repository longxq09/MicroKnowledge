import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui'
import App from './App'

Vue.use(VueRouter);
Vue.use(VueAxios,axios);
Vue.use(ElementUI, {
    size: 'small'
});
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
	render: h => h(App)
})