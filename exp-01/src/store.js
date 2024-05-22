// src/store.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      username: 'default',
      email: 'default',
      balance: 0,
      birthday: '2000-01-01'
    }
  },
  mutations: {
    setUsername(state, username) {
      state.user.username = username;
    },
    setEmail(state, email) {
      state.user.email = email;
    },
    setBirthday(state, birthday) {
      state.user.birthday = birthday;
    },
    setBalance(state, balance) {
      state.user.balance = balance;
    },
    updateUser(state, user) {
      state.user = { ...state.user, ...user };
    }
  },
  actions: {
    updateUsername({ commit }, username) {
      commit('setUsername', username);
    },
    updateEmail({ commit }, email) {
      commit('setEmail', email);
    },
    updateBirthday({ commit }, birthday) {
      commit('setBirthday', birthday);
    },
    updateBalance({ commit }, balance) {
      commit('setBalance', balance);
    },
    updateUser({ commit }, user) {
      commit('updateUser', user);
    }
  },
  getters: {
    user: state => state.user,
    username: state => state.user.username,
    email: state => state.user.email,
    birthday: state => state.user.birthday,
    balance: state => state.user.balance
  }
});
