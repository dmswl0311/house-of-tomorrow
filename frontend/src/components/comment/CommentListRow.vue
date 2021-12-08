<template>
  <b-tr>
    <b-td>{{ comment_user_id }}</b-td>
    <b-td>{{ comment_content }}</b-td>
    <b-td>{{ comment_date }}</b-td>
    <!-- <b-button
      variant="outline-danger"
      size="sm"
      @click="deletecomment"
      v-if="userInfo.user_id == this.article.deal_user_id"
      >삭제</b-button
    > -->
  </b-tr>
</template>

<script>
// import { mapState } from "vuex";
// const member = "member";
// import http from "@/util/http-common";
export default {
  name: "CommentListRow",
  props: {
    comment_user_id: String,
    comment_content: String,
    comment_date: String,
  },

  data() {
    return {};
  },
  computed: {
    // ...mapState(member, ["userInfo"]),
  },
  methods: {
    deletecomment() {},
  },
};
</script>

<style lang="scss" scoped></style>
