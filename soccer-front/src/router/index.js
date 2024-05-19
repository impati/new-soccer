import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import PlayerSearch from '../views/PlayerSearch.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/player-search',
    name: 'PlayerSearch',
    component: PlayerSearch
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
