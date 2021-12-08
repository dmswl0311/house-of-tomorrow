<template>
  <div class="container" style="font-family: 'Pretendard-Regular'">
    <h1
      style="
        font-family: 'NEXON Lv2 Gothic Bold';
        margin-bottom: 20px;
        margin-top: 50px;
      "
    >
      찜 목록
    </h1>
    <br />
    <b-container fluid>
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
// import BookMarkList from "@/components/bookmark/BookMarkList.vue";
export default {
  name: "BookMark",
  //   components: { BookMarkList },
  data() {
    return {};
  },
};
</script>

<style></style>
