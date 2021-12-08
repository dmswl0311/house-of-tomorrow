import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

import Notice from "@/views/Notice.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeWrite from "@/components/notice/NoticeWrite.vue";
import NoticeView from "@/components/notice/NoticeView.vue";
import NoticeUpdate from "@/components/notice/NoticeUpdate.vue";
import NoticeDelete from "@/components/notice/NoticeDelete.vue";

import Deal from "@/views/Deal.vue";
import DealList from "@/components/deal/DealList.vue";
import DealView from "@/components/deal/DealView.vue";
import DealDelete from "@/components/deal/DealDelete.vue";
import DealUpdate from "@/components/deal/DealUpdate.vue";
import DealWrite from "@/components/deal/DealWrite.vue";

import Apt from "@/components/apt/Apt.vue";

import WhereIsMyApt from "@/views/WhereIsMyApt.vue";
import MyAptResult from "@/components/myapt/MyAptResult.vue";

import BookMark from "@/views/BookMark.vue";
import BookMarkList from "@/components/bookmark/BookMarkList.vue";

import MemberJoin from "@/components/user/MemberJoin.vue";
import MemberUpdate from "@/components/user/MemberUpdate.vue";
import MemberDelete from "@/components/user/MemberDelete.vue";
import MemberMyPage from "@/components/user/MemberMyPage.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";

import Recommend from "@/views/Recommend.vue";

import House from "@/views/House.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);
// 이전, 어디, 다음 
const onlyAuthUser = async (to, from, next) => {
  // console.log(store); => 이걸 보면 store안에서 어떤걸 쓸 수 있는지 볼 수 있다. 
  const checkUserInfo = store.getters["member/checkUserInfo"]; //실제로 찍어보면 이렇게 생김 
  const getUserInfo = store._actions["member/getUserInfo"]; 
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token); //서버에 가서 정상적인 토큰인지 검사해라 
  }
  if (checkUserInfo === null) {  //서버에 갔는데도 null이면
    alert("로그인이 필요한 페이지입니다"); //로그인이 필요하다. 
    // next({ name: "SignIn" });
    router.push({ name: "MemberLogin" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};


const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    //공지사항
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/list",
    children: [
      {
        //공지사항 리스트
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        //공지사항 작성
        path: "write",
        name: "NoticeWrite",
        component: NoticeWrite,
      },
      {
        //공지사항 상세조회
        path: "detail/:notice_id",
        name: "NoticeView",
        component: NoticeView,
      },
      {
        //공지사항 업데이트
        path: "update/:notice_id",
        name: "NoticeUpdate",
        component: NoticeUpdate,
      },
      {
        //공지사항 삭제
        path: "delete/:notice_id",
        name: "NoticeDelete",
        component: NoticeDelete,
      },
      {
        //home으로 이동
        path: "/house",
        name: "House",
        component: House,
      },
    ],
  },
  {
    //매매게시판
    //매매게시판
    path: "/deal",
    name: "Deal",
    component: Deal,
    redirect: "/deal/deallist",
    children: [
      {
        //매매게시판 리스트
        path: "deallist",
        name: "DealList",
        component: DealList,
      },
      {
        //매매게시판 작성
        path: "dealwrite",
        name: "DealWrite",
        beforeEnter:onlyAuthUser,
        component: DealWrite,
      },
      {
        //매매게시판 상세조회
        path: "dealdetail/:deal_id",
        name: "DealView",
        beforeEnter:onlyAuthUser,
        component: DealView,
      },
      {
        //매매게시판 업데이트
        path: "dealupdate/:deal_id",
        name: "DealUpdate",
        beforeEnter:onlyAuthUser,
        component: DealUpdate,
      },
      {
        //매매게시판 삭제
        path: "dealdelete/:deal_id",
        name: "DealDelete",
        beforeEnter:onlyAuthUser,
        component: DealDelete,
      },
    ],
  },
  { 
    path: "/bookmark",
    name: "BookMark",
    component: BookMark,
    redirect: "/bookmark/bookmarklist",
    children: [
      {
        //매매게시판 리스트
        path: "bookmarklist",
        name: "BookMarkList",
        component: BookMarkList,
      },
    ]
  },
  {
    // 내집 마련
    path: "/wheremyapt",
    name: "WhereIsMyApt",
    component: WhereIsMyApt,
  },
  {
    // 내집 마련 결과
    path: "/wheremyapt/result",
    name: "MyAptResult",
    component: MyAptResult,
  },
  {
    //회원가입
    path: "memberJoin",
    name: "MemberJoin",
    component: MemberJoin,
  },
  {
    //회원 수정
    path: "memberUpdate",
    name: "MemberUpdate",
    component: MemberUpdate,
  },
  {
    //회원삭제
    path: "memberDelete/:user_id",
    name: "MemberDelete",
    component: MemberDelete,
  },
  {
    //내정보
    path: "memberMyPage",
    name: "MemberMyPage",
    component: MemberMyPage,
  },
  {
    //로그인
    path: "memberLogin",
    name: "MemberLogin",
    component: MemberLogin,
  },
  // 아파트 리스트 카카오맵으로
  {
    path: "/apt",
    name: "Apt",
    component: Apt,
  },
  {
    path: "/rec",
    name: "Recommend",
    beforeEnter:onlyAuthUser,
    component: Recommend,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
