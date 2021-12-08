<template>
  <div>
    <b-col>
      <b-table-simple hover responsive>
        <tbody>
          <!-- 하위 component인 ListRow에 데이터 전달(props) -->
          <comment-list-row
            v-for="(comment, index) in comments"
            :key="index"
            v-bind="comment"
          />
        </tbody>
      </b-table-simple>
    </b-col>
  </div>
</template>

<script>
import http from "@/util/http-common";
import CommentListRow from "@/components/comment/CommentListRow";
import { mapMutations, mapState } from "vuex";
export default {
  name: "CommentList",
  components: {
    CommentListRow,
  },
  watch: {
    isOk: function () {
      console.log("댓글 리스트 refresh");
      // this.load();
      http({
        method: "get",
        url: "/comment/" + this.deal_id,
      })
        .then(({ data }) => {
          console.log("data", data);
          this.comments = data;
        })
        .finally(() => {
          console.log("finally");
          this.SET_IS_OK("nope");
        });
      // this.SET_IS_OK("back");
    },
  },
  computed: {
    ...mapState("myapt", ["deal_id", "isOk"]),
  },
  data() {
    return {
      comments: null,
      commentdata: "",
    };
  },
  created() {
    console.log("확인", this.deal_id);
    http({
      method: "get",
      url: "/comment/" + this.deal_id,
    }).then(({ data }) => {
      console.log("data", data);
      this.comments = data;
    });
  },
  methods: {
    ...mapMutations("myapt", ["SET_IS_OK"]),
    // load() {
    //   http({
    //     method: "get",
    //     url: "/comment/" + this.deal_id,
    //   }).then(({ data }) => {
    //     console.log("data", data);
    //     this.comments = data;
    //     // this.SET_IS_OK("nope");
    //   });
    // },
  },
};
</script>

<style lang="scss" scoped></style>
