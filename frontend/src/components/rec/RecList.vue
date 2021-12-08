<template>
  <div style="overflow: scroll; height: 400px">
    <b-container v-if="list && list.length != 0" class="bv-example-row mt-3">
      <rec-list-row
        v-for="(apt, index) in list"
        :key="index"
        :apt="apt"
        @child="parents"
      />
      <!--  @latandlng="aptlatandlng"  @changeComp="changeComp2"-->
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>추천 아파트 목록이 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import RecListRow from "@/components/rec/RecListRow.vue";

export default {
  components: {
    RecListRow,
  },
  props: ["list"],
  data() {
    return {};
  },
  methods: {
    parents(data) {
      console.log("list가 emit받음", data);
      this.$emit("listchild", data);
    },
  },
};
</script>

<style></style>
