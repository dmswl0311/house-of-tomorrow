<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <!-- <b-alert show><h3>공지사항 목록</h3></b-alert> -->
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left"> </b-col>
      <div class="p-2 bd-highlight">
        <b-input-group class="mb-3" style="padding-top:20px">
          <b-form-select v-model="option">
          <b-form-select-option value="title">글 제목</b-form-select-option>
          <b-form-select-option value="content">글 내용</b-form-select-option>
        </b-form-select>
          <b-form-input type="text" name="titles"
          id="query"
          v-model="query"></b-form-input>
          <b-input-group-append>
            <b-button size="sm" text="Button" @click="search()" variant="outline-primary"
              >검색</b-button
            >
          </b-input-group-append>
        </b-input-group>
      </div>
    </b-row>
    <b-overlay :show="show" rounded="circle">
    <b-row>
      <b-col v-if="articles.length">
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <notice-list-row
          v-for="(article, index) in articles"
          :key="index"
          v-bind="article"
        />
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    </b-overlay>
    <footer>
      <b-button
        variant="outline-primary"
        @click="moveWrite()"
        v-if="admin == 'T'"
        >공지사항 작성</b-button
      >
    </footer>
  </b-container>
</template>

<script>
import http from "@/util/http-common";
import NoticeListRow from "@/components/notice/child/NoticeListRow";
import { mapState } from "vuex";

export default {
  name: "NoticeList",
  components: {
    NoticeListRow,
  },
  data() {
    return {
      articles: [],
      option: [],
      query: "",
      show: true,
    };
  },
  computed: {
    ...mapState("member", ["isLogin", "userInfo", "admin"]),
  },
  created() {
    this.show=true;
    http.get(`/notice/`).then(({ data }) => {
      this.articles = data;
    }).finally(()=>{
      this.show=false;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
    search() {
      console.log("option", this.option);
      console.log("query", this.query);
      http({
        method: "get",
        url: "/notice/" + this.option + "/" + this.query,
      }).then(({ data }) => {
        console.log("data", data);
        this.articles = data;
      });
    },
    test() {
      this.$router.push({ name: "test" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
/* .parent{
 position: relative;
}
 
.child{
 position: absolute;
 bottom: 0;
} */
footer {
  position: fixed;
  right: -400px;
  bottom: 0px;
  height: 80px;
  width: 100%;
  color: rgba(255, 255, 255, 0);
}
</style>
