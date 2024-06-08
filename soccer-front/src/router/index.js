import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import PlayerSearch from '../views/PlayerSearch.vue'
import PlayerDetails from "../views/PlayerDetails.vue"
import League from "../views/League.vue";

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
    path: '/player/:playerId',
    name: 'PlayerDetails',
    props: route => ({playerId: Number(route.params.playerId)}),
    component: PlayerDetails
  },

  {
    path: '/:league',
    name: 'LeaguePage',
    props: route => ({league: String(route.params.league)}),
    component: League
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
