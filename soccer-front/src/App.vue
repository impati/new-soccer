<template>
  <Sidebar/>
  <div id="app" class="main-content">
    <router-view/>
  </div>
</template>

<script>
import Sidebar from './components/Sidebar.vue'

export default {
  name: 'App',
  components: {
    Sidebar
  }
}
</script>

<style>
#app {
  display: flex;
  height: 100vh; /* 사이드바와 메인 콘텐츠가 전체 높이를 차지하도록 설정 */
}

.main-content {
  flex-grow: 1;
  padding: 20px;
  overflow-y: auto; /* 메인 콘텐츠가 화면을 넘어가면 스크롤 가능하도록 설정 */
}
</style>
