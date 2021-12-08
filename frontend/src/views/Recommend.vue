<template>
  <div
    class="container"
    style="font-family: 'Pretendard-Regular'; margin-bottom: 50px"
  >
    <h1
      style="
        font-family: 'NEXON Lv2 Gothic Bold';
        margin-bottom: 20px;
        margin-top: 50px;
      "
    >
      선호하는 기준으로 집을 추천해드려요!
    </h1>
    <h5>
      반경 1km 내에 <b-badge variant="success">{{ user_rec }}</b-badge> 의
      개수가 많은 아파트
    </h5>
    <br />
    <b-container fluid>
      <b-row>
        <b-col sm="2"> </b-col>
        <b-col sm="2">
          <code>살고싶은 지역 <br />(서울만 가능해요!)</code>
        </b-col>
        <b-col sm="4">
          <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
        </b-col>
        <b-button variant="outline-primary" @click="submitBtn"
          >찾아보기 👀</b-button
        >
      </b-row>
    </b-container>
    <br />
    <b-overlay :show="show" rounded="sm">
      <rec-result :list="list"></rec-result>
    </b-overlay>
  </div>
</template>

<script>
const house = "house";
import { mapState, mapActions, mapMutations } from "vuex";
import RecResult from "@/components/rec/RecResult.vue";
import http from "@/util/http-common.js";

export default {
  data() {
    return {
      sidoCode: 11,
      gugunCode: null,
      list: [],
      user_rec: "",
      flag: 0,
      show: false,
    };
  },
  components: {
    RecResult,
  },
  methods: {
    //...mapActions(myapt,["SET"])
    ...mapMutations(house, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    ...mapActions(house, ["getSido", "getGugun", "getAptList"]),

    submitBtn() {
      this.show = true;
      console.log("click");
      if (this.gugunCode == null) {
        alert("구를 선택해주세요!");
        return;
      }
      http({
        methods: "get",
        url: "/recommend/" + this.gugunCode + "/" + this.flag,
      })
        .then((res) => {
          console.log(res);
          this.list = res.data;
        })
        .finally(() => {
          this.show = false;
        });
    },
  },
  computed: {
    ...mapState(house, ["sidos", "guguns"]),
    ...mapState("member", ["isLogin", "userInfo"]),
  },
  created() {
    this.CLEAR_GUGUN_LIST();
    this.gugunCode = null;
    if (this.sidoCode) this.getGugun(this.sidoCode);
    console.log(this.userInfo);
    if (
      this.userInfo.user_rec_con == "T" &&
      this.userInfo.user_rec_cafe != "T"
    ) {
      this.user_rec = " 편의점";
      this.flag = "1";
    } else if (
      this.userInfo.user_rec_con != "T" &&
      this.userInfo.user_rec_cafe == "T"
    ) {
      this.user_rec = " 카페";
      this.flag = "2";
    } else if (
      this.userInfo.user_rec_con == "T" &&
      this.userInfo.user_rec_cafe == "T"
    ) {
      this.user_rec = "편의점, 카페";
      this.flag = "3";
    }
  },
};
</script>

<style lang="scss" scoped></style>
