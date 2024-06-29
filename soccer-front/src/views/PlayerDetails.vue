<template>
  <div class="container mt-4" v-if="player">
    <div class="card mt-4">
      <div class="card-header d-flex justify-content-between align-items-center mb-2">
        <h2>{{ player.name }}</h2>
        <div>
          <button class="btn btn-primary me-2" @click.prevent="redirectToPlayerEdit">선수 수정</button>
          <button class="btn btn-danger" @click.prevent="deletePlayer">선수 삭제</button>
        </div>
      </div>
      <div class="card-body">
        <div class="row mb-3">
          <div class="col-12">
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                <div class="row">
                  <div class="col-2"><strong>ID:</strong></div>
                  <div class="col-10">{{ player.playerId }}</div>
                </div>
              </li>
              <li class="list-group-item">
                <div class="row">
                  <div class="col-2"><strong>Position:</strong></div>
                  <div class="col-10">
                    <span v-for="pos in player.positions" :key="pos" class="badge bg-primary me-1">
                      {{ pos }}
                    </span>
                  </div>
                </div>
              </li>
              <li class="list-group-item">
                <div class="row">
                  <div class="col-2"><strong>Main Foot:</strong></div>
                  <div class="col-10">
                    <span class="badge bg-primary me-1"> {{ player.mainFoot }} </span>
                  </div>
                </div>
              </li>
              <li class="list-group-item">
                <div class="row">
                  <div class="col-2"><strong>Rating:</strong></div>
                  <div class="col-10">{{ player.rating }}</div>
                </div>
              </li>
              <li class="list-group-item">
                <div class="row">
                  <div class="col-2"><strong>Traits:</strong></div>
                  <div class="col-10">
                    <span v-for="trait in player.traits" :key="trait" class="badge bg-secondary me-1">
                      {{ trait }}
                    </span>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <div class="row">
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Basic</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.basic" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Physical</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.physical" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Pass</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.pass" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Forward</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.forward" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Defense</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.defense" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="col-12 col-md-6 mb-3">
            <div class="stat-section card">
              <div class="card-header"><h5>Goal Keeping</h5></div>
              <table class="table table-striped mt-2">
                <tbody>
                <tr v-for="(value, key) in player.stat.goalKeeping" :key="key">
                  <td><strong>{{ translateStat(key) }}</strong></td>
                  <td>{{ value }}</td>
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
const statTranslations = {
  acceleration: "가속력",
  speed: "속력",
  physicalFight: "몸싸움",
  stamina: "체력",
  activeness: "적극성",
  jump: "점프력",
  balance: "밸런싱",
  ballControl: "볼 컨트롤",
  crosses: "크로스",
  pass: "패스",
  longPass: "롱 패스",
  dribble: "드리블",
  goalDetermination: "결정력",
  midRangeShot: "중거리슛",
  shootPower: "슛 파워",
  heading: "헤딩",
  defense: "수비력",
  tackle: "태클",
  intercepting: "가로채기",
  slidingTackle: "슬라이딩 태클",
  diving: "다이빙",
  handling: "핸들링",
  goalKick: "골킥",
  speedReaction: "반응속도",
  positioning: "위치선정",
  visualRange: "시야",
  sense: "센스"
};

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
    translateStat(key) {
      return statTranslations[key] || key;
    },
    redirectToPlayerEdit() {
      this.$router.push({name: 'PlayerEdit', params: {playerId: this.playerId}});
    },
    async deletePlayer() {
      await axios.delete(`http://localhost:8080/players/${this.playerId}`)
      await this.$router.push({name: 'PlayerSearch'})
    }
  }
};
</script>

<style scoped>
.card {
  margin-top: 20px;
}

.table {
  margin-top: 10px;
}

.list-group-item {
  border: 1px solid #ddd;
}

.stat-section {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
