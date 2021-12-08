<template>
  <div>
    <b-container v-if="apt" class="bv-example-row" style="margin-top: 20px">
      <road-view :data="data"></road-view>
      <b-card no-body style="max-width: 550px">
        <b-list-group flush>
          <b-list-group-item>
            <h3>
              <b-icon
                icon="heart-fill"
                variant="danger"
                animation="throb"
                font-scale="1"
                @click="bookmarkBtn"
              ></b-icon>
              {{ apt.아파트 }}
            </h3></b-list-group-item
          >
          <b-list-group-item> {{ apt.거래금액 }}만원</b-list-group-item>
          <b-list-group-item>층수 : {{ apt.층 }}층</b-list-group-item>
          <b-list-group-item
            >면적 :
            {{
              Math.ceil(parseInt(apt.전용면적) / 3.3058)
            }}평</b-list-group-item
          >
          <b-list-group-item
            ><b-button variant="outline-primary" @click="backBtn()"
              >뒤로가기</b-button
            ></b-list-group-item
          >
        </b-list-group>
      </b-card>

      <!--  -->
    </b-container>
  </div>
</template>

<script>
import RoadView from "@/components/util/RoadView.vue";
import http from "@/util/http-common";
import { mapActions, mapState } from "vuex";

const house = "house";
const member = "member";

export default {
  name: "AptDetail",
  components: {
    RoadView,
  },
  data() {
    return {
      lat: this.data[0][0],
      lng: this.data[0][1],
    };
  },
  props: ["data"],
  watch: {
    lng(newMsg) {
      console.log("props lng", newMsg);
    },
    lat(newMsg) {
      console.log("props lat", newMsg);
    },
  },
  computed: {
    ...mapState(house, ["apt", "currentView"]),
    ...mapState(member, ["userInfo", "isLogin"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  methods: {
    ...mapActions(house, ["changeComp"]),
    backBtn() {
      this.$emit("back", "AptList");
    },
    bookmarkBtn() {
      if (this.userInfo == null) {
        alert("로그인 해주세요");
        return;
      }
      http({
        method: "get",
        url: "/bookmark/" + this.lat + "/" + this.lng,
      }).then(({ data }) => {
        console.log(data);
        if (data != 1) {
          http
            .post(`/bookmark/`, {
              bookmark_user_id: this.userInfo.user_id,
              bookmark_apt_name: this.apt.아파트,
              bookmark_apt_dongname: this.apt.법정동,
              bookmark_apt_floor: this.apt.층,
              bookmark_apt_price: this.apt.거래금액,
              bookmark_apt_lat: this.lat,
              bookmark_apt_lng: this.lng,
            })
            .then(({ data }) => {
              let msg = "등록성공";
              if (data === "success") {
                msg = "등록이 완료되었습니다.";
              }
              alert(msg);
            });
        } else {
          alert("이미 즐겨찾기에 추가되었습니다.");
        }
      });
    },
  },
  //   filters: {
  //     price(value) {
  //       if (!value) return value;
  //       return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  //     },
  //   },
};
</script>

<style scoped>
.list-group-item {
  padding: 0.5rem 1.25rem;
}
</style>
