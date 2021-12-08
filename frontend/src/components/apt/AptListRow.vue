<template>
  <b-row
    class="m-2"
    @click="selectApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="8" class="align-self-center">
      {{ apt.아파트 }}
    </b-col>
    <b-col cols="2" class="align-self-center">
      <b-badge variant="success">{{ apt.거래금액 }}만원</b-badge>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";

const house = "house";

export default {
  name: "AptListRow",
  data() {
    return {
      isColor: false,
      lat: Object,
      lng: Object,
      data: [],
    };
  },
  props: {
    apt: Object,
  },
  methods: {
    ...mapActions(house, ["getAptDetail", "changeComp"]),
    async selectApt() {
      console.log("listRow : ", this.apt);
      //this.$store.dispatch("getHouse", this.house);
      await this.getAptDetail(this.apt);
      let addr = this.apt.법정동 + " " + this.apt.지번;
      axios({
        methods: "get",
        url:
          "https://dapi.kakao.com/v2/local/search/address.json?query=" +
          encodeURIComponent(addr),
        headers: {
          Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
        },
      })
        .then((res) => {
          // 주소 변환
          if (res != null) {
            let lat = res.data.documents[0].road_address.y;
            let lng = res.data.documents[0].road_address.x;
            this.lat = lat;
            this.lng = lng;
            let position = [];
            position.push(lat);
            position.push(lng);
            this.data.push(position);
          }
        })
        .finally(() => {
          console.log("AptListRow 값 확인", this.data);
          this.$emit("latandlng", this.data);
          this.$emit("changeComp", "AptDetail");
        });
      //console.log("AptList에게 data 보냄 ", this.data);
      //   await this.changeComp("AptDetail");
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  computed: {
    ...mapState(house, ["apt_lat"]),
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgba(245, 192, 18, 0.301);
  font-weight: bold;
}
</style>
