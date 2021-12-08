<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="light">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="90px"
            alt="Kitten"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'WhereIsMyApt' }" class="link"
              ><b-icon icon="house-fill" font-scale="1"></b-icon>
              내집마련</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Recommend' }" class="link"
              ><b-icon icon="house" font-scale="1"></b-icon> 집추천</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Notice' }" class="link"
              ><b-icon icon="journal" font-scale="1"></b-icon>
              공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'Deal' }" class="link">
              매매게시판</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item class="align-self-center">
              <b-avatar
                variant="primary"
                v-text="
                  userInfo ? userInfo.user_id.charAt(0).toUpperCase() : ''
                "
              ></b-avatar>
              <router-link
                :to="{ name: 'MemberMyPage' }"
                class="link align-self-center"
              >
                {{ userInfo.user_name }}({{ userInfo.user_id }})님 환영합니다.
              </router-link></b-nav-item
            >

            <b-nav-item class="align-self-center"
              ><router-link
                :to="{ name: 'BookMark' }"
                class="link align-self-center"
                ><b-icon icon="house-fill" font-scale="1"></b-icon>
                찜목록</router-link
              ></b-nav-item
            >

            <!-- <b-nav-item class="align-self-center"
              ><router-link
                :to="{ name: 'MemberMyPage' }"
                class="link align-self-center"
                >내정보보기</router-link
              ></b-nav-item
            > -->

            <b-nav-item
              class="link align-self-center"
              @click.prevent="onClickLogout"
              >로그아웃</b-nav-item
            >
          </b-navbar-nav>

          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item href="#"
              ><router-link :to="{ name: 'MemberJoin' }" class="link"
                ><b-icon icon="person-circle" font-scale="1"></b-icon>
                회원가입</router-link
              ></b-nav-item
            >

            <b-nav-item href="#"
              ><router-link :to="{ name: 'MemberLogin' }" class="link"
                ><b-icon icon="key" font-scale="1"></b-icon> 로그인</router-link
              ></b-nav-item
            >
          </b-navbar-nav>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const member = "member";

export default {
  name: "NaviBar",
  computed: {
    ...mapState(member, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(member, ["SET_IS_LOGIN", "SET_USER_INFO", "SET_USER_ADIN"]),
    onClickLogout() {
      let flag=confirm("로그아웃 하시겠습니까?");
      if(flag){
      this.SET_USER_ADIN("F");
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      alert("로그아웃 되었습니다!");
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    }else{
      return;
    }
    },
  },
};
</script>

<style>
nav.navbar.navbar-light.bg-light.navbar-expand-lg {
  box-shadow: -7px -14px 20px 19px #80808048;
}
</style>
