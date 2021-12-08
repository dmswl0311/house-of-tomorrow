<template>
  <b-container class="mt-4" v-if="userInfo">
    <center>
    <section>
      <div class="col-md-4">
        <div class="card profile-card-2">
          <div class="card-img-block">
            <img
              class="img-fluid"
              src="@/assets/apt.jpg"
              alt="Card image cap"
            />
          </div>
          <div class="card-body pt-5">
            <img
              src="@/assets/ssafyuser.png"
              alt="profile-image"
              class="profile"
            />
            <h3 class="card-title">{{ userInfo.user_name }}</h3>
            <p class="card-text">
              {{ userInfo.user_id }}
            </p>
            <p class="card-text">
              {{ userInfo.user_addr }}
            </p>
            <p class="card-text">
              <b-button variant="outline-primary" @click="usermodify()" class="mr-1"
                >정보수정</b-button
              >
              <b-button variant="outline-danger" @click="userdelete()" class="mr-1" href="#"
                >회원탈퇴</b-button
              >
            </p>
          </div>
        </div>
      </div>
    </section>
    </center>
  </b-container>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const member = "member";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(member, ["userInfo", "isok"]),
  },
  methods: {
    ...mapActions(member, ["getUserInfo"]),
    ...mapMutations(member, ["ISUPDATE"]),
    usermodify() {
      console.log("usermodify", this.isok);
      this.$router.push({ name: "MemberUpdate" });
      //  this.$router.replace({
      // name: "MemberUpdate",
      // params: { user_id: this.userInfo.user_id },
      // });
    },
    userdelete() {
      this.$router.push({ name: "MemberDelete" });
    },
  },
  // watch:{
  //   isok:function(){
  //     console.log("watch",this.isok);
  //      let token = sessionStorage.getItem("access-token");
  //      this.getUserInfo(token);
  //      this.ISUPDATE("fail");
  //   }
  // }
  watch: {
    //  console.log("watch실행");
    isok: function () {
      console.log("isok 값 바뀜");
      let token = sessionStorage.getItem("access-token");
      this.getUserInfo(token);
      this.ISUPDATE("false");
    },
  },
};
</script>

<style scoped>
#nav a {
  font-weight:normal;
    color: #f90a0a;
}
*:hover {
  -webkit-transition: all 1s ease;
  transition: all 1s ease;
}
section {
  margin:0 auto;
  float: left;
  width: 100%;
  background: #fff; /* fallback for old browsers */
  padding: 30px 0;
}
/*Profile card 2*/
.profile-card-2 .card-img-block {
  float: left;
  width: 100%;
  height: 150px;
  overflow: hidden;
}
.profile-card-2 .card-body {
  position: relative;
}
.profile-card-2 .profile {
  border-radius: 50%;
  position: absolute;
  top: -42px;
  left: 15%;
  max-width: 75px;
  border: 3px solid rgba(255, 255, 255, 1);
  -webkit-transform: translate(-50%, 0%);
  transform: translate(-50%, 0%);
}
.profile-card-2 h3 {
  font-weight: 600;
  color: #6ab04c;
}
.profile-card-2 .card-text {
  font-weight: 300;
  font-size: 15px;
}
.profile-card-2 .icon-block {
  float: left;
  width: 100%;
}
.profile-card-2 .icon-block a {
  text-decoration: none;
}
.profile-card-2 i {
  display: inline-block;
  font-size: 16px;
  color: #6ab04c;
  text-align: center;
  border: 1px solid #6ab04c;
  width: 30px;
  height: 30px;
  line-height: 30px;
  border-radius: 50%;
  margin: 0 5px;
}
.profile-card-2 i:hover {
  background-color: #6ab04c;
  color: #fff;
}
</style>
