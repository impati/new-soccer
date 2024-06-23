import {createRouter, createWebHistory} from 'vue-router'
import Home from '../views/Home.vue'
import PlayerSearch from '../views/PlayerSearch.vue'
import PlayerDetails from "../views/PlayerDetails.vue"
import League from "../views/League.vue";
import TeamDetails from "@/views/TeamDetails.vue";
import PlayerRegister from "@/views/PlayerRegister.vue";

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
    path: '/player-register',
    name: 'PlayerRegister',
    component: PlayerRegister
  },

  {
    path: '/players/:playerId',
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

  {
    path: '/teams/:teamId',
    name: "TeamDetails",
    props: route => ({teamId: Number(route.params.teamId)}),
    component: TeamDetails
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
