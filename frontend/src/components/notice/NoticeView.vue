<template>
  <b-container class="bv-example-row mt-3" style="margin-bottom: 100px">
    <div class="col-md-12">
      <fieldset>
        <br />
        <br />
        <h2>{{ article.notice_title }}</h2>
        <header
          style="margin-top: 20px; padding-top: 20px; padding-botton: 20px"
        >
          <img
            :src="article.notice_img_url"
            alt="사진 미리보기"
            width="500px"
            height="100%"
          /><br /><br /><br /><span v-html="message"></span>
        </header>

        <b-row style="float: right">
          <b-col class="text-right">
            <b-button
              variant="outline-info"
              @click="moveModifyNotice"
              class="mr-2"
              v-if="admin == 'T'"
              >수정</b-button
            >
            <b-button
              variant="outline-danger"
              @click="deleteNotice"
              v-if="admin == 'T'"
              class="mr-2"
              >삭제</b-button
            >
            <b-button
              variant="outline-primary"
              class="mr-2"
              @click="listArticle"
              >목록</b-button
            >
          </b-col>
        </b-row>
      </fieldset>
    </div>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/util/http-common";
import { mapState } from "vuex";
export default {
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState("member", ["admin"]),
    message() {
      if (this.article.notice_content)
        return this.article.notice_content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    http.get(`/notice/${this.$route.params.notice_id}`).then(({ data }) => {
      console.log(data);
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyNotice() {
      this.$router.replace({
        name: "NoticeUpdate",
        params: { notice_id: this.article.notice_id },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteNotice() {
      if (confirm("정말 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "NoticeDelete",
          params: { notice_id: this.article.notice_id },
        });
      }
    },
  },
};
</script>

<style scoped>
img {
  width: 300px;
}
.parent {
  position: relative;
}

.child {
  position: absolute;
  bottom: 0;
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
img[data-v-f1231c98]{
  width:600px;
}
</style>
