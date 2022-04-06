
const state = {
    apiData: undefined
}


const mutations = {
    setData(state, data) {
        state.apiData = data
    },
}

const actions = {
    setData({ commit }, arg) {
        commit('setData', arg)
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
  }