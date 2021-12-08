<template>
  <form class="vue-form" @submit="onSubmit" @reset="onReset">
    <fieldset>
      <legend v-if="this.type === 'register'">회원 가입</legend>
      <legend v-else>회원 수정</legend>
      <div>
        <label class="label" for="name">이름</label>
        <input
          type="text"
          name="name"
          id="name"
          required=""
          v-model="user.user_name"
          placeholder="이름을 입력하세요."
        />
      </div>
      <div>
        <label class="label" for="user_id">아이디</label>
        <b-row v-if="this.type === 'register'">
          <b-col cols="10"
            ><input
              type="text"
              name="user_id"
              id="user_id"
              required=""
              v-model="user.user_id"
              placeholder="아이디를 입력하세요."
          /></b-col>
          <b-col cols="2">
            <b-icon
              icon="check-square"
              font-scale="3"
              variant="success"
              @click="idCheck"
              v-if="this.type === 'register'"
            ></b-icon>
            <!-- <b-button
              pill
              variant="info"
              style="margin-bottom: 10px"
              @click="idCheck"
              v-if="this.type === 'register'"
              >check</b-button
            > -->
          </b-col>
        </b-row>
        <b-row v-else>
          <input
            type="text"
            name="user_id"
            id="user_id"
            required=""
            v-model="user.user_id"
            placeholder="아이디를 입력하세요."
            readonly
          />
        </b-row>

        <!-- <div id="idresult" class="mt-1"></div> -->
      </div>

      <div>
        <label class="label" for="user-pass">비밀번호</label>
        <input
          type="password"
          id="user-pass"
          required=""
          @keyup="validPw"
          v-model="user.user_pass"
          placeholder="비밀번호를 입력하세요."
        />
        <!--  @keyup="validPw" -->
        <div>{{ validcheck }}</div>
      </div>
      <div>
        <label class="label" for="user_pass_ck">비밀번호 확인</label>
        <input
          type="password"
          id="user_pass_ck"
          required=""
          @keyup="checkPw"
          v-model="user.user_pass_ck"
          placeholder="비밀번호 확인"
        />
        <div>{{ pwcheck }}</div>
      </div>
      <div>
        <label class="label" for="name">주소</label>
        <input
          type="text"
          name="user_addr"
          id="user_addr"
          required=""
          v-model="user.user_addr"
          placeholder="주소를 입력하세요."
        />
      </div>

      <div v-if="this.type === 'register'">
        <h4>집추천을 위한 선호 구역 선택</h4>
        <ul class="vue-form-list">
           <li>
          </li>
          <li>
          </li>
          <li>
          </li>
          <li>
          </li>
          <li>
            <input
              type="checkbox"
              name="checkbox-1"
              id="checkbox-1"
              value="T"
              @change="ss"
              v-model="user.user_rec_con"
              v-if="this.type === 'register'"
            />
            <label for="checkbox-1">편의점</label>
          </li>
          <li>
            <input
              type="checkbox"
              name="checkbox-2"
              id="checkbox-2"
              value="T"
              @change="ss"
              v-model="user.user_rec_cafe"
              v-if="this.type === 'register'"
            />
            <label for="checkbox-2">카페</label>
          </li>
        </ul>
      </div>
      <div>
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'modify'"
          >회원정보 수정
        </b-button>
        <b-button type="submit" variant="outline-success" class="m-1" v-else
          >회원가입
        </b-button>
        <b-button type="reset" variant="outline-danger" class="m-1"
          >초기화</b-button
        >
      </div>
    </fieldset>
  </form>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import http from "@/util/http-common";
