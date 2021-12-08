import jwt_decode from "jwt-decode";
import { login } from "@/api/member.js";
import { findById } from "@/api/member.js";

const member = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isok:null,
    admin: "F",
    // idcheck:false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    ISUPDATE: (state, payload) => {
      state.isok = payload;
    },
    SET_USER_ADIN: (state,payload) => {
      state.admin = payload;
    },
    // SET_ID_CHECK: (state, payload) => {
    //   state.idcheck = payload;
    // }
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"]; //success면 이 access-token이 있을것이다. (data의 속성이름)
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },

    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token); //받은 token을 decode해주고
      console.log(decode_token);
      findById(
        decode_token.user_id, //decode니까 정상적인 글자가 나오는데, 거기에서 userid을 뺀다.
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
            console.log("로그인시 userInfo", response.data.userInfo);
            if(response.data.userInfo.user_admin=="T"){
              commit("SET_USER_ADIN","T");
              console.log("admin 로그인");
            }else{
              commit("SET_USER_ADIN","F");
              console.log("일반회원 로그인");
            }
            
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default member;
