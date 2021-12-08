<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <!-- <b-alert show><h3>게시글 수정</h3></b-alert> -->
      </b-col>
    </b-row>
    <deal-write-form type="modify" />
  </b-container>
</template>

<script>
import DealWriteForm from "@/components/deal/child/DealWriteForm";

export default {
  name: "DealUpdate",
  components: {
    DealWriteForm,
  },
};
</script>

<style></style>
