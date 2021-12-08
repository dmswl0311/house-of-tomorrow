<template>
  <div>
    <b-card no-body style="max-width:380px; height:400px">
      <b-card-body>
        <b-card-title><h2>{{ clickdata.aptName }}</h2></b-card-title>
        <b-card-sub-title class="mb-2"><h4>{{
          clickdata.recentPrice
        }} 만원</h4></b-card-sub-title>
      </b-card-body>

      <b-list-group flush>
          <b-list-group-item>
          선호 구역 {{ clickdata.total }} 개</b-list-group-item
        >
        <b-list-group-item
          >준공년도 : {{ clickdata.buildYear }}년</b-list-group-item
        >
        <b-list-group-item>층수 : {{ clickdata.floor }}층</b-list-group-item>
        <b-list-group-item
          >면적 : {{
            Math.ceil(parseInt(clickdata.area) / 3.3058)
          }}평</b-list-group-item
        >
        <b-list-group-item
          ><b-button variant="outline-primary" @click="back"
            >뒤로가기</b-button
          ></b-list-group-item
        >
      </b-list-group>
    </b-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [],
    };
  },
  props: ["clickdata"],
  methods: {
    back() {
      console.log("back버튼 누름");
      this.$emit("backBtn");
    },
  },
};
</script>

<style></style>
