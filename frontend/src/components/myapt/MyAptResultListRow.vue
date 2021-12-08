<template>
  <div class="text-center">
    <b-card
      no-body
      class="overflow-hidden"
      style="max-width: 800px"
      align="center"
    >
      <b-row no-gutters>
        <b-col md="6">
          <b-card-body :title="apt.아파트">
            <b-card-title> {{ apt.거래금액 }}만원</b-card-title>
            <b-list-group flush>
              <b-list-group-item>
                <b-card-img
                  src="https://img.hankyung.com/photo/201908/AA.20284290.1.jpg"
                  alt="Image"
                  class="rounded-0"
                ></b-card-img>
              </b-list-group-item>
              <b-list-group-item
                >평수 :
                {{ Math.ceil(apt.전용면적 / 3.3058) }} 평</b-list-group-item
              >
              <b-list-group-item>층수 : {{ apt.층 }}</b-list-group-item>
              <b-list-group-item
                >건축 년도 : {{ apt.건축년도 }}</b-list-group-item
              >
            </b-list-group>
          </b-card-body>
        </b-col>
        <b-col md="6">
          <b-card-body title=" ">
            <b-card-title> </b-card-title>
            <b-list-group flush><br /><br /><br /><br /><br /></b-list-group>
            <b-list-group flush>
              <b-alert show variant="success"
                >필요한 금액은? : {{ need }} 만원</b-alert
              ><b-alert show variant="success"
                >약 {{ Math.ceil(result_age) }}살에 살수있어요!</b-alert
              ><b-alert show variant="success"
                >월{{ month }}만원 저축으로 약{{ Math.ceil(result_year) }}년
                걸려요</b-alert
              >
            </b-list-group>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
    <br /><br />
  </div>
</template>

<script>
import { mapState } from "vuex";
//import axios from "axios";

export default {
  data() {
    return {
      need: null,
      result_age: null,
      result_year: null,
    };
  },
  props: ["apt"],
  computed: {
    ...mapState("myapt", [
      "age",
      "month",
      "balance",
      "gugunCode",
      "aptCoordsList",
    ]),
  },
  created() {
    this.need =
      parseInt(this.apt.거래금액.trim().replace(",", "")) -
      parseInt(this.balance);
    this.result_year =
      parseInt(this.need) / parseInt(parseInt(this.month) * 12);
    this.result_age = parseInt(this.age) + parseInt(this.result_year);
  },
  watch: {
    apt: function () {
      this.need =
        parseInt(this.apt.거래금액.trim().replace(",", "")) -
        parseInt(this.balance);
      this.result_year =
        parseInt(this.need) / parseInt(parseInt(this.month) * 12);
      this.result_age = parseInt(this.age) + parseInt(this.result_year);
    },
  },
};
</script>

<style scoped>
.text-center{
  margin: 0 auto;
}
</style>
