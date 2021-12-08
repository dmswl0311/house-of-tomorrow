<template>
  <div>
    <!-- Header - set the background image for the header in the line below-->
    <!-- <header
      class="py-5 bg-image-full"
      style="
        background-image: url('http://image.chosun.com/sitedata/image/201810/29/2018102902845_0.jpg');
      "
    >
      <div class="text-center my-1">
        <img
          class="img-fluid rounded-circle mb-3"
          src="@/assets/ssafy_logo.png"
          alt="..."
        />
        <h1 class="text-white fs-3 fw-bolder">어떤집을 찾고 계세요?</h1>
      </div>
    </header> -->
    <!-- Content section-->
    <section>
      <div style="padding-top: 150px;">
        <h1 >원하는 아파트? 여기 있지!</h1>
      </div>
      <div class="container my-1">
        <div class="row justify-content-center">
          <b-row class="mt-4 text-center">
            <b-col class="mt-3">
              <b-form-select
                size="10"
                v-model="sidoCode"
                :options="sidos"
                @change="gugunList"
              ></b-form-select>
            </b-col>
            <b-col class="mt-3">
              <b-form-select
                v-model="gugunCode"
                :options="guguns"
                @change="dongList"
              ></b-form-select>
            </b-col>
            <b-col class="mt-3">
              <b-form-select
                v-model="dongCode"
                :options="dongs"
              ></b-form-select>
            </b-col>
          </b-row>
        </div>
        <div class="row justify-content-center">
          <b-row class="mb-4 text-center">
            <b-col class="mt-3">
              <b-input-group class="mb-3">
                <b-form-input
                  v-model="query"
                  placeholder="아파트명을 입력하세요."
                ></b-form-input>
                <b-input-group-append>
                  <b-button text="Button" variant="light" @click="searchBtn"
                    >검색 🔍</b-button
                  >
                </b-input-group-append>
              </b-input-group>
            </b-col>
          </b-row>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
// import { mapState, mapActions, mapMutations } from "vuex";
import { mapState, mapActions, mapMutations } from "vuex";
const house = "house";

export default {
  name: "Main",
  components: {},
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      query: null,
    };
  },
  computed: {
    ...mapState(house, [
      "sidos",
      "guguns",
      "dongs",
      "aptList",
      "moveGugunCode",
    ]),
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
  methods: {
    //  ...mapActions(["getSido", "getGugun", "getDong"]),
    ...mapMutations(house, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_MOVE_GUGUN_CODE",
    ]),
    ...mapActions(house, [
      "getSido",
      "getGugun",
      "getDong",
      "getAptList",
      "getAptDongList",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    // 시도,군구 검색 버튼 클릭
    async searchBtn() {
      this.SET_MOVE_GUGUN_CODE(this.gugunCode);
      console.log(this.dongCode);
      //console.log("버튼 클릭");
      // 시도, 군구 선택했을 때, 아파트명 x
      console.log("dongCode", this.dongCode);
      if (
        this.sidoCode != null &&
        this.gugunCode != null &&
        this.dongCode == null &&
        this.query == null
      ) {
        console.log("시도, 군구 선택했을 때, 아파트명 x");
        await this.getAptList([this.gugunCode, null]);
        this.$router.push("/apt");
      } else if (
        this.sidoCode != null &&
        this.gugunCode != null &&
        this.dongCode == null &&
        this.query != null
      ) {
        // 시도, 군구 선택했을 때, 아파트명 o
        console.log("시도, 군구 선택했을 때, 아파트명 o");
        await this.getAptList([this.gugunCode, this.query]);
        this.$router.push("/apt");
      } else if (
        this.sidoCode != null &&
        this.gugunCode != null &&
        this.dongCode != null &&
        this.query == null
      ) {
        // 시도, 군구, 동 선택했을 때, 아파트명 x
        console.log("시도, 군구, 동 선택했을 때, 아파트명 x");
        await this.getAptDongList([this.dongCode, null]);
        this.$router.push("/apt");
      } else if (
        this.sidoCode != null &&
        this.gugunCode != null &&
        this.dongCode != null &&
        this.query != null
      ) {
        // 시도, 군구, 동 선택했을 때, 아파트명 o
        console.log("시도, 군구, 동 선택했을 때, 아파트명 o");
        await this.getAptDongList([this.dongCode, this.query]);
        this.$router.push("/apt");
      } else {
        alert("최소 시도, 구군을 선택해주세요!");
        return;
      }
    },
    // aptList() {
    //   console.log(this.dongCode);
    //   this.CLEAR_DONG_LIST();
    //   this.dongCode = null;
    //   if (this.dongCode) this.getHouseList(this.dongCode);
    // },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "Recipekorea";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "GongGothicLight";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicLight.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "IBMPlexSansKR-Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Bold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "twayfly";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_tway@1.0/twayfly.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "EliceDigitalBaeum_Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/EliceDigitalBaeum_Bold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

h1[data-v-fae5bece] {
  font-family: 'IBMPlexSansKR-Bold';
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
section {
  /* */
  /* background-image: url("https://cdn.pixabay.com/photo/2021/11/08/00/30/living-room-6778197_960_720.jpg");  */
  background-image: url("https://cdn.pixabay.com/photo/2021/02/02/18/46/city-5974876_960_720.jpg");
  background-size: cover;
  height: 720px;
  /* background:url("/assets/apt.jpg") no-repeat; */
  /* background-image:url('/assets/apt.jpg');background-repeat: no-repeat; */
  /* background:url('/assets/images/header_roof.png') no-repeat; */
}
h1 {
  font-family: "Pretendard-Regular";
  color: black;
}

@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@700&display=swap");
body {
  background: #333;
  height: 100vh;
  display: flex;
  justify-content: center;
  overflow: hidden;
}
svg {
  width: 30%;
  animation: rotate infinite linear;
  mix-blend-mode: multiply;
  margin: 0 -8%;
}
svg:nth-child(1) {
  fill: tomato;
  animation-duration: 4s;
}
svg:nth-child(2) {
  fill: gold;
  animation-duration: 2s;
  animation-direction: reverse;
}
svg:nth-child(3) {
  fill: lime;
  animation-duration: 6s;
}
svg:nth-child(4) {
  fill: turquoise;
  animation-duration: 3s;
  animation-direction: reverse;
}
@keyframes rotate {
  100% {
    transform: rotate(360deg);
  }
}
</style>
