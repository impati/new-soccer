<template>
  <div class="container mt-4" v-if="team">

    <!-- team Info -->
    <div class="card team-info mb-4">
      <div class="card-header d-flex justify-content-between align-items-center">
        <h2>{{ team.name }}</h2>
        <span class="badge bg-secondary">{{ team.rating }}</span>
      </div>
    </div>

    <hr>
    <!-- Player Lists -->
    <div><h4>선수 목록</h4></div>
    <div class="card player-lists shadow-sm">

      <div class="card-body">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>선수 이름</th>
            <th>포지션</th>
            <th>특성</th>
            <th>주발</th>
            <th>레이팅</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="player in team.players" :key="player.playerId" class="cursor-pointer">
            <td>
              <a @click.prevent="redirectToPlayerDetails(player.playerId)">{{ player.name }}</a>
            </td>
            <td>{{ player.position.join(', ') }}</td>
            <td>{{ player.trait.join(', ') }}</td>
            <td>{{ player.mainFoot }}</td>
            <td>{{ player.rating }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'TeamDetails',
  data() {
    return {
      team: null,
      loading: true,
    };
  },
  props: {
    teamId: {
      type: Number,
      required: true,
    },
  },

  created() {
    this.fetchTeamData();
  },
  methods: {
    redirectToPlayerDetails(playerId) {
      this.$router.push({name: 'PlayerDetails', params: {playerId: playerId}});
    },
    async fetchTeamData() {
      try {
        const response = await axios.get(`http://localhost:8080/teams/${this.teamId}`);
        this.team = response.data;
      } catch (error) {
        console.error('팀 데이터를 가져오는 중 오류 발생:', error);
      } finally {
        this.loading = false;
      }
    }
  },
}
</script>

<style scoped>
.team-info {
  margin-bottom: 20px; /* 공간을 추가합니다 */
}

.player-lists {
  margin-top: 20px; /* 필요에 따라 추가 공간을 설정합니다 */
}

/* 두 카드의 가로 길이를 동일하게 설정합니다 */
.team-info, .player-lists {
  width: 100%;
}
</style>
