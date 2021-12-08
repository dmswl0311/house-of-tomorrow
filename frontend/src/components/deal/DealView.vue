<template>
  <b-container class="bv-example-row mt-3" style="margin-bottom: 100px">
    <div class="col-md-12">
      <fieldset>
        <br />
        <h2>{{ article.deal_title }}</h2>
        <b-row>
          <b-col>
            <h4>
              <span style="margin-right: 10px">
                <b-badge variant="success"
                  >{{ article.deal_price }}원
                </b-badge></span
              >
              <span style="margin-right: 10px"
                ><b-badge variant="success">
                  {{ article.deal_floor }}층</b-badge
                ></span
              >
              <span style="margin-right: 10px"
                ><b-badge variant="success">
                  {{ article.deal_size }}</b-badge
                ></span
              >
              <span style="margin-right: 10px"
                ><b-badge variant="success"
                  >{{ article.deal_addr }}
                </b-badge></span
              >
            </h4>
          </b-col>
        </b-row>

        <header
          style="margin-top: 20px; padding-top: 20px; padding-botton: 20px"
        >
          <img
            :src="article.deal_img_url"
            alt="사진 미리보기"
            width="500px"
            height="100%"
          /><br /><br /><br /><span v-html="article.deal_content"></span>
          <div>
            <br /><br />
            <b-button variant="outline-danger" class="mb-2" @click="updaterec">
              <b-icon icon="heart-fill" aria-hidden="true"></b-icon>
              {{ deal_recommand }}
            </b-button>
          </div>
        </header>

        <b-row style="float: right">
          <b-col>
            <b-button
              variant="outline-info"
              @click="moveModifyDeal"
              v-if="userInfo.user_id == this.article.deal_user_id"
              >수정</b-button
            >
            <b-button
              variant="outline-danger"
              @click="deleteDeal"
              v-if="userInfo.user_id == this.article.deal_user_id"
              >삭제</b-button
            >
            <b-button variant="outline-primary" @click="listArticle"
              >목록</b-button
            >
          </b-col>
        </b-row>
      </fieldset>
    </div>
    <Comment />
  </b-container>
</template>

<script>
// import moment from "moment";
import { mapState } from "vuex";
import http from "@/util/http-common";
import Comment from "@/views/Comment.vue";
// const myapt = "myapt";
const member = "member";
export default {
  components: {
    Comment,
  },
  data() {
    return {
      article: {},
      deal_recommand: null,
    };
  },
  computed: {
    ...mapState(member, ["userInfo"]),
    message() {
      if (this.article.deal_content)
        return this.article.deal_content.split("\n").join("<br>");
      return "";
    },
    // watch: {
    //   isOk: function () {
    //     console.log("댓글 리스트 refresh");
    //     this.load();
    //     this.SET_IS_OK("back");
    //   },
    // },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    console.log(this.$route.params.deal_id);
    http.get(`/deal/${this.$route.params.deal_id}`).then(({ data }) => {
      console.log(data);
      this.article = data;
      this.deal_recommand = this.article.deal_rec;
    });
  },
  methods: {
    // ...mapMutations(myapt, ["SET_IS_OK"]),
    listArticle() {
      this.$router.push({ name: "DealList" });
    },
    moveModifyDeal() {
      this.$router.replace({
        name: "DealUpdate",
        params: { deal_id: this.article.deal_id },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteDeal() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "DealDelete",
          params: { deal_id: this.article.deal_id },
        });
      }
    },
    updaterec() {
      http.put(`/deal/like/${this.article.deal_id}`).then(({ data }) => {
        console.log(data);
        this.deal_recommand = data;
      });
    },
  },
};
</script>

<style scoped>
img {
  width: 300px;
}
footer {
  position: fixed;
  right: -400px;
  bottom: 0px;
  height: 80px;
  width: 100%;
  color: rgba(255, 255, 255, 0);
}
*,
*:before,
*:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
form[data-v-6a83845e] {
  max-width: 1200px;
}

body {
  font-family: "Nunito", sans-serif;
  color: #384047;
}

form {
  width: 900px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}

input[type="text"],
input[type="password"],
input[type="date"],
input[type="datetime"],
input[type="email"],
input[type="number"],
input[type="search"],
input[type="tel"],
input[type="time"],
input[type="url"],
input[type="file"],
textarea,
header,
select {
  background: rgba(255, 255, 255, 0.1);
  border: none;
  font-size: 16px;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 20px;
  width: 100%;
  background-color: #f3f3f3dc;
  color: #000000;
  box-shadow: 5px 6px 0 #00000008 inset;
  margin-bottom: 30px;
}
form[data-v-4a00f3d1] {
  max-width: 1200px;
}
input[type="radio"],
input[type="checkbox"] {
  margin: 0 4px 8px 0;
}

select {
  padding: 6px;
  height: 32px;
  border-radius: 2px;
}

/* button {
  padding: 19px 39px 18px 39px;
  color: #fff;
  background-color: #4bc970;
  font-size: 18px;
  text-align: center;
  font-style: normal;
  border-radius: 5px;
  width: 100%;
  border: 1px solid #3ac162;
  border-width: 1px 1px 3px;
  box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
  margin-bottom: 10px;
} */

fieldset {
  margin-bottom: 30px;
  border: none;
}

legend {
  font-size: 1.4em;
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 8px;
}

label.light {
  font-weight: 300;
  display: inline;
}

.number {
  background-color: #5fcf80;
  color: #fff;
  height: 30px;
  width: 30px;
  display: inline-block;
  font-size: 0.8em;
  margin-right: 4px;
  line-height: 30px;
  text-align: center;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
  border-radius: 100%;
}
img[data-v-f1231c98] {
  width: 600px;
}
</style>
