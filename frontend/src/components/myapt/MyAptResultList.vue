<template>
  <div class="container">
    <div style="margin-bottom:10px; margin-top:30px">
    <b-button variant="outline-success" style="margin-right:20px" @click="high">#제일 비싼 😱</b-button>
    <b-button variant="outline-success" @click="low">#제일 저렴한 😉</b-button>
    </div>
    <br />
    <center>
    <my-apt-list-row
      v-for="(apt, index) in ori_aptList"
      :key="index"
      :apt="apt"
    /></center>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import MyAptListRow from "@/components/myapt/MyAptResultListRow.vue";
export default {
  components: {
    MyAptListRow,
  },
  data() {
    return {
    };
  },
  methods: {
    ...mapActions("house",["setaptsort"]),
    high(){
        // 내림차순
        this.setaptsort([this.ori_aptList,1]);
        console.log(this.ori_aptList);
        
      },
      low(){
        // 오름차순
        this.setaptsort([this.ori_aptList,0]);
        console.log(this.ori_aptList);
      }
  },
  computed: {
    ...mapState("house", ["ori_aptList"]),
  },
};
</script>

<style></style>
