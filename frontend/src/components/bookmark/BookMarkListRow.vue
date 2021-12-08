<template>
  <div style="float: left; margin-right: 40px; margin-bottom: 20px">
    <b-card
      img-src="https://img.hankyung.com/photo/201908/AA.20284290.1.jpg"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem"
      class="mb-2"
    >
      <b-card-text> <h4>{{ bookmark_apt_name }}</h4> </b-card-text>
      <hr />
      <b-card-text> {{ bookmark_apt_dongname }} </b-card-text>
      <hr />
      <b-card-text> {{ bookmark_apt_floor }}층</b-card-text>
      <hr />
      <b-card-text> {{ bookmark_apt_price }}만원 </b-card-text>
      <hr />
      <b-button pill variant="outline-danger" @click="listdelete"
        >삭제</b-button
      >
    </b-card>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapMutations, mapState } from "vuex";
const myapt = "myapt";
export default {
  name: "BookMarkListRow",

  data() {
    return {};
  },
  computed: {
    ...mapState(myapt, ["isupdate"]),
  },
  props: {
    bookmark_id: Number,
    bookmark_user_id: String,
    bookmark_apt_name: String,
    bookmark_apt_dongname: String,
    bookmark_apt_floor: Number,
    bookmark_apt_price: String,
    bookmark_apt_lat: String,
    bookmark_apt_lng: String,
  },

  mounted() {},

  methods: {
    ...mapMutations(myapt, ["SET_IS_UPDATE"]),
    listdelete() {
      let flag=confirm("찜목록에서 삭제하시겠습니까?");
      if(flag){
      console.log(this.bookmark_user_id);
      http({
        method: "delete",
        url: "/bookmark/" + this.bookmark_id,
      }).then(({ data }) => {
        let msg = "삭제 성공했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
          if (this.isupdate == "false") {
            this.SET_IS_UPDATE("true");
          } else {
            this.SET_IS_UPDATE("false");
          }
        }
        alert(msg);
      });
      }else{
        return;
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
