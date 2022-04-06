
const state = {
    alpha2Code: undefined,
    name: undefined
}

const getters = {
    getAlphaCode(state){
        return state.alpha2Code
    },
    getName(state){
        return state.name
    },
}

const mutations = {
    selectCountry(state, country) {
        state.alpha2Code = country.alpha2Code
        state.name = country.name
    },
    resetCountry(state) {
        state.alpha2Code = undefined
        state.name = undefined
    }
}

const actions = {
    select({ commit }, arg) {
        commit('selectCountry', arg)
    },
    reset({ commit }) {
        commit('resetCountry')
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
  }