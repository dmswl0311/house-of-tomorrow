<template>
  <div id="app" style="font-family: 'Pretendard-Regular'">
    <div id="nav">
      <navi-bar />
      <router-view />
    </div>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/NaviBar.vue";

export default {
  name: "app",
  components: {
    NaviBar,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 0px;
}

#nav a {
  font-weight: bold;
  color: #3b3b3b;
}

#nav a.router-link-exact-active {
  color: #3b3b3b;
}
</style>
