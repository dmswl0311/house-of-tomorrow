import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import CKEditor from 'ckeditor4-vue';
// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
Vue.use(CKEditor)

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  // 껐다 켰을 때 로컬스토리지에 세션이 남아있는지? 
  // async beforeCreate() {
  //   let token = sessionStorage.getItem("access-token");
  //   if (memberStore.state.userInfo == null && token) { //userInfo는 null인데 token 이 있으면 ?
  //     await memberStore.dispatch("getUserInfo", token); //서버에 가서 정상인지 체크 
  //   }
  // },
  render: (h) => h(App),
}).$mount("#app");
