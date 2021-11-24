import Vue from 'vue'
import Vuex from 'vuex'
import {login} from "@/api";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: {}
    },
    getters: {
        userInfo: state => state.userInfo,
    },
    mutations: {
        set_user_info : (state, info) => {
            state.userInfo = info
        }
    },
    actions: {
        async login({ commit ,state }, {username = '', password = ''}) {
            login({username, password}).then(res => {
                commit('set_user_info', {username, password})
            })
        }
    },
})
