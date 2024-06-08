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
              </tr>
              </thead>
              <tbody>
              <tr v-for="player in players" :key="player.playerId" class="cursor-pointer">
                <td>
                  <a @click.prevent="redirectToPlayerDetails(player.playerId)" class="player-link">{{ player.name }}</a>
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
        position: '',
        rating: {
          value: '',
          operator: '',
          orderBy: ''
        }
      },
      players: [],  // 검색 결과를 저장할 배열
      selectedPlayer: null,  // 선택된 선수 정보를 저장할 객체
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
    redirectToPlayerDetails(playerId) {
      this.$router.push({name: 'PlayerDetails', params: {playerId: playerId}});
    }
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cursor-pointer {
  cursor: pointer;
}
</style>
