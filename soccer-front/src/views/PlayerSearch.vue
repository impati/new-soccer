<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-lg-10">

        <!-- Search Form -->
        <div class="bg-light p-5 rounded shadow-sm">
          <div class="mb-4">
            <h2>선수 검색</h2>
          </div>
          <div class="card-body">
            <form @submit.prevent="searchPlayers">

              <div class="form-group row mb-3">
                <label for="playerName" class="col-sm-2 col-form-label">선수명 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="playerName" v-model="searchCriteria.name"
                         placeholder="선수명">
                </div>
              </div>

              <div class="form-group row mb-3">
                <label for="teamName" class="col-sm-2 col-form-label">팀명 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="teamName" v-model="searchCriteria.teamName"
                         placeholder="팀명">
                </div>
              </div>

              <div class="form-group row mb-3">
                <label for="mainFoot" class="col-sm-2 col-form-label">주발 :</label>
                <div class="col-sm-10">
                  <select class="form-control" id="mainFoot" v-model="searchCriteria.mainFoot">
                    <option value="">주발</option>
                    <option value="LEFT">왼발</option>
                    <option value="RIGHT">오른발</option>
                    <option value="BOTH">양발</option>
                  </select>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label class="col-sm-2 col-form-label">특성 :</label>
                <div class="col-sm-10">
                  <div v-for="trait in traitsOptions" :key="trait.value" class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" :id="trait.value" :value="trait.value"
                           v-model="searchCriteria.traits">
                    <label class="form-check-label" :for="trait.value">{{ trait.label }}</label>
                  </div>
                </div>
              </div>

              <div class="form-group row mb-3">
                <label for="position" class="col-sm-2 col-form-label">포지션 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="position" v-model="searchCriteria.position"
                         placeholder="포지션">
                </div>
              </div>

              <div class="form-group row mb-3">
                <label for="ratingValue" class="col-sm-2 col-form-label">레이팅 :</label>
                <div class="col-sm-10 d-flex align-items-center">
                  <input type="number" class="form-control me-2" id="ratingValue" v-model="searchCriteria.rating.value"
                         placeholder="레이팅">
                  <select class="form-control me-2" id="ratingOperator" v-model="searchCriteria.rating.operator">
                    <option value="">비교</option>
                    <option value="LESS_THAN">미만</option>
                    <option value="LESS_THAN_EQUAL">이하</option>
                    <option value="EQUAL">같음</option>
                    <option value="MORE_THAN_EQUAL">이상</option>
                    <option value="MORE_THAN">초과</option>
                  </select>
                  <select class="form-control" id="ratingOrderBy" v-model="searchCriteria.rating.orderBy">
                    <option value="">정렬 순서</option>
                    <option value="ASC">오름차순</option>
                    <option value="DESC">내림차순</option>
                  </select>
                </div>
              </div>

              <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">검색</button>
              </div>
            </form>
          </div>
        </div>

        <hr class="my-5">

        <!-- Search Results -->
        <div class="card mb-4 shadow-sm">
          <div class="card-body">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>선수 이름</th>
                <th>포지션</th>
                <th>특성</th>
                <th>주발</th>
                <th>레이팅</th>
                <th>영입</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="player in players" :key="player.playerId" class="cursor-pointer">
                <td>
                  <a @click.prevent="redirectToPlayerDetails(player.playerId)" class="player-link">{{ player.name }}</a>
                </td>
                <td>{{ player.positions.join(', ') }}</td>
                <td>{{ player.traits.join(', ') }}</td>
                <td>{{ player.mainFoot }}</td>
                <td>{{ player.rating }}</td>
                <td>
                  <button @click="openRecruitModal(player)" class="btn btn-success btn-sm">영입</button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Recruit Modal -->
        <div v-if="showModal" class="modal" tabindex="-1" role="dialog" style="display: block;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">선수 영입</h5>
                <button type="button" class="btn-close" @click="closeModal"></button>
              </div>
              <div class="modal-body">

                <div class="form-group">
                  <label for="leagueSelect">리그를 선택해주세요:</label>
                  <select v-model="selectedLeague" class="form-control" id="leagueSelect" @change="fetchTeams">
                    <option v-for="league in leagues" :key="league.name" :value="league.name">{{
                        league.leagueName
                      }}
                    </option>
                  </select>
                </div>

                <div v-if="selectedLeague" class="form-group mt-3">
                  <label for="teamSelect">팀을 선택하세요:</label>
                  <select v-model="selectedTeam" class="form-control" id="teamSelect">
                    <option v-for="team in teams" :key="team.teamId" :value="team">{{ team.name }}</option>
                  </select>
                </div>
                <p v-if="selectedTeam" class="mt-3">
                  {{ selectedPlayer.name }} 선수를 {{ selectedTeam.name }} 팀으로 영입하시겠습니까?
                </p>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" @click="closeModal">닫기</button>
                <button type="button" class="btn btn-primary" @click="recruitPlayer">영입</button>
              </div>
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
  name: 'PlayerSearch',
  data() {
    return {
      searchCriteria: {
        name: '',
        teamName: '',
        mainFoot: '',
        traits: [],
        positions: '',
        rating: {
          value: '',
          operator: '',
          orderBy: ''
        }
      },
      players: [],  // 검색 결과를 저장할 배열
      selectedPlayer: null,  // 선택된 선수 정보를 저장할 객체
      leagues: [], // 리그 목록을 저장할 배열
      teams: [], // 팀 목록을 저장할 배열
      selectedLeague: '', // 선택된 리그
      selectedTeam: '', // 선택된 팀
      showModal: false, // 모달 표시 여부
      traitsOptions: [
        {value: 'PASS_MASTER', label: 'PASS_MASTER'},
        {value: 'HEADER', label: 'HEADER'},
        {value: 'MAJOR', label: 'MAJOR'},
        // 추가 특성 옵션
      ]
    }
  },
  methods: {
    async searchPlayers() {
      const params = new URLSearchParams();
      if (this.searchCriteria.name) {
        params.append('name', this.searchCriteria.name);
      }
      if (this.searchCriteria.position) {
        params.append('position', this.searchCriteria.position);
      }
      if (this.searchCriteria.teamName) {
        params.append('teamName', this.searchCriteria.teamName);
      }
      if (this.searchCriteria.mainFoot) {
        params.append('mainFoot', this.searchCriteria.mainFoot);
      }
      if (this.searchCriteria.traits.length) {
        params.append('traits', this.searchCriteria.traits.join(','));
      }
      if (this.searchCriteria.rating.value) {
        params.append('rating.value', this.searchCriteria.rating.value);
      }
      if (this.searchCriteria.rating.operator) {
        params.append('rating.operator', this.searchCriteria.rating.operator);
      }
      if (this.searchCriteria.rating.orderBy) {
        params.append('rating.orderBy', this.searchCriteria.rating.orderBy);
      }
      try {
        const response = await axios.get(`http://localhost:8080/players?${params.toString()}`);
        this.players = response.data;  // 서버로부터 받은 데이터를 players 배열에 저장
      } catch (error) {
        console.error('Error fetching players:', error);
      }
    },
    async openRecruitModal(player) {
      this.selectedPlayer = player;
      this.showModal = true;
      await this.fetchLeagues();
    },
    closeModal() {
      this.showModal = false;
      this.selectedLeague = '';
      this.selectedTeam = '';
      this.teams = [];
    },
    async fetchLeagues() {
      try {
        const response = await axios.get(`http://localhost:8080/leagues`);
        this.leagues = response.data;
      } catch (error) {
        console.error('Error fetching leagues:', error);
      }
    },
    async fetchTeams() {
      if (this.selectedLeague !== '') {
        try {
          const response = await axios.get(`http://localhost:8080/leagues/${this.selectedLeague}`);
          this.teams = response.data;
        } catch (error) {
          console.error('Error fetching teams:', error);
        }
      }
    },
    async recruitPlayer() {
      try {
        console.log("selectedTeam " + this.selectedTeam)
        await axios.post(`http://localhost:8080/teams/${this.selectedTeam.teamId}/scout`, {
          playerIds: [this.selectedPlayer.playerId]
        });
        alert('선수 영입 성공!');
        this.closeModal();
        // 필요한 경우 추가적인 처리를 여기에 작성
      } catch (error) {
        console.error('Error recruiting player:', error);
        alert('선수 영입 실패!');
      }
    },
    redirectToPlayerDetails(playerId) {
      this.$router.push({name: 'PlayerDetails', params: {playerId: playerId}});
    }
  }
}
</script>

<style scoped>
.modal {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1050;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-dialog {
  max-width: 500px;
}

.modal-content {
  background-color: #fff;
  border-radius: 5px;
  padding: 20px;
}

.cursor-pointer {
  cursor: pointer;
}
</style>
