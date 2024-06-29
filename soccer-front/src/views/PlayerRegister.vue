<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <!-- Registration Form -->
        <div class="bg-light p-5 rounded shadow-sm">
          <div class="mb-4 card-header">
            <h2>선수 등록</h2>
          </div>
          <div class="card-body">
            <form @submit.prevent="registerPlayer">
              <div class="form-group row mb-3">
                <label for="playerName" class="col-sm-2 col-form-label">선수명 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="playerName" v-model="player.name" placeholder="선수명"
                         required>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label class="col-sm-2 col-form-label">포지션 :</label>
                <div class="col-sm-10">
                  <div v-for="position in positionsOptions" :key="position" class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" :id="position" :value="position"
                           v-model="player.positions">
                    <label class="form-check-label" :for="position">{{ position }}</label>
                  </div>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label class="col-sm-2 col-form-label">특성 :</label>
                <div class="col-sm-10">
                  <div v-for="trait in traitsOptions" :key="trait" class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" :id="trait" :value="trait" v-model="player.traits">
                    <label class="form-check-label" :for="trait">{{ trait }}</label>
                  </div>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label for="mainFoot" class="col-sm-2 col-form-label">주발 :</label>
                <div class="col-sm-10">
                  <select class="form-control" id="mainFoot" v-model="player.mainFoot" required>
                    <option disabled value="">주발 (양발, 왼발, 오른발)</option>
                    <option value="BOTH">양발</option>
                    <option value="LEFT">왼발</option>
                    <option value="RIGHT">오른발</option>
                  </select>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label class="col-sm-2 col-form-label">스텟 입력</label>
              </div>

              <div class="form-group row mb-3">
                <div class="col-12 col-md-6 mb-3">
                  <div class="stat-section card">
                    <div class="card-header"><h5>기본</h5></div>
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
                    <div class="card-header"><h5>피지컬</h5></div>
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
                    <div class="card-header"><h5>패스</h5></div>
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
                    <div class="card-header"><h5>공격</h5></div>
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
                    <div class="card-header"><h5>수비</h5></div>
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
                    <div class="card-header"><h5>골키핑</h5></div>
                    <table class="table table-striped mt-2">
                      <tbody>
                      <tr v-for="(value, key) in player.stat.goalKeeping" :key="key">
                        <td><strong>{{ translateStat(key) }}</strong></td>
                        <td class="text-end">
                          <input type="number" class="form-control text-end" v-model="player.stat.goalKeeping[key]"
                                 min="0" max="100">
                        </td>
                      </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

              <div class="d-flex justify-content-end mt-3">
                <button type="submit" class="btn btn-primary">등록</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PlayerRegistration',
  data() {
    return {
      player: {
        name: '',
        positions: [],
        traits: [],
        mainFoot: '',
        stat: {
          basic: {
            positioning: 0,
            visualRange: 0,
            sense: 0
          },
          physical: {
            acceleration: 0,
            speed: 0,
            physicalFight: 0,
            stamina: 0,
            activeness: 0,
            jump: 0,
            balance: 0
          },
          pass: {
            ballControl: 0,
            crosses: 0,
            pass: 0,
            longPass: 0
          },
          forward: {
            dribble: 0,
            goalDetermination: 0,
            midRangeShot: 0,
            shootPower: 0,
            heading: 0
          },
          defense: {
            defense: 0,
            tackle: 0,
            intercepting: 0,
            slidingTackle: 0
          },
          goalKeeping: {
            diving: 0,
            handling: 0,
            goalKick: 0,
            speedReaction: 0
          }
        }
      },
      positionsOptions: ['GK', 'CB', 'LB', 'RB', 'CDM', 'CM', 'CAM', 'LM', 'RM', 'LW', 'RW', 'CF', 'ST'], // Example positions
      traitsOptions: ['PASS_MASTER', 'HEADER', 'MAJOR', 'MINOR', 'DRIBBLE', 'KICK', 'CROSS', 'WALL', 'SPEED',
        'HEADING'], // Example traits
    }
  },
  methods: {
    async registerPlayer() {
      try {
        console.log('호출');
        const response = await axios.post('http://localhost:8080/players', this.player);
        await this.$router.push({name: 'PlayerDetails', params: {playerId: response.data.playerId}});
      } catch (error) {
        console.error('Error registering player:', error);
        alert('선수 등록 실패!');
      }
    },
    translateStat(key) {
      const translations = {
        positioning: '위치선정',
        visualRange: '시야',
        sense: '센스',
        acceleration: '가속력',
        speed: '속력',
        physicalFight: '몸싸움',
        stamina: '체력',
        activeness: '적극성',
        jump: '점프력',
        balance: '밸런스',
        ballControl: '볼 컨트롤',
        crosses: '크로스',
        pass: '패스',
        longPass: '롱 패스',
        dribble: '드리블',
        goalDetermination: '결정력',
        midRangeShot: '중거리슛',
        shootPower: '슛 파워',
        heading: '헤딩',
        defense: '수비력',
        tackle: '태클',
        intercepting: '가로채기',
        slidingTackle: '슬라이딩 태클',
        diving: '다이빙',
        handling: '핸들링',
        goalKick: '골킥',
        speedReaction: '반응속도'
      };
      return translations[key] || key;
    }
  }
}
</script>

<style scoped>
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
