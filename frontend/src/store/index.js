import Vue from 'vue'
import Vuex from 'vuex'
import country from './country'
import apiStore from './apiStore'
Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
      country,
      apiStore
    },
    strict: true
  })