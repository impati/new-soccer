<template>
  <div class="container mt-4">
    <div class="league-page d-flex">
      <div class="content flex-grow-1 p-3 bg-light text-dark">
        <div class="league-title">
          <h1>{{ leagueName }}</h1>
        </div>
        <table class="table table-striped table-bordered">
          <thead>
          <tr>
            <th scope="col">순위</th>
            <th scope="col">팀 이름</th>
            <th scope="col">레이팅</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(team, index) in sortedTeams" :key="team.name">
            <td>{{ index + 1 }}</td>
            <td>{{ team.name }}</td>
            <td>{{ team.rating }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LeaguePage',
  data() {
    return {
      teams: [],
      leagueName: ""
    };
  },
  props: {
    league: {
      type: String,
      required: true,
    },
  },
  computed: {
    sortedTeams() {
      return this.teams.slice().sort((a, b) => b.rating - a.rating);
    },
  },
  methods: {
    fetchLeagueData() {
      axios.get(`http://localhost:8080/leagues/${this.league}`)
      .then(response => {
        this.teams = response.data;
        this.leagueName = response.data.length > 0 ? response.data[0].league.leagueName : this.league;
      })
      .catch(error => {
        console.error('There was an error fetching the teams:', error);
      });
    }
  },
  watch: {
    league() {
      this.fetchLeagueData();
    }
  },
  mounted() {
    this.fetchLeagueData();
  },
};
</script>

<style scoped>
.league-page {
  display: flex;
}

.content {
  padding: 1.5rem;
  background-color: #f8f9fa; /* Light background */
  color: #212529; /* Dark text */
  width: 100%;
}

.league-title {
  margin-bottom: 2rem;
}

.table {
  width: 100%;
  margin-bottom: 1rem;
  color: #212529;
  border: 1px solid #dee2e6;
}

.table thead th {
  border-bottom: 2px solid #dee2e6;
}

.table tbody tr td {
  border-top: 1px solid #dee2e6;
}

.table-striped tbody tr:nth-of-type(odd) {
  background-color: rgba(0, 0, 0, 0.05);
}

.table-bordered {
  border: 1px solid #dee2e6;
}

.table-bordered th,
.table-bordered td {
  border: 1px solid #dee2e6;
}
</style>
