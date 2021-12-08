<template>
  <div style="overflow: scroll; height: 600px; margin-top:30px">
    <b-container
      v-if="aptList && aptList.length != 0"
      class="bv-example-row mt-3"
    >
      <apt-list-row
        v-for="(apt, index) in aptList"
        :key="index"
        :apt="apt"
        @latandlng="aptlatandlng"
        @changeComp="changeComp2"
      />
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>아파트 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import AptListRow from "@/components/apt/AptListRow.vue";
import { mapState } from "vuex";

const house = "house";

export default {
  components: {
    AptListRow,
  },
  props: ["floor"],
  data() {
    return {};
  },
  computed: {
    ...mapState(house, ["aptList"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    // 위치부터 찍고 component 교체하기
    async aptlatandlng(data) {
      await this.$emit("aptlatandlng", data);
      //console.log("Apt에게 보냄 ",data);
    },
    changeComp2(data) {
      this.$emit("changeComp3", data);
      //console.log("changeComp3");
    },
  },
};
</script>

<style></style>
