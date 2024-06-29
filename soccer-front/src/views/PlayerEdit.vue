<template>
  <div class="container mt-4" v-if="player">
    <div class="card mt-4">
      <div class="card-header">
        <h2>{{ player.name }}</h2>
      </div>
      <div class="card-body">
        <form @submit.prevent="updatePlayer">
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
                      <div v-for="position in positionsOptions" :key="position" class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" :id="position" :value="position"
                               v-model="player.positions">
                        <label class="form-check-label" :for="position">{{ position }}</label>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="list-group-item">
                  <div class="row">
                    <div class="col-2"><strong>Main Foot:</strong></div>
                    <div class="col-10">
                      <select class="form-control" v-model="player.mainFoot" required>
                        <option disabled value="">주발 (양발, 왼발, 오른발)</option>
                        <option value="BOTH">양발</option>
                        <option value="LEFT">왼발</option>
                        <option value="RIGHT">오른발</option>
                      </select>
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
                      <div v-for="trait in traitsOptions" :key="trait" class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" :id="trait" :value="trait"
                               v-model="player.traits">
                        <label class="form-check-label" :for="trait">{{ trait }}</label>
                      </div>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.basic[key]" min="0"
                             max="100">
                    </td>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.physical[key]" min="0"
                             max="100">
                    </td>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.pass[key]" min="0"
                             max="100">
                    </td>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.forward[key]" min="0"
                             max="100">
                    </td>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.defense[key]" min="0"
                             max="100">
                    </td>
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
                    <td class="text-end">
                      <input type="number" class="form-control text-end" v-model="player.stat.goalKeeping[key]" min="0"
                             max="100">
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="d-flex justify-content-end mt-3">
            <button type="submit" class="btn btn-primary">수정</button>
          </div>
        </form>
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
  name: 'PlayerEdit',
  data() {
    return {
      player: null,
      positionsOptions: ['GK', 'CB', 'LB', 'RB', 'CDM', 'CM', 'CAM', 'LM', 'RM', 'LW', 'RW', 'CF', 'ST'],
      traitsOptions: ['PASS_MASTER', 'HEADER', 'MAJOR', 'MINOR', 'DRIBBLE', 'KICK', 'CROSS', 'WALL', 'SPEED',
        'HEADING'],
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
      }
    },
    async updatePlayer() {
      try {
        await axios.put(`http://localhost:8080/players/${this.playerId}`, this.player);
        await this.$router.push({name: 'PlayerDetails', params: {playerId: this.playerId}});
      } catch (error) {
        console.error('선수 정보 수정 중 오류 발생:', error);
        alert('선수 정보 수정 실패!');
      }
    },
    translateStat(key) {
      return statTranslations[key] || key;
    }
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

.list-group-item {
  border: 1px solid #ddd;
}

.stat-section {
  margin-bottom: 20px;
}

.table td,
.table th {
  vertical-align: middle;
}

.table input[type="number"] {
  width: 60px;
  text-align: center;
  float: right;
}

.table .text-end {
  text-align: right;
}
</style>
