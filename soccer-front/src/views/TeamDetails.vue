<template>


  <div class="container mt-4" v-if="team">


    <div class="container mt-4">
      <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <h2>{{ team.name }}</h2>
          <span class="badge bg-secondary">{{ team.rating }}</span>
        </div>

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

</style>
