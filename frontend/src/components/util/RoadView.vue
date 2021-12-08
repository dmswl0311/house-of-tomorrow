<template>
  <div>
    <div id="roadview"></div>
  </div>
</template>

<script>
export default {
  name: "RoadView",
  props: ["data"],
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.init);
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0432be282496b485c6dc9d5b911c3352";
    document.head.appendChild(script);
  },
  methods: {
    init() {
      console.log("props 위치정보",this.data[0]);
      const roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      //var position = new kakao.maps.LatLng(33.450701, 126.570667);
      var position = new kakao.maps.LatLng(this.data[0][0], this.data[0][1]);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 150, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
  },
};
</script>

<style>
#roadview {
  width: 100%;
  height: 350px;
}
</style>
