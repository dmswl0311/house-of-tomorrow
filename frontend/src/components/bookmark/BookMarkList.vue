<template>
  <div>
    <!-- <bookmark-list-row v-for="(apt, index) in apts" :key="index" v-bind="apt" /> -->

    <book-mark-list-row
      v-for="(apt, index) in apts"
      :key="index"
      v-bind="apt"
    />
  </div>
</template>

<style></style>

<script>
import http from "@/util/http-common";
import BookMarkListRow from "@/components/bookmark/BookMarkListRow.vue";
import { mapState } from "vuex";
const myapt = "myapt";
// import BookMarkListRow from "./BookMarkListRow.vue";
const member = "member";
export default {
  name: "BookMarkList",
  components: {
    BookMarkListRow,
  },
  data() {
    return {
      apts: [],
    };
  },
  computed: {
    ...mapState(member, ["userInfo"]),
    ...mapState(myapt, ["isupdate"]),
  },
  created() {
    // console.log(this.userInfo.user_id);
    http({
      method: "get",
      url: "/bookmark/list/" + this.userInfo.user_id,
    }).then(({ data }) => {
      console.log("찜목록확인", data);
      this.apts = data;
    });
  },

  watch: {
    isupdate: function () {
      console.log("isupdate 값 바뀜");
      http({
        method: "get",
        url: "/bookmark/list/" + this.userInfo.user_id,
      }).then(({ data }) => {
        console.log("찜목록확인", data);
        this.apts = data;
      });
    },
  },

  mounted() {},

  methods: {},
};
</script>

<style lang="scss" scoped></style>
