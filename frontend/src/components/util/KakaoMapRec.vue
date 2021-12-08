<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  props: ["list"],
  data() {
    return {
      map: null,
      mp: [],
      markerPositions: [],
      markerPositionsCon: [],
      markers: [],
      stationmarkers: [],
      infowindow: null,
      arr: [],
    };
  },
  watch: {
    // 리스트가 변경되면 lat, lng 뽑아내기
    list: function () {
      this.markerPositions = [];
      this.markerPositionsCon = [];
      for (let index = 0; index < 10; index++) {
        let position = [];
        let positionCon = [];
        position.push(this.list[index].lat);
        position.push(this.list[index].lng);
        positionCon.push(this.list[index].clat);
        positionCon.push(this.list[index].clng);
        this.markerPositions.push(position);
        this.markerPositionsCon.push(positionCon);
      }
      this.displayMarkerCon(this.markerPositionsCon);
      this.displayMarker(this.markerPositions);
    },
  },
  created() {
    // for (let index = 0; index < 100; index++) {
    //   let position=[];
    //   position.push(this.station[index].lat);
    //   position.push(this.station[index].lng);
    //   this.stationMarkerPositions.push(position);
    // }
    console.log("created");
  },
  async mounted() {
    if (window.kakao && window.kakao.maps) {
      await this.initMap();
      //console.log("aptCoordsList 확인", this.aptCoordsList);
      //await this.displayMarker(this.aptCoordsList);
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0432be282496b485c6dc9d5b911c3352";
      document.head.appendChild(script);
    }
  },
  computed: {
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.555078, 126.970702),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.displayMarker(this.aptCoordsList);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      var imageSrc =
        // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173774.png?token=exp=1637737681~hmac=098a849448a73080738a236c7cc8248d";
        // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173841.png?token=exp=1637744954~hmac=671bb24cb7ba6f2225a733d1cb44c780";
      // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173774.png?token=exp=1637803698~hmac=e1b88f9b8e5d7a72cf133a722df4f489";
      "https://kr.seaicons.com/wp-content/uploads/2016/10/house-icon.png";
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        var imageSize = new kakao.maps.Size(30, 30);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayMarkerCon(markerPositions) {
      var imageSrc = "https://cdn-icons-png.flaticon.com/512/610/610365.png";

      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        var imageSize = new kakao.maps.Size(30, 30);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
