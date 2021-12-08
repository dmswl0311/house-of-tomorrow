<template>
  <b-container>
    <div style="max-height:600px; margin-top:90px">
      <div class="mt-2">가격: {{ value| price }}만원</div>
      <b-form-input
        id="range-2"
        v-model="value"
        @change="selectFilter"
        type="range"
        min="0"
        max="5000000000"
        step="100000000"
      ></b-form-input>
    </div>
    층수
    <b-form-group v-slot="{ ariaDescribedby }">
      <b-form-radio-group
        id="btn-radios-1"
        @change="selectFilter"
        v-model="selected"
        :options="options"
        :aria-describedby="ariaDescribedby"
        name="radios-btn-default"
        buttons
        stacked
      ></b-form-radio-group>
    </b-form-group>
    평수
    <b-form-group v-slot="{ ariaDescribedby }">
      <b-form-radio-group
        id="btn-radios-1"
        @change="selectFilter"
        v-model="selected2"
        :options="options2"
        :aria-describedby="ariaDescribedby"
        name="radios-btn-default1"
        stacked
        buttons
      ></b-form-radio-group>
    </b-form-group>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
const house = "house";

export default {
  data() {
    return {
      value: 3000000000,
      selected: null,
      options: [
        { text: "~ 10층", value: "1" },
        { text: "~ 20층", value: "10" },
        { text: "21층 ~", value: "21" },
      ],
      selected2: null,
      options2: [
        { text: "10평대", value: "1" },
        { text: "20평대", value: "20" },
        { text: "30평대", value: "30" },
        { text: "40평대", value: "40" },
        { text: "50평 이상", value: "51" },
      ],
    };
  },
  computed: {
    ...mapState(house, ["ori_aptList", "aptList"]),
  },
  methods: {
    // ...mapMutations(house, [
    //   "CLEAR_SIDO_LIST",
    //   "CLEAR_GUGUN_LIST",
    //   "CLEAR_DONG_LIST",
    // ]),
    ...mapActions(house, ["selectFilterAction", "selectFilterSizeAction"]),
    selectFilter() {
      console.log(this.selected);
      this.selectFilterAction([
        this.selected,
        this.ori_aptList,
        this.selected2,
        this.value,
      ]);
    },
  },
   filters: {
    price(v) {
      if (!v) return v;
      return (v/10000).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
