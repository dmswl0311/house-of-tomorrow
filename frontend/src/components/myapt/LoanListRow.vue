<template>
  <b-list-group v-b-toggle.collapse-1>
    <b-list-group-item>
      <b-avatar button variant="primary" text="대출" class="align-baseline">
      </b-avatar>
      <span style="font-weight: bold"> {{ loan.prdcType }}</span>
      <b-collapse id="collapse-1" class="mt-2">
        <b-card>
          <p class="card-text"><span v-html="loan.crdtline"></span></p>
        </b-card>
      </b-collapse>
    </b-list-group-item>
  </b-list-group>
  <!-- <b-row
    class="m-2"
  >
    <b-col cols="10" class="align-self-center">
      <h4>{{ loan.prdcType}}</h4>
    </b-col>
    <b-col cols="10" class="align-self-center" >
         <span v-html="loan.crdtline"></span>
    </b-col>
   
  </b-row> -->
</template>

<script>
export default {
  props: ["loan"],
  data() {
    return {};
  },
  methods: {},
};
</script>

<style></style>
