<template>
  <div class="container" style="font-family: 'Pretendard-Regular'">
    <h1 style="font-family: 'NEXON Lv2 Gothic Bold'; margin-bottom: 20px; margin-top:50px">
      내집마련, 얼마나 걸릴까?
    </h1>
    <br>
    <b-container fluid>
      <b-row class="my-1" >
        <b-col sm="4">
          <code>나이</code>
        </b-col>
        <b-col sm="6">
          <b-form-input
            type="number"
            v-model="age"
            placeholder="나이를 입력해주세요! "
          ></b-form-input>
        </b-col>
         <b-col sm="2">
          <span></span>
        </b-col>
      </b-row>
      <b-row class="my-1">
        <b-col sm="4">
          <code>월 저축액(만원)</code>
        </b-col>
        <b-col sm="6">
          <b-form-input
            type="number"
            v-model="month"
            placeholder="월 저축액을 입력해주세요! "
          ></b-form-input>
        </b-col>
        <b-col sm="2">
          <span></span>
        </b-col>
      </b-row>
      <b-row class="my-1" >
        <b-col sm="4">
          <code>현재 통장 잔고(만원)</code>
        </b-col>
        <b-col sm="6">
          <b-form-input
            type="number"
            v-model="balance"
            placeholder="현재 통장 잔고를 입력해주세요!"
          ></b-form-input>
        </b-col>
         <b-col sm="2">
          <span></span>
        </b-col>
      </b-row>

      <b-row class="my-1" key="number">
        <b-col sm="4">
          <code>살고싶은 지역</code>
        </b-col>
        <b-col sm="3">
          <b-form-select
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
         <b-col sm="3">
           <b-form-select v-model="gugunCode" :options="guguns"></b-form-select>
        </b-col>
      </b-row>
    </b-container>
    <br />
    <b-button variant="outline-primary" @click="submitBtn">찾아보기 👀</b-button>
  </div>
</template>

<script>
const myapt = "myapt";
const house = "house";
import { mapState, mapActions, mapMutations } from "vuex";

export default {
  data() {
    return {
      age: "",
      month: "",
      balance: "",
      sidoCode: null,
      gugunCode: null,
    };
  },
  methods: {
    //...mapActions(myapt,["SET"])
    ...mapMutations(myapt, ["SET_SURVEY"]),
    ...mapMutations(house, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    ...mapActions(house, ["getSido", "getGugun", "getAptList"]),

    submitBtn() {
      console.log("click");
      if (this.gugunCode == null) {
        alert("군/구를 선택해주세요!");
        return;
      }
      // vuex로 저장
      this.SET_SURVEY([this.age, this.month, this.balance, this.gugunCode]);
      // 아파트 리스트 계산
      this.getAptList([this.gugunCode, null]);
      this.$router.push({ name: "MyAptResult" });
    },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
  },
  computed: {
    ...mapState(house, ["sidos", "guguns"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    //  this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
};
</script>

<style></style>