const member = "member";
export default {
  name: "MemberJoinForm",
  data() {
    return {
      selected: null,
      user: {
        user_id: "",
        user_pass: "",
        user_pass_ck: "",
        user_name: "",
        user_addr: "",
        user_rec_con: "F",
        user_rec_cafe: "F",
        user_rec_daiso: "F",
      },
      idresult: "", //아이디 유효성
      validcheck: "", //비밀번호 유효성
      pwcheck: "", //비밀번호 체크
      check: "true",
      idcheck: "false",
      state: "",
    };
  },
  props: { type: { type: String } },
  created() {
    if (this.type === "modify") {
      this.state = "readonly";
      http.get(`/user/${this.userInfo.user_id}`).then(({ data }) => {
        this.user.user_id = data.user_id;
        this.user.user_name = data.user_name;
        this.user.user_addr = data.user_addr;
      });
    }
  },

  computed: {
    ...mapState(member, ["userInfo", "isok"]),
  },
  methods: {
    ...mapMutations(member, ["ISUPDATE"]),
    onSubmit(event) {
      event.preventDefault();
      // let err = true;
      // let msg = "";
      //  !this.user.user_name &&
      //   ((msg = "이름을 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      //  !this.user.user_id &&
      //   ((msg = "아이디를 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      //   !this.user.user_pass &&
      //   ((msg = "비밀번호를 입력해주세요"),
      //   (err = false),
      //   this.$refs.subject.focus());
      // err &&
      //   !this.user.user_addr &&
      //   ((msg = "주소를 입력해주세요"),
      //   (err = false),
      //   this.$refs.content.focus());

      // if (!err) alert(msg);
      // else
      this.type === "register" ? this.registerMember() : this.modifyMember();
    },

    onReset(event) {
      event.preventDefault();
      this.user.user_id = "";
      this.user.user_name = "";
      this.user.user_pass = "";
      this.user.user_pass_ck = "";
      this.user.user_addr = "";
    },
    //비밀번호 validation체크
    validPw() {
      var reg =
        /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      var pw = this.user.user_pass;

      if (false === reg.test(pw)) {
        this.validcheck =
          "비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.";
      } else {
        console.log("통과");
        this.validcheck = "유효합니다.";
      }
    },
    //비밀번호 체크
    checkPw() {
      if (this.user.user_pass != this.user.user_pass_ck) {
        console.log("체크실패");
        this.pwcheck = "위 비밀번호와 다릅니다.";
      } else {
        console.log("체크성공");
        this.pwcheck = "위 비밀번호와 동일합니다.";
      }
    },
    idCheck() {
      http({
        method: "get",
        url: "/user/idcheck/" + this.user.user_id,
      }).then(({ data }) => {
        console.log(data);
        if (data == 1) {
          alert("사용 불가능합니다. ");
        } else {
          alert("사용 가능합니다.");
          this.idcheck = "true";
        }
      });
    },

    // idCheck() {
    //   checkId(
    //     this.user.userid,
    //     ({ data }) => {
    //       let cnt = data.idcount;
    //       if (cnt == 0) {
    //         this.idresult = this.user.userid + "는 사용 가능합니다.";
    //       } else {
    //         this.idresult = this.user.userid + "는 사용할 수 없습니다.";
    //       }
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // },

    registerMember() {
      if (this.idcheck == "false") {
        alert("아이디 체크 해주세요.");
        return;
      }
      console.log("rec정보", this.user.user_rec_con);
      if (this.user.user_rec_con == false && this.user.user_rec_cafe == false) {
        alert("관심지역을 한가지 이상 선택해주세요.");
        return;
      }
      if (this.user.user_rec_con == false) {
        this.user.user_rec_con = "F";
      } else if (this.user.user_rec_con == true) {
        this.user.user_rec_con = "T";
      }
      if (this.user.user_rec_cafe == false) {
        this.user.user_rec_cafe = "F";
      } else if (this.user.user_rec_cafe == true) {
        this.user.user_rec_cafe = "T";
      }
      console.log("user정보 ", this.user);
      http
        .post(`/user/`, {
          user_id: this.user.user_id,
          user_pass: this.user.user_pass,
          user_name: this.user.user_name,
          user_addr: this.user.user_addr,
          user_rec_con: this.user.user_rec_con,
          user_rec_cafe: this.user.user_rec_cafe,
          user_rec_daiso: this.user.user_rec_daiso,
        })
        .then(({ data }) => {
          let msg = "";
          if (data === "success") {
            msg = "회원가입 성공";
          } else {
            msg = "회원가입 실패";
          }
          alert(msg);
          this.moveList();
        });
    },
    ss(){
      console.log("편의점확인",this.user_rec_con);
      console.log("카페확인",this.user_rec_cafe);
    },
    async modifyMember() {
      http.defaults.headers["access-token"] =
        sessionStorage.getItem("access-token");
      await http
        .put(`/user/`, {
          user_name: this.user.user_name,
          user_id: this.user.user_id,
          user_pass: this.user.user_pass,
          user_addr: this.user.user_addr,
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
          this.$router.push({ name: "MemberMyPage" });
        })
        .finally(() => {
          this.ISUPDATE("ok");
        });
    },
    moveList() {
      this.$router.push({ name: "Home" });
    },
    submit: function () {
      this.submitted = true;
    },
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css?family=Source+Code+Pro:300,400");

*,
*::after,
*::before {
  box-sizing: border-box;
}

body {
  color: #fff;
  background: #949c4e;
  background: linear-gradient(
    115deg,
    rgba(86, 216, 228, 1) 10%,
    rgba(159, 1, 234, 1) 90%
  );
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial,
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

html,
body,
.container {
  min-height: 100vh;
}

.center {
  display: flex;
  justify-content: center;
  align-items: center;
}

a {
  color: #2c3e50;
  text-decoration: none;
}

header {
  position: relative;
  height: 150px;
  padding-top: 50px;
}

header h1 {
  text-align: center;
  font-size: 2.4rem;
  font-weight: 300;
}

#app {
  display: flex;
}

.vue-form {
  font-size: 16px;
  width: 500px;
  padding: 15px 30px;
  border-radius: 4px;
  margin: 20px auto;
  width: 500px;
  background-color: #fff;
  box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.3);
}
.vue-form fieldset {
  margin: 24px 0 0 0;
}
.vue-form legend {
  padding-bottom: 10px;
  border-bottom: 1px solid #ecf0f1;
}
.vue-form div {
  position: relative;
  margin: 20px 0;
}
.vue-form h4,
.vue-form .label {
  color: #94aab0;
  margin-bottom: 10px;
}
.vue-form .label {
  display: block;
}
.vue-form input,
.vue-form textarea,
.vue-form select,
.vue-form label {
  color: #2b3e51;
}
.vue-form input[type="text"],
.vue-form input[type="password"],
.vue-form textarea,
.vue-form select,
.vue-form legend {
  display: block;
  width: 100%;
  appearance: none;
}
.vue-form input[type="text"],
.vue-form input[type="password"],
.vue-form textarea,
.vue-form select {
  padding: 12px;
  border: 1px solid #cfd9db;
  background-color: #ffffff;
  border-radius: 0.25em;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.08);
}
.vue-form input[type="text"]:focus,
.vue-form input[type="password"]:focus,
.vue-form textarea:focus,
.vue-form select:focus {
  outline: none;
  border-color: #2c3e50;
  box-shadow: 0 0 5px rgba(44, 151, 222, 0.2);
}
.vue-form .select {
  position: relative;
}
.vue-form .select::after {
  content: "";
  position: absolute;
  z-index: 1;
  right: 16px;
  top: 50%;
  margin-top: -8px;
  display: block;
  width: 16px;
  height: 16px;
  background: url("data:image/svg+xml;charset=utf-8,%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22utf-8%22%3F%3E%0D%0A%3C%21DOCTYPE%20svg%20PUBLIC%20%22-%2F%2FW3C%2F%2FDTD%20SVG%201.1%2F%2FEN%22%20%22http%3A%2F%2Fwww.w3.org%2FGraphics%2FSVG%2F1.1%2FDTD%2Fsvg11.dtd%22%3E%0D%0A%3Csvg%20version%3D%221.1%22%20id%3D%22Layer_1%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20x%3D%220px%22%20y%3D%220px%22%0D%0A%09%20width%3D%2216px%22%20height%3D%2216px%22%20viewBox%3D%220%200%2016%2016%22%20enable-background%3D%22new%200%200%2016%2016%22%20xml%3Aspace%3D%22preserve%22%3E%0D%0A%3Cg%3E%0D%0A%09%3Cpolygon%20fill%3D%22%232c3e50%22%20points%3D%220.9%2C5.5%203.1%2C3.4%208%2C8.3%2012.9%2C3.4%2015.1%2C5.5%208%2C12.6%20%09%22%2F%3E%0D%0A%3C%2Fg%3E%0D%0A%3C%2Fsvg%3E")
    no-repeat center center;
  pointer-events: none;
}
.vue-form select {
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  cursor: pointer;
}
.vue-form select::-ms-expand {
  display: none;
}
.vue-form .vue-form-list {
  margin-top: 16px;
}
.vue-form .vue-form-list::after {
  clear: both;
  content: "";
  display: table;
}
.vue-form .vue-form-list li {
  display: inline-block;
  position: relative;
  user-select: none;
  margin: 0 26px 16px 0;
  float: left;
}
.vue-form input[type="radio"],
.vue-form input[type="checkbox"] {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  margin: 0;
  padding: 0;
  opacity: 0;
  z-index: 2;
}
.vue-form input[type="radio"] + label,
.vue-form input[type="checkbox"] + label {
  padding-left: 24px;
}
.vue-form input[type="radio"] + label::before,
.vue-form input[type="radio"] + label::after,
.vue-form input[type="checkbox"] + label::before,
.vue-form input[type="checkbox"] + label::after {
  content: "";
  display: block;
  position: absolute;
  left: 0;
  top: 50%;
  margin-top: -8px;
  width: 16px;
  height: 16px;
}
.vue-form input[type="radio"] + label::before,
.vue-form input[type="checkbox"] + label::before {
  border: 1px solid #cfd9db;
  background: #ffffff;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.08);
}
.vue-form input[type="radio"] + label::before,
.vue-form input[type="radio"] + label::after {
  border-radius: 50%;
}
.vue-form input[type="checkbox"] + label::before,
.vue-form input[type="checkbox"] + label::after {
  border-radius: 0.25em;
}
.vue-form input[type="radio"] + label::after,
.vue-form input[type="checkbox"] + label::after {
  background-color: #2c3e50;
  background-position: center center;
  background-repeat: no-repeat;
  box-shadow: 0 0 5px rgba(44, 151, 222, 0.4);
  display: none;
}
.vue-form input[type="radio"] + label::after {
  background-image: url("data:image/svg+xml;charset=utf-8,%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22utf-8%22%3F%3E%0D%0A%3C%21DOCTYPE%20svg%20PUBLIC%20%22-%2F%2FW3C%2F%2FDTD%20SVG%201.1%2F%2FEN%22%20%22http%3A%2F%2Fwww.w3.org%2FGraphics%2FSVG%2F1.1%2FDTD%2Fsvg11.dtd%22%3E%0D%0A%3Csvg%20version%3D%221.1%22%20id%3D%22Layer_1%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20x%3D%220px%22%20y%3D%220px%22%0D%0A%09%20width%3D%2216px%22%20height%3D%2216px%22%20viewBox%3D%220%200%2016%2016%22%20enable-background%3D%22new%200%200%2016%2016%22%20xml%3Aspace%3D%22preserve%22%3E%0D%0A%3Ccircle%20fill%3D%22%23FFFFFF%22%20cx%3D%228%22%20cy%3D%228%22%20r%3D%223%22%2F%3E%0D%0A%3C%2Fsvg%3E");
}
.vue-form input[type="checkbox"] + label::after {
  background-image: url("data:image/svg+xml;charset=utf-8,%3C%3Fxml%20version%3D%221.0%22%20encoding%3D%22utf-8%22%3F%3E%0D%0A%3C%21--%20Generator%3A%20Adobe%20Illustrator%2018.1.1%2C%20SVG%20Export%20Plug-In%20.%20SVG%20Version%3A%206.00%20Build%200%29%20%20--%3E%0D%0A%3C%21DOCTYPE%20svg%20PUBLIC%20%22-%2F%2FW3C%2F%2FDTD%20SVG%201.1%2F%2FEN%22%20%22http%3A%2F%2Fwww.w3.org%2FGraphics%2FSVG%2F1.1%2FDTD%2Fsvg11.dtd%22%3E%0D%0A%3Csvg%20version%3D%221.1%22%20id%3D%22Layer_1%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20x%3D%220px%22%20y%3D%220px%22%0D%0A%09%20width%3D%2216px%22%20height%3D%2216px%22%20viewBox%3D%220%200%2016%2016%22%20enable-background%3D%22new%200%200%2016%2016%22%20xml%3Aspace%3D%22preserve%22%3E%0D%0A%3Cpolyline%20fill%3D%22none%22%20stroke%3D%22%23FFFFFF%22%20stroke-width%3D%222%22%20stroke-linecap%3D%22square%22%20stroke-miterlimit%3D%2210%22%20points%3D%225%2C8%207%2C10%2011%2C6%20%22%2F%3E%0D%0A%3C%2Fsvg%3E");
}
.vue-form input[type="radio"]:focus + label::before,
.vue-form input[type="checkbox"]:focus + label::before {
  box-shadow: 0 0 5px rgba(44, 151, 222, 0.6);
}
.vue-form input[type="radio"]:checked + label::after,
.vue-form input[type="checkbox"]:checked + label::after {
  display: block;
}
.vue-form input[type="radio"]:checked + label::before,
.vue-form input[type="radio"]:checked + label::after,
.vue-form input[type="checkbox"]:checked + label::before,
.vue-form input[type="checkbox"]:checked + label::after {
  animation: cd-bounce 0.3s;
}
.vue-form textarea {
  min-height: 120px;
  resize: vertical;
  overflow: auto;
}
.vue-form input[type="submit"] {
  border: none;
  background: #2c3e50;
  border-radius: 0.25em;
  padding: 12px 20px;
  color: #ffffff;
  font-weight: bold;
  float: right;
  cursor: pointer;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  appearance: none;
}
.no-touch .vue-form input[type="submit"]:hover {
  background: #42a2e1;
}
.vue-form input[type="submit"]:focus {
  outline: none;
  background: #2b3e51;
}
.vue-form input[type="submit"]:active {
  transform: scale(0.9);
}
.vue-form .error-message {
  height: 35px;
  margin: 0px;
}
.vue-form .error-message p {
  background: #e94b35;
  color: #ffffff;
  font-size: 1.4rem;
  text-align: center;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  border-radius: 0.25em;
  padding: 16px;
}
.vue-form .error {
  border-color: #e94b35 !important;
}
.vue-form .counter {
  color: #2c3e50;
  position: absolute;
  right: 0px;
  top: 0px;
  font-size: 10px;
  padding: 4px;
}

.debug {
  border-radius: 4px;
  margin: 50px auto;
  width: 500px;
  background-color: #000;
  padding: 50px;
  background: rgba(0, 0, 0, 0.8);
  box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.3);
}

.debug pre {
  color: #ffffff;
  font-size: 18px;
  line-height: 30px;
  font-family: "Source Code Pro", monospace;
  font-weight: 300;
  white-space: pre-wrap;
}

@-webkit-keyframes cd-bounce {
  0%,
  100% {
    -webkit-transform: scale(1);
  }
  50% {
    -webkit-transform: scale(0.8);
  }
}
@-moz-keyframes cd-bounce {
  0%,
  100% {
    -moz-transform: scale(1);
  }
  50% {
    -moz-transform: scale(0.8);
  }
}
@keyframes cd-bounce {
  0%,
  100% {
    transform: scale(1);
  }
  50% {
    transform: scale(0.8);
  }
}
</style>
