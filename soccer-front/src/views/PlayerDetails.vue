<template>
  <div class="container mt-4" v-if="player">


    <div class="container mt-4">
      <div class="card">
        <div class="card-header">
          <h2>{{ player.name }}</h2>
        </div>
        <div class="card-body">
          <div class="row mb-3">
            <div class="col-12">
              <ul class="list-group list-group-item flex-wrap">
                <li class="list-group-item flex-fill">
                  <div class="row">
                    <div class="col-2"><strong>ID:</strong></div>
                    <div class="col-10">{{ player.playerId }}</div>
                  </div>
                </li>
                <li class="list-group-item flex-fill">
                  <div class="row">
                    <div class="col-2"><strong>Position:</strong></div>
                    <div class="col-10">
                    <span v-for="pos in player.position" :key="pos" class="badge bg-primary me-1">
                      {{ pos }}
                    </span>
                    </div>
                  </div>
                </li>
                <li class="list-group-item flex-fill">
                  <div class="row">
                    <div class="col-2"><strong>Main Foot:</strong></div>
                    <div class="col-10"><span class="badge bg-primary me-1"> {{ player.mainFoot }} </span></div>
                  </div>
                </li>
                <li class="list-group-item flex-fill">
                  <div class="row">
                    <div class="col-2"><strong>Rating:</strong></div>
                    <div class="col-10">{{ player.rating }}</div>
                  </div>
                </li>
                <li class="list-group-item flex-fill">
                  <div class="row">
                    <div class="col-2"><strong>Traits:</strong></div>
                    <div class="col-10">
                    <span v-for="trait in player.trait" :key="trait" class="badge bg-secondary me-1">
                      {{ trait }}
                    </span>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="row">
            <div class="col-12">
              <strong>Stats </strong>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(chunk, index) in statChunks" :key="index">
                  <template v-for="(key, idx) in chunk" :key="idx">
                    <td><strong>{{ key }}:</strong></td>
                    <td>{{ player.stat[key] }}</td>
                  </template>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PlayerDetail',
  data() {
    return {
      player: null,
      loading: true,
    };
  },
  props: {
    playerId: {
      type: Number,
      required: true,
    },
  },
  created() {
    this.fetchPlayerData();
  },
  methods: {
    async fetchPlayerData() {
      try {
        const response = await axios.get(`http://localhost:8080/players/${this.playerId}`);
        this.player = response.data;
      } catch (error) {
        console.error('선수 데이터를 가져오는 중 오류 발생:', error);
      } finally {
        this.loading = false;
      }
    },
  },
  computed: {
    statChunks() {
      if (!this.player || !this.player.stat) {
        return [];
      }
      const stats = Object.keys(this.player.stat);
      const chunkSize = 2; // 한 행에 두 개의 키-값 쌍을 배치
      const chunks = [];
      for (let i = 0; i < stats.length; i += chunkSize) {
        chunks.push(stats.slice(i, i + chunkSize));
      }
      return chunks;
    },
  },
};
</script>

<style scoped>
.card {
  margin-top: 20px;
}

.table {
  margin-top: 10px;
}
</style>
