<template>
  <div>
    <b-row style="margin-left: 0px;">
        <h4 style="margin-right:10px">
          <b-badge
            v-b-toggle.collapse-1
            variant="primary"
            @click="
              selectFilter(
                'SW8','https://cdn-icons.flaticon.com/png/512/3660/premium/3660432.png?token=exp=1637827583~hmac=c9a6feb6ec4caa493e484dbeb5b591f9'
                
              )
            "
            style="float: left"
            >+지하철</b-badge
          >
        </h4>
        <h4 style="margin-right:10px">
          <b-badge
            variant="primary"
            @click="
              selectFilter(
                'HP8',
                'https://cdn-icons-png.flaticon.com/512/4006/4006511.png'
              )
            "
            style="float: left"
            >+병원</b-badge
          >
        </h4>
        <h4 style="margin-right:10px">
          <b-badge
            variant="primary"
            @click="
              selectFilter(
                'CE7',
                'https://cdn-icons-png.flaticon.com/512/2578/2578645.png'
              )
            "
            style="float: left"
            >+카페</b-badge
          >
        </h4>
        <h4 style="margin-right:10px">
          <b-badge
            variant="primary"
            @click="
              selectFilter(
                'CS2',
                'https://cdn-icons-png.flaticon.com/512/4091/4091998.png'
              )
            "
            style="float: left"
            >+편의점</b-badge
          >
        </h4>
    </b-row>
    <div id="map" style="margin-top: 10px"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import stationList from "@/assets/station_coordinate.json";

export default {
  name: "KakaoMap",
  data() {
    return {
      station: stationList,
      stationMarkerPositions: [],
      map: null,
      mp: [],
      markerPositions1: [],
      markerPositionshospital: [],
      markerPositionSubway: [],
      markers: [],
      stationmarkers: [],
      infowindow: null,
      arr: [],
      flag: "T",
      flag_hos: "T",
      value: 1,
      places: null,
      // markerPositions2: [
      //   [37.499590490909185, 127.0263723554437],
      //   [37.499427948430814, 127.02794423197847],
      //   [37.498553760499505, 127.02882598822454],
      //   [37.497625593121384, 127.02935713582038],
      //   [37.49629291770947, 127.02587362608637],
      //   [37.49754540521486, 127.02546694890695],
      //   [37.49646391248451, 127.02675574250912],
      // ],
    };
  },
  watch: {
    aptCoordsList: function () {
      console.log("aptCoordsList 바뀜");
      // 아파트 위치 찍기
      this.displayMarker(this.aptCoordsList);
    },
  },
  created() {
    for (let index = 0; index < 100; index++) {
      let position = [];
      position.push(this.station[index].lat);
      position.push(this.station[index].lng);
      this.stationMarkerPositions.push(position);
    }
    // let code = this.moveGugunCode.substring(0, 2) + "0000";
    // axios({
    //   methods: "get",
    //   url: "http://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1",
    //   params: {
    //     ServiceKey:
    //       "kba1rHN2hq5naOpsikTgmDqu/bdcwFvV6SXurL37C8wVZIGGN0vwon6Hpl7dKvo8ZNjAlwIljR4Dv2CDyUePCQ==",
    //     pageNo: "1",
    //     numOfRows: "100",
    //     sidoCd: code,
    //     _type: "json",
    //   },
    // }).then((res) => {
    //   console.log("병원 찍기", res.data.response.body.items.item);
    //   let arr = res.data.response.body.items.item;
    //   for (let index = 0; index < arr.length; index++) {
    //     let a = [];
    //     a.push(parseFloat(arr[index].YPos));
    //     a.push(parseFloat(arr[index].XPos));
    //     this.markerPositionshospital.push(a);
    //   }
    //   console.log("만들어진 병원 좌표", this.markerPositionshospital);
    // });
  },
  async mounted() {
    if (window.kakao && window.kakao.maps) {
      await this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0432be282496b485c6dc9d5b911c3352&libraries=services";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState("house", ["aptList", "aptCoordsList", "moveGugunCode"]),
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
      // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173774.png?token=exp=1637803698~hmac=e1b88f9b8e5d7a72cf133a722df4f489";
        // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173774.png?token=exp=1637737681~hmac=098a849448a73080738a236c7cc8248d";
        // "https://cdn-icons.flaticon.com/png/512/2173/premium/2173841.png?token=exp=1637744954~hmac=671bb24cb7ba6f2225a733d1cb44c780";
        "https://kr.seaicons.com/wp-content/uploads/2016/10/house-icon.png";
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        var imageSize = new kakao.maps.Size(35, 35);
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
    // 지하철,카페 등 마커 표시
    displayMarker2(markerPositions, url) {
      var imageSrc = url;

      console.log(this.flag);
      if (this.flag == "F") {
        this.flag = "T";
        this.displayMarker([]);
        return;
      } else {
        this.flag = "F";
      }
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        var imageSize = new kakao.maps.Size(32, 32);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            })
        );
      }
    },
    // 병원
    displayMarkerHos(markerPositions, url) {
      var imageSrc = url;

      console.log(this.flag_hos);
      if (this.flag_hos == "F") {
        this.flag_hos = "T";
        this.displayMarker([]);
        return;
      } else {
        this.flag_hos = "F";
      }
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        var imageSize = new kakao.maps.Size(25, 25);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            })
        );
      }
    },
    selectFilter(query, url) {
      console.log("query", query);
      this.markerPositionSubway = [];
      this.places = new kakao.maps.services.Places();
      this.places.setMap(this.map);
      let that = this;
      var callback = function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          console.log("result :", result);
          for (let index = 0; index < result.length; index++) {
            let arr = [];
            arr.push(result[index].y);
            arr.push(result[index].x);
            that.markerPositionSubway.push(arr);
          }
          that.displayMarker2(that.markerPositionSubway, url);
        }
      };
      // 지하철 코드 검색
      this.places.categorySearch(query, callback, {
        // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
        location: new kakao.maps.LatLng(37.564968, 126.939909),
        useMapCenter: true,
        useMapBounds: true,
      });
    },
    // displayInfoWindow() {
    //   if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    //   }

    //   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    //   this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    //   });

    //   this.map.setCenter(iwPosition);
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 550px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
