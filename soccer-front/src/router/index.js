import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import PlayerSearch from '../views/PlayerSearch.vue'
import PlayerDetails from "../views/PlayerDetails.vue";

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
  {
    path: '/player/:id',
    name: 'PlayerDetails',
    component: PlayerDetails
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
