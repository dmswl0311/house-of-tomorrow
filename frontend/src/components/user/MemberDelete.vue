<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>내 정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";
const member = "member";
export default {
  name: "MemberDelete",
  created() {
    http.delete(`/user/${this.userInfo.user_id}`).then(({ data }) => {
      http.defaults.headers["access-token"] =
        sessionStorage.getItem("access-token");
      if (data === "success") {
        alert("삭제 성공");
        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");
        // if (this.$route.path != "/") this.$router.push({ name: "Home" });
        this.$router.push({ name: "Home" });
      } else {
        alert("삭제 실패");
      }
    });
  },
  computed: {
    ...mapState(member, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(member, ["SET_IS_LOGIN", "SET_USER_INFO"]),
  },
};
</script>

<style></style>
