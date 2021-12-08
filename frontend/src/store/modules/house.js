import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common.js";
import axios from "axios";
Vue.use(Vuex);

const house = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    ori_aptList: [],
    aptList: [],
    aptCoordsList: [],
    apt: [],
    apt_lat: 0,
    apt_lng: 0,
    moveGugunCode:0,
  },
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.aptList = [];
    },
    CLEAR_CURRENT_VIEW(state) {
      state.currentView = "AptList";
    },
    SET_APT_LIST(state, list) {
      state.aptList = list;
    },
    SET_ORI_APT_LIST(state, list) {
      state.ori_aptList = list;
    },
    SET_APT_COORDS_LIST(state, list) {
      state.aptCoordsList = list;
    },
    SET_APT(state, apt) {
      state.apt = apt;
    },
    SET_APT_COORDS(state, lat, lng) {
      state.lat = lat;
      state.lng = lng;
    },
    SET_MOVE_GUGUN_CODE(state,params){
      state.moveGugunCode=params;
    }
  },
  actions: {
    getSido({ commit }) {
      console.log("action");
      http
        .get(`/sido`)
        .then((response) => {
          console.log(response);
          commit("SET_SIDO_LIST", response.data);
        })
        .catch((error) => {
          console.log("error", error);
        });
    },
    getGugun({ commit }, sidoCode) {
      console.log("action1");
      const params = { sido: sidoCode };
      http
        .get(`/gugun`, { params })
        .then((response) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong({ commit }, gugunCode) {
      console.log("action2");
      const params = { sidogugun: gugunCode };
      http
        .get(`/dong`, { params })
        .then((response) => {
          console.log(commit, response);
          commit("SET_DONG_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 시도,군구로 검색 (아파트명 유무)
    async getAptList({ commit }, params) {
      let sidogugunCode = params[0];
      let query = params[1];

      console.log("sidogugunCode:", sidogugunCode);
      console.log("query:", query);

      await http({
        method: "get",
        url: "/api/apt",
        params: {
          ServiceKey:
            "kba1rHN2hq5naOpsikTgmDqu/bdcwFvV6SXurL37C8wVZIGGN0vwon6Hpl7dKvo8ZNjAlwIljR4Dv2CDyUePCQ==",
          pageNo: "1",
          numOfRows: "50",
          LAWD_CD: sidogugunCode,
          DEAL_YMD: "202110",
        },
      }).then((res) => {
        console.log("action 실행 후 ", res.data.response.body.items.item);
        let list = res.data.response.body.items.item;

        let list_result = [];
        let result = []; // 위치 저장할 곳

        // 아파트명 검색 위한 함수
        function cal(data) {
          return new Promise((resolve) => {
            setTimeout(() => {
              list_result.push(data);
              resolve(list_result);
            }, 1);
          });
        }

        async function forloop() {
          for (let index = 0; index < list.length; index++) {
            if (list[index].아파트.includes(query)) {
              console.log(list[index].아파트);
              await cal(list[index]);
              console.log(
                list[index].아파트 +
                  " " +
                  list[index].법정동 +
                  " " +
                  list[index].지번
              );
              let addr = list[index].법정동 + " " + list[index].지번;
              axios({
                methods: "get",
                url:
                  "https://dapi.kakao.com/v2/local/search/address.json?query=" +
                  encodeURIComponent(addr),
                headers: {
                  Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
                },
              }).then((res) => {
                // 주소 변환
                if (res != null) {
                  let lat = res.data.documents[0].road_address.x;
                  let lng = res.data.documents[0].road_address.y;
                  result.push([lng, lat]);
                }
              });
            }
          }
        }
        if (query != null) {
          // 아파트 명으로 검색
          forloop();
        } else {
          list.forEach((apt) => {
            let addr = apt.법정동 + " " + apt.지번;
            axios({
              methods: "get",
              url:
                "https://dapi.kakao.com/v2/local/search/address.json?query=" +
                encodeURIComponent(addr),
              headers: {
                Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
              },
            }).then((res) => {
              // 주소 변환
              if (res != null) {
                let lat = res.data.documents[0].road_address.x;
                let lng = res.data.documents[0].road_address.y;
                result.push([lng, lat]);
              }
            });
          });
        }
        if (query != null) {
          commit("SET_APT_LIST", list_result);
          commit("SET_ORI_APT_LIST", list_result);
        } else {
          commit("SET_APT_LIST", list);
          commit("SET_ORI_APT_LIST", list);
        }
        commit("SET_APT_COORDS_LIST", result);
        console.log("변환 결과", result);
      });
    },
    // 시도,군구, 동으로 검색 (아파트명 유무)
    async getAptDongList({ commit }, params) {
      let code = params[0];
      let query = params[1];
      let sidogugunCode = code.substr(0, 5);
      let dongCode = code.substr(5, 9);
      console.log(
        "actions sidogugunCode:" + sidogugunCode + " dongCode:" + dongCode
      );
      await http({
        method: "get",
        url: "/api/apt",
        params: {
          ServiceKey:
            "kba1rHN2hq5naOpsikTgmDqu/bdcwFvV6SXurL37C8wVZIGGN0vwon6Hpl7dKvo8ZNjAlwIljR4Dv2CDyUePCQ==",
          pageNo: "1",
          numOfRows: "50",
          LAWD_CD: sidogugunCode,
          DEAL_YMD: "202110",
        },
      }).then((res) => {
        console.log(
          "getAptDongList action 실행 후 ",
          res.data.response.body.items.item
        );
        let list = res.data.response.body.items.item;
        let list_result = [];
        let result = []; // 좌표 들어갈 list

        // 동으로 필터링
        function cal(data) {
          return new Promise((resolve) => {
            setTimeout(() => {
              list_result.push(data);
              resolve(list_result);
            }, 1);
          });
        }
        async function forloop() {
          for (let index = 0; index < list.length; index++) {
            if (list[index].법정동읍면동코드 == dongCode) {
              if (query == null) {
                await cal(list[index]);
                let addr = list[index].법정동 + " " + list[index].지번;
                axios({
                  methods: "get",
                  url:
                    "https://dapi.kakao.com/v2/local/search/address.json?query=" +
                    encodeURIComponent(addr),
                  headers: {
                    Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
                  },
                }).then((res) => {
                  // 주소 변환
                  if (res != null) {
                    let lat = res.data.documents[0].road_address.x;
                    let lng = res.data.documents[0].road_address.y;
                    result.push([lng, lat]);
                  }
                });
              } else {
                if (list[index].아파트.includes(query)) {
                  await cal(list[index]);
                  let addr = list[index].법정동 + " " + list[index].지번;
                  axios({
                    methods: "get",
                    url:
                      "https://dapi.kakao.com/v2/local/search/address.json?query=" +
                      encodeURIComponent(addr),
                    headers: {
                      Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
                    },
                  }).then((res) => {
                    // 주소 변환
                    if (res != null) {
                      let lat = res.data.documents[0].road_address.x;
                      let lng = res.data.documents[0].road_address.y;
                      result.push([lng, lat]);
                    }
                  });
                }
              }
            }
          }
        }
        forloop();

        console.log("동으로 거른 list", list_result);
        console.log("변환 결과", result);
        commit("SET_APT_LIST", list_result);
        commit("SET_ORI_APT_LIST", list_result);
        commit("SET_APT_COORDS_LIST", result);
      });
    },
    async getAptDetail({ commit }, apt) {
      commit("SET_APT", apt);
    },
    // 아파트 필터링 결과
    selectFilterAction({ commit }, params) {
      let floor = params[0];
      let price_list = params[1];
      let list = [];
      let size = params[2];
      let price = params[3];
      let apt = [];
      let result = [];
      // 가격으로 한번 거름
      console.log("받아온 price", price);

      for (let index = 0; index < price_list.length; index++) {
        //console.log(price_list[index].거래금액.trim().replace(",", ""));
        let ori = price_list[index].거래금액.trim().replace(",", "");
        console.log(
          "ori vs 입력받은 price",
          parseInt(ori) * 10000,
          parseInt(price)
        );
        if (parseInt(ori) * 10000 <= parseInt(price)) {
          list.push(price_list[index]);
        }
      }
      console.log("만들어진 list");

      if (floor != null && size == null) {
        for (let index = 0; index < list.length; index++) {
          if (
            parseInt(floor) <= parseInt(list[index].층) &&
            parseInt(list[index].층) <= parseInt(floor) + 10
          ) {
            apt.push(list[index]);
            coords(index);
          }
        }
      } else if (floor != null && size != null) {
        for (let index = 0; index < list.length; index++) {
          if (
            parseInt(floor) <= parseInt(list[index].층) &&
            parseInt(list[index].층) <= parseInt(floor) + 10
          ) {
            if (
              parseInt(size) * 3.3058 <= parseInt(list[index].전용면적) &&
              parseInt(list[index].전용면적) <= (parseInt(size) + 10) * 3.3058
            ) {
              apt.push(list[index]);
              coords(index);
            }
          }
        }
      } else if (floor == null && size != null) {
        for (let index = 0; index < list.length; index++) {
          if (
            parseInt(size) * 3.3058 <= parseInt(list[index].전용면적) &&
            parseInt(list[index].전용면적) <= (parseInt(size) + 10) * 3.3058
          ) {
            apt.push(list[index]);
            coords(index);
          }
        }
      } else if (floor == null && size == null) {
        for (let index = 0; index < list.length; index++) {
          apt.push(list[index]);
          coords(index);
        }
      }

      function coords(index) {
        let addr = list[index].법정동 + " " + list[index].지번;
        axios({
          methods: "get",
          url:
            "https://dapi.kakao.com/v2/local/search/address.json?query=" +
            encodeURIComponent(addr),
          headers: {
            Authorization: "KakaoAK c420902bf013e6a215efef159a46af41",
          },
        }).then((res) => {
          // 주소 변환
          if (res != null) {
            let re = [];
            let lat = res.data.documents[0].road_address.y;
            let lng = res.data.documents[0].road_address.x;
            re.push(lat);
            re.push(lng);
            result.push(re);
          }
        });
      }
      console.log("필터에 맞는 apt in actions", apt);
      commit("SET_APT_LIST", apt);
      commit("SET_APT_COORDS_LIST", result);
    },
    setaptsort({ commit }, params) {
      let list = params[0];
      let flag = params[1]; // 오름차순, 내림차순 여부
      let list_result = [];
      if (flag == 1) {
        list_result = list.sort(function (a, b) {
          return (
            parseInt(b.거래금액.trim().replace(",", "")) -
            parseInt(a.거래금액.trim().replace(",", ""))
          );
        });
        
      } else {
        //오름치순
        list_result = list.sort(function (a, b) {
          return (
            parseInt(a.거래금액.trim().replace(",", "")) -
            parseInt(b.거래금액.trim().replace(",", ""))
          );
        });
      }
      commit("SET_ORI_APT_LIST", list_result);
    },
  },
  modules: {},
};
export default house;
