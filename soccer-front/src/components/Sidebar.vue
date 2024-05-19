<template>
  <div id="sidebar" class="d-flex flex-column flex-shrink-0 p-3 bg-dark text-white" style="width: 280px;">
    <router-link to="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <i class="bi bi-bootstrap-fill me-2"></i>
      <span class="fs-4">Sidebar</span>
    </router-link>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <a href="#" class="nav-link text-white active" @click="togglePlayerManagement">
          <i class="bi bi-house-door-fill me-2"></i>
          선수 관리
        </a>
        <ul v-if="isPlayerManagementOpen" class="nav flex-column ms-3">
          <li class="nav-item">
            <router-link to="/player-search" class="nav-link text-white">
              <i class="bi bi-search me-2"></i>
              선수 검색
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/player-register" class="nav-link text-white">
              <i class="bi bi-plus-square-fill me-2"></i>
              선수 등록
            </router-link>
          </li>
        </ul>
      </li>
    </ul>
    <hr>
    <div class="dropdown">
      <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1"
         data-bs-toggle="dropdown" aria-expanded="false">
        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
        <strong>mdo</strong>
      </a>
      <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
        <li><a class="dropdown-item" href="#">Settings</a></li>
        <li><a class="dropdown-item" href="#">Profile</a></li>
        <li>
          <hr class="dropdown-divider">
        </li>
        <li><a class="dropdown-item" href="#">Sign out</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppSidebar',
  data() {
    return {
      isPlayerManagementOpen: false,
    }
  },
  methods: {
    togglePlayerManagement() {
      this.isPlayerManagementOpen = !this.isPlayerManagementOpen;
    }
  }
}
</script>

<style scoped>
#sidebar {
  height: 100vh;
}

.nav-pills .nav-link {
  cursor: pointer;
}
</style>
