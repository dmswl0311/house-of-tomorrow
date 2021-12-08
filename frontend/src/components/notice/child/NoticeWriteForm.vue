<template>
  <div class="col-md-12">
    <form @submit="onSubmit" @reset="onReset">
      <fieldset>
        <input
          type="text"
          id="subject"
          required
          v-model="article.notice_title"
          placeholder="제목을 입력하세요"
          style="margin-top: 20px"
        />
        <ckeditor v-model="article.notice_content" :config="editorConfig"></ckeditor>
        <!-- <textarea
          id="content"
          required
          v-model="article.notice_content"
          placeholder="구체적인 내용을 입력하세요"
          rows="10"
          max-rows="15"
        /> -->
        <img
          :src="newPhoto"
          alt="사진 미리보기"
          width="200px"
          height="100%"
        /><br />
        <form method="post" enctype="multipart/form-data">
          <input type="file" ref="file" @change="onFileSelected" />
        </form>
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
export default {
  name: "NoticeWriteForm",
  data() {
    return {
      article: {
        notice_id: 0,
        notice_title: "",
        notice_user_id: "admin",
        notice_content: "",
        notice_img_url: "",
      },
      newPhoto: "https://cdn-icons-png.flaticon.com/512/2659/2659360.png",
      // editorData: "",
      editorConfig: {},
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/notice/${this.$route.params.notice_id}`).then(({ data }) => {
        this.article = data;
        this.newPhoto = data.notice_img_url;
      });
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      console.log("아예 안찍히나요?");
      console.log("editorData내용 :: ", this.editorData);

      let err = true;
      let msg = "";
      // !this.article.notice_user_id &&
      //   ((msg = "아이디를 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      !this.article.notice_title &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.notice_content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.notice_id = 0;
      // this.article.notice_user_id = "";
      this.article.notice_title = "";
      this.article.notice_content = "";
      this.article.notice_img_url = "";
      this.$router.push({ name: "NoticeList" });
    },
    registArticle() {
      // console.log(this.$refs.file.files[0]);
      //let file = this.$refs.file.files[0];
      // 서버로
      // const formData = new FormData();
      // formData.append("photo",file);
      http
        .post(`/notice/`, {
          notice_title: this.article.notice_title,
          notice_content: this.article.notice_content,
          notice_user_id: this.article.notice_user_id,
          notice_img_url: this.newPhoto,
        })
        .then(({ data }) => {
          let msg = "등록성공";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/notice/`, {
          notice_id: this.article.notice_id,
          notice_title: this.article.notice_title,
          notice_content: this.article.notice_content,
          notice_img_url: this.article.notice_img_url,
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
          this.$router.push({ name: "NoticeList" });
        });
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
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
form[data-v-6a83845e] {
  width: 100%;
}
form[data-v-6a83845e][data-v-6a83845e] {
  max-width: 800px;
}
</style>
