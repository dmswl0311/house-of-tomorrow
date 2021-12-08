<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
        <b-row>
          <b-col cols="2">
            <!-- <b-form-input
              id="subject"
              v-model="comment.comment_user_id"
              type="text"
              required
              placeholder="아이디 입력"
            ></b-form-input> -->
          </b-col>
          <b-col cols="8">
            <b-form-input
              id="subject"
              v-model="comment.comment_content"
              type="text"
              required
              placeholder="내용을 입력하세요"
            ></b-form-input>
          </b-col>
          <b-col cols="2">
            <b-button variant="primary" class="m-1" @click="submitcomment"
              >댓글작성</b-button
            ></b-col
          >
        </b-row>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/util/http-common";
import { mapState, mapMutations } from "vuex";
export default {
  name: "CommentWrite",

  data() {
    return {
      comment: {
        comment_content: "",
        comment_deal_id: this.propsdata,
        // comment_user_id: "",
      },
    };
  },
  props: {
    propsdata: Number,
  },
  mounted() {},
  computed: {
    ...mapState("myapt", ["deal_id"]),
    ...mapState("member", ["userInfo"]),
  },
  methods: {
    ...mapMutations("myapt", ["SET_IS_OK"]),
    submitcomment() {
      console.log("댓글 작성 누름");
      console.log("comment_content", this.comment.comment_content);
      console.log("comment_deal_id", this.deal_id);
      // console.log("comment_user_id", this.comment.comment_user_id);

      http
        .post(`/comment/`, {
          comment_content: this.comment.comment_content,
          comment_deal_id: this.deal_id,
          comment_user_id: this.userInfo.user_id,
        })
        .then(({ data }) => {
          let msg = "등록성공";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          //    this.$router.push({ name: "DealList" });
        })
        .finally(() => {
          console.log("finally");
          this.SET_IS_OK("refresh");
        });
    },
  },
};
</script>

<style lang="scss" scoped></style>
