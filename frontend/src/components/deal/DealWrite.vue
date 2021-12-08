<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <!-- <b-alert show><h3>매매게시판 작성</h3></b-alert> -->
      </b-col>
    </b-row>
    <deal-write-form type="register" />
  </b-container>
</template>

<script>
import DealWriteForm from "./child/DealWriteForm.vue";

export default {
  name: "Dealwrite",
  components: {
    DealWriteForm,
  },
};
</script>

<style></style>
