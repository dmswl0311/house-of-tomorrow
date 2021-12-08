import http from "@/util/http-common";


async function login(user, success, fail) {
  await http.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(user_id, success, fail) {
  //http axios객체의 header에 access-tokens을 저장해서 보낸다.
  http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await http.get(`/user/info/${user_id}`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, findById };
