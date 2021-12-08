<template>
  <b-row
    class="m-3"
    @click="selectApt"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="8" class="align-self-center">
      {{ apt.aptName }}
    </b-col>
    <b-col cols="2" class="align-self-center">
      <b-badge variant="success">{{ apt.total }}개</b-badge>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "RecListRow",
  data() {
    return {
      isColor: false,
      lat: Object,
      lng: Object,
      data: [],
    };
  },
  props: {
    apt: Object,
  },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
    },
    selectApt() {
      console.log("row click");
      this.$emit("child", this.apt);
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgba(0, 153, 255, 0.294);
  font-weight: bold;
}
</style>
