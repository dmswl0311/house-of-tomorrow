<template>
  <b-container class="bv-example-row mt-3">
   <member-join-form type="modify"/>
  </b-container>
</template>
<script>
import MemberJoinForm from "./child/MemberJoinForm.vue";
export default{
    name:"MemberUpdate",
    components:{
        MemberJoinForm
    },
};

</script>
<style></style>
