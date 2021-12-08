<template>
  <div class="col-md-12">
    <form @submit="onSubmit" @reset="onReset">
      <fieldset>
        <input
          type="text"
          id="subject"
          required
          v-model="article.deal_title"
          placeholder="제목을 입력하세요"
          style="margin-top: 20px"
        />
        <b-row>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_price"
              placeholder="가격"
              style="margin-top: 20px"
            />
          </b-col>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_floor"
              placeholder="층수"
              style="margin-top: 20px"
            />
          </b-col>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_size"
              placeholder="면적"
              style="margin-top: 20px"
            />
          </b-col>
        </b-row>
        <input
          type="text"
          id="subject"
          required
          v-model="article.deal_addr"
          placeholder="주소를 입력하세요"
          style="margin-top: 20px"
        />
        <ckeditor v-model="article.deal_content" :config="editorConfig"></ckeditor>
        <img
          :src="newPhoto"
          alt="사진 미리보기"
          width="200px"
          height="100%"
        /><br />
        <form method="post" enctype="multipart/form-data">
          <input type="file" ref="file" @change="onFileSelected" />
        </form>
        <b-row>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_tag1"
              placeholder="태그1"
              style="margin-top: 20px"
            />
          </b-col>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_tag2"
              placeholder="태그2"
              style="margin-top: 20px"
            />
          </b-col>
          <b-col>
            <input
              type="text"
              id="subject"
              required
              v-model="article.deal_tag3"
              placeholder="태그3"
              style="margin-top: 20px"
            />
          </b-col>
        </b-row>
      </fieldset>
      <div style="margin-bottom: 30px">
        <b-button
          type="submit"
          variant="outline-success"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="outline-primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="outline-danger" class="m-1"
          >초기화</b-button
        >
      </div>
    </form>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapState } from "vuex";
export default {
  name: "DealWriteForm",
  data() {
    return {
      editorConfig:{},
      newPhoto: "https://cdn-icons-png.flaticon.com/512/2659/2659360.png",
      article: {
        deal_user_id: "",
        deal_title: "",
        deal_price: "",
        deal_floor: "",
        deal_size: "",
        deal_addr: "",
        deal_content: "",
        deal_img_url: "",
        deal_tag1: "",
        deal_tag2: "",
        deal_tag3: "",
      },
    };
  },
  computed: {
    ...mapState("member", ["userInfo"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/deal/${this.$route.params.deal_id}`).then(({ data }) => {
        this.article = data;
      });
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      // let err = true;
      // let msg = "";
      //  !this.article.deal_user_id &&
      //   ((msg = "아이디를 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      //   !this.article.deal_title &&
      //   ((msg = "제목 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      //   !this.article.deal_content &&
      //   ((msg = "내용 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());
      // err &&
      //   !this.article.deal_floor &&
      //   ((msg = "층수를 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());
      // err &&
      //   !this.article.deal_size &&
      //   ((msg = "면적을 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());
      // err &&
      //   !this.article.deal_price &&
      //   ((msg = "가격을 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());
      // err &&
      //   !this.article.deal_addr &&
      //   ((msg = "주소를 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());

      // if (!err) alert(msg);
      // else
      this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.notice_id = 0;
      this.article.deal_user_id = "";
      this.article.deal_title = "";
      this.article.deal_price = "";
      this.article.deal_floor = "";
      this.article.deal_size = "";
      this.article.deal_addr = "";
      this.article.deal_content = "";
      this.article.deal_img_url = "";
      this.article.deal_tag1 = "";
      this.article.deal_tag2 = "";
      this.article.deal_tag3 = "";
      this.$router.push({ name: "DealList" });
    },
    registArticle() {
      http
        .post(`/deal/`, {
          deal_user_id: this.userInfo.user_id,
          deal_title: this.article.deal_title,
          deal_price: this.article.deal_price,
          deal_floor: this.article.deal_floor,
          deal_size: this.article.deal_size,
          deal_addr: this.article.deal_addr,
          deal_content: this.article.deal_content,
          deal_img_url: this.newPhoto,
          deal_tag1: this.article.deal_tag1,
          deal_tag2: this.article.deal_tag2,
          deal_tag3: this.article.deal_tag3,
        })
        .then(({ data }) => {
          let msg = "등록성공";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "DealList" });
        });
    },
    modifyArticle() {
      http
        .put(`/deal/`, {
          deal_id: this.article.deal_id,
          deal_user_id: this.userInfo.user_id,
          deal_title: this.article.deal_title,
          deal_price: this.article.deal_price,
          deal_floor: this.article.deal_floor,
          deal_size: this.article.deal_size,
          deal_addr: this.article.deal_addr,
          deal_content: this.article.deal_content,
          deal_img_url: this.article.deal_img_url,
          deal_tag1: this.article.deal_tag1,
          deal_tag2: this.article.deal_tag2,
          deal_tag3: this.article.deal_tag3,
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          } else {
            msg = "수정 실패";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "DealList" });
        });
    },
    onFileSelected(event) {
      console.log(event.target);
      const reader = new FileReader();
      const that = this;
      reader.addEventListener("load", function () {
        // 여기서 this는 FileReader이다. data가 아님..
        console.log(this);
        that.newPhoto = reader.result;
      });
      reader.readAsDataURL(event.target.files[0]);
    },
  },
};
</script>

<style scoped>
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
select {
  background: rgba(255, 255, 255, 0.1);
  border: none;
  font-size: 16px;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 15px;
  width: 100%;
  background-color: #e8eeef;
  color: #000000;
  box-shadow: 5px 6px 0 #00000008 inset;
  margin-bottom: 30px;
}
form[data-v-4a00f3d1]{
  max-width:1200px;
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

@media screen and (min-width: 480px) {
  form {
    max-width: 480px;
  }
}
form[data-v-4a00f3d1]{
  width:100%;
}
form[data-v-4a00f3d1][data-v-4a00f3d1]{
  max-width:800px;
}
</style>
