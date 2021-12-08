<template>
  <div style="font-family: 'Pretendard-Regular'">
    <br />
    <h1
      style="
        font-family: 'NEXON Lv2 Gothic Bold';
        margin-bottom: 10px;
        margin-top: 30px;
      "
    >
      나는 언제쯤 집을 살 수 있을까?
    </h1>
    <h5>
      나이 : <b-badge variant="success">{{ age }}세 </b-badge> 한달 저축액:
      <b-badge variant="success">{{ month }}만원 </b-badge> 현재 저축액:
      <b-badge variant="success">{{ balance }}만원 </b-badge>
    </h5>

    <my-apt-list></my-apt-list>

    <footer>
      <div class="text-center">
        <b-button v-b-modal.modal-1 variant="primary" @click="loanModal">
          대출 정보 보기! <b-badge variant="light">new</b-badge>
        </b-button>
      </div>
    </footer>
    <b-modal id="modal-1" title="대출정보가 궁금하신가요?">
      <loan-list-row v-for="(loan, index) in list" :key="index" :loan="loan" />
    </b-modal>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import http from "@/util/http-common.js";
import axios from "axios";
import MyAptList from "@/components/myapt/MyAptResultList.vue";
import LoanListRow from "@/components/myapt/LoanListRow.vue";
const myapt = "myapt";

export default {
  components: {
    MyAptList,
    LoanListRow,
  },
  data() {
    return {
      list: [],
    };
  },
  methods: {
    loanModal() {
      console.log("modal");
      // 대출정보 불러오기
      axios({
        method: "get",
        url: "http://apis.data.go.kr/B553701/ProductAttribute/getProductAttribute",
        params: {
          ServiceKey:
            "kba1rHN2hq5naOpsikTgmDqu/bdcwFvV6SXurL37C8wVZIGGN0vwon6Hpl7dKvo8ZNjAlwIljR4Dv2CDyUePCQ==",
          pageNo: "1",
          numOfRows: "50",
          type: "json",
        },
      }).then((res) => {
        console.log("결과: ", res.data.response.body.items.item);
        this.list = res.data.response.body.items.item;
      });
    },
  },
  computed: {
    ...mapState(myapt, ["age", "month", "balance", "gugunCode"]),
  },
};
</script>

<style>
footer {
  position: fixed;
  right: -550px;
  bottom: 0px;
  height: 80px;
  width: 100%;
  color: rgba(255, 255, 255, 0);
}
@font-face {
  font-family: "NEXON Lv2 Gothic Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv2 Gothic Bold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Pretendard-Regular";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff")
    format("woff");
  font-weight: 400;
  font-style: normal;
}
</style>
