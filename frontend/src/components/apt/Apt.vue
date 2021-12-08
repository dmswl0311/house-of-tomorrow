<template>
  <b-container fluid>
    <b-row>
      <b-col> <br></b-col>
    </b-row>
    <b-row>
      <b-col cols="2">
        <apt-filter></apt-filter>
      </b-col>
      <b-col cols="7">
        <kakao-map></kakao-map>
      </b-col>
      <b-col>
        <component
          v-bind:is="currentView"
          @aptlatandlng="toaptdetail"
          :data="data"
          @changeComp3="changeComp4"
          @back="back1"
        />
      </b-col>
    </b-row>

    <!--<apt-list></apt-list>
    <apt-detail></apt-detail>-->
  </b-container>
</template>

<script>
import KakaoMap from "../util/KakaoMap.vue";
import AptList from "@/components/apt/AptList.vue";
import AptDetail from "@/components/apt/AptDetail.vue";
import AptFilter from "@/components/apt/AptFilter.vue";
import { mapState } from "vuex";

export default {
  data() {
    return {
      data: [],
      currentView: "AptList",
    };
  },
  methods: {
    toaptdetail(data) {
      //console.log("Apt", data);
      this.data = data;
    },
    changeComp4(data) {
      this.currentView = data;
    },
    back1(data) {
      this.currentView = data;
    },
  },
  components: {
    KakaoMap,
    AptList,
    AptDetail,
    AptFilter,
  },
  computed: {
    ...mapState("house", ["aptList"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  // async created() {
  //   await this.$store.commit("CLEAR_CURRENT_VIEW");
  // },
};
</script>

<style></style>
