<template>
  <b-container fluid>
    <b-row>
      <b-col cols="7">
        <kakao-map-rec :list="list"></kakao-map-rec>
      </b-col>
      <b-col>
        <component
          v-bind:is="currentRecView"
          :list="list"
          :clickdata="clickdata"
          @listchild="parents"
          @backBtn="backtolist"
        />
        <!-- <rec-list :list="list" @listchild="parents"></rec-list> -->
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import RecList from "@/components/rec/RecList.vue";
import RecDetail from "@/components/rec/RecDetail.vue";
import KakaoMapRec from "@/components/util/KakaoMapRec.vue";

export default {
  components: {
    RecList,
    RecDetail,
    KakaoMapRec,
  },
  props: ["list"],
  data() {
    return {
      clickdata: null,
      currentRecView: "RecList",
    };
  },
  methods: {
    parents(data) {
      this.clickdata = data;
      this.currentRecView = "RecDetail";
    },
    backtolist() {
      this.currentRecView = "RecList";
      this.clickdata = null;
    },
  },
};
</script>

<style></style>
