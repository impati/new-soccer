<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-lg-10">


        <!-- Search Form -->
        <div class="bg-light p-5 rounded">
          <div>
            <h2>선수 검색</h2>
          </div>
          <div class="card-body">
            <form @submit.prevent="searchPlayers">
              <div class="form-group row mb-3">
                <label for="playerName" class="col-sm-1 col-form-label">선수명 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="playerName" v-model="searchCriteria.name"
                         placeholder="선수명">
                </div>
              </div>
              <div class="form-group row mb-3">
                <label for="position" class="col-sm-1 col-form-label">포지션 :</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="position" v-model="searchCriteria.position"
                         placeholder="포지션">
                </div>
              </div>
              <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary">검색</button>
              </div>
            </form>
          </div>
        </div>

        <hr>

        <!-- Search Results -->
        <div class="card mb-4">
          <div class="card-body">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>선수 이름</th>
                <th>선수 포지션</th>
                <th>...</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="player in players" :key="player.id" @click="selectPlayer(player)">
                <td>{{ player.name }}</td>
                <td>{{ player.position }}</td>
                <td>...</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- Player Details -->
        <div class="card" v-if="selectedPlayer">
          <div class="card-header">
            <h4 class="mb-0">선수 정보</h4>
          </div>
          <div class="card-body">
            <p><strong>이름:</strong> {{ selectedPlayer.name }}</p>
            <p><strong>포지션:</strong> {{ selectedPlayer.position }}</p>
            <!-- 추가 선수 정보 -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PlayerSearch',
  data() {
    return {
      searchCriteria: {
        name: '',
        position: ''
      },
      players: [],  // 검색 결과를 저장할 배열
      selectedPlayer: null  // 선택된 선수 정보를 저장할 객체
    }
  },
  methods: {
    searchPlayers() {
      // 여기에 검색 로직을 추가합니다
      // 예시로 하드코딩된 데이터를 사용합니다
      this.players = [
        {id: 1, name: '홍길동', position: '공격수'},
        {id: 2, name: '이순신', position: '수비수'}
      ];
    },
    selectPlayer(player) {
      this.selectedPlayer = player;
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
</style>
