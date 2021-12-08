import Vue from "vue";
import Vuex from "vuex";
// import http from "@/util/http-common.js";
// import axios from "axios";
Vue.use(Vuex);

const myapt = {
  namespaced: true,
  state: {
    age: null,
    month: null,
    balance: null,
    gugunCode: null,
    deal_id: null,
    isOk: null,
    isupdate:"false",
  },
  mutations: {
    SET_SURVEY(state, params) {
      console.log("mutations", params);
      state.age = params[0];
      state.month = params[1];
      state.balance = params[2];
      state.gugunCode = params[3];
    },
    SET_DEALID(state, params) {
      state.deal_id = params;
    },
    SET_IS_OK(state, params) {
      state.isOk = params;
    },
    SET_IS_UPDATE(state, params) {
      state.isupdate = params;
    }
  },
  actions: {},
  modules: {},
};
export default myapt;
