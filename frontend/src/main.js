import Vue from 'vue'
import App from './App.vue'
import './index.css'
import 'flag-icon-css/css/flag-icon.css'
import Vuex from 'vuex'
import store from './store'


Vue.config.productionTip = false

Vue.use(Vuex)

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
