<template>
  <Sidebar/>
  <div id="app" class="container-fluid">
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
}

.main-content {
  flex-grow: 1;
  padding: 20px;
}
</style>
