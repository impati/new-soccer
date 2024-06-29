<template xmlns:width="http://www.w3.org/1999/xhtml">
  <div class="container mt-4" v-if="team">
    <!-- team Info -->
    <div class="card team-info mb-4">
      <div class="card-header d-flex justify-content-between align-items-center">
        <h2>{{ team.name }}</h2>
        <span class="badge bg-secondary">{{ team.rating }}</span>
      </div>
    </div>

    <!-- formation -->
    <div class="card formation mb-4">
      <div class="card-header d-flex justify-content-between align-items-center">
        <h3>포메이션</h3>
        <button class="btn btn-primary" @click="showAddFormationModal">+ 포메이션 추가</button>
      </div>
      <div class="card-body">

        <div class="btn-group mb-3">
          <button
              v-for="formation in formations"
              :key="formation.formationId"
              class="btn btn-dark"
              @click="selectFormation(formation)">
            {{ formation.formationName }}
          </button>
        </div>

        <div v-if="selectedFormation">
          <div class="d-flex justify-content-between align-items-center mb-2">
            <h4>선발 목록</h4>
            <div>
              <button class="btn btn-warning me-2" @click="showEditFormationModal">포메이션 수정</button>
              <button class="btn btn-danger" @click="deleteFormation">포메이션 삭제</button>
            </div>
          </div>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th>포지션</th>
              <th>선수 이름</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="player in selectedFormation.formationElements" :key="player.playerId">
              <td>{{ player.position }}</td>
              <td>{{ player.playerName }}</td>
            </tr>
            </tbody>
          </table>


        </div>
      </div>
    </div>


    <hr>
    <!-- Player Lists -->
    <div>
      <h4>선수 목록</h4>
    </div>
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
          <tr
              v-for="player in team.players"
              :key="player.playerId"
              class="cursor-pointer"
          >
            <td>
              <a @click.prevent="redirectToPlayerDetails(player.playerId)">{{ player.name }}</a>
            </td>
            <td>{{ player.positions.join(', ') }}</td>
            <td>{{ player.traits.join(', ') }}</td>
            <td>{{ player.mainFoot }}</td>
            <td>{{ player.rating }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>


    <!-- 포메이션 추가 모달 -->
    <div class="modal fade" id="addFormationModal" tabindex="-1" aria-labelledby="addFormationModalLabel"
         aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">

          <div class="modal-header">
            <h5 class="modal-title" id="addFormationModalLabel">포메이션 추가</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <div class="modal-body">
            <form @submit.prevent="addFormation">

              <div class="mb-3">
                <label for="formationName" class="form-label">포메이션 명</label>
                <input type="text" class="form-control" id="formationName" v-model="newFormation.name" required>
              </div>


              <h4>선발 목록</h4>
              <table class="table table-bordered">
                <thead>
                <tr>
                  <th style="width: 20%;">포지션</th>
                  <th style="width: 70%;">선수 이름</th>
                  <th style="width: 10%;"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="player in startPlayers" :key="player.playerId">
                  <td>
                    <select v-model="player.selectedPosition" class="col-form-label">
                      <option disabled value="">포지션 선택</option>
                      <option v-for="position in availablePositions" :key="position">{{ position }}</option>
                    </select>
                  </td>
                  <td>{{ player.name }}</td>
                  <td>
                    <button class="btn btn-arrow red-arrow" @click="moveToReserves(player)">⬇️</button>
                  </td>
                </tr>
                </tbody>
              </table>

              <h4>후보 선수 목록</h4>
              <table class="table table-bordered table-primary">
                <thead>
                <tr>
                  <th style="width: 20%;">포지션</th>
                  <th style="width: 70%;">선수 이름</th>
                  <th style="width: 10%;"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="player in subPlayers" :key="player.playerId">
                  <td>{{ player.positions.join(', ') }}</td>
                  <td>{{ player.name }}</td>
                  <td>
                    <button class="btn btn-arrow blue-arrow" @click="moveToStarters(player)">⬆️</button>
                  </td>
                </tr>
                </tbody>
              </table>


              <button type="submit" class="btn btn-primary right">추가</button>
            </form>
          </div>
        </div>
      </div>
    </div>


    <!-- 포메이션 수정 모달 -->
    <div v-if="selectedFormation" class="modal fade" id="editFormationModal" tabindex="-1"
         aria-labelledby="editFormationModalLabel"
         aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editFormationModalLabel">포메이션 수정</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="editFormation">
              <div class="mb-3">
                <label for="formationName" class="form-label">포메이션 명</label>
                <input type="text" class="form-control" id="formationName" v-model="selectedFormation.formationName"
                       required>
              </div>
              <h4>선발 목록</h4>
              <table class="table table-bordered">
                <thead>
                <tr>
                  <th style="width: 20%;">포지션</th>
                  <th style="width: 70%;">선수 이름</th>
                  <th style="width: 10%;"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="player in startPlayers" :key="player.playerId">
                  <td>
                    <select v-model="player.selectedPosition" class="col-form-label">
                      <option disabled value="">포지션 선택</option>
                      <option v-for="position in availablePositions " :key="position">{{ position }}</option>
                    </select>
                  </td>
                  <td>{{ player.name }}</td>
                  <td>
                    <button class="btn btn-arrow red-arrow" @click="moveToReserves(player)">⬇️</button>
                  </td>
                </tr>
                </tbody>
              </table>

              <h4>후보 선수 목록</h4>
              <table class="table table-bordered table-primary">
                <thead>
                <tr>
                  <th style="width: 20%;">포지션</th>
                  <th style="width: 70%;">선수 이름</th>
                  <th style="width: 10%;"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="player in subPlayers" :key="player.playerId">
                  <td>{{ player.positions.join(', ') }}</td>
                  <td>{{ player.name }}</td>
                  <td>
                    <button class="btn btn-arrow blue-arrow" @click="moveToStarters(player)">⬆️</button>
                  </td>
                </tr>
                </tbody>
              </table>

              <button type="submit" class="btn btn-primary right">수정</button>
            </form>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import {Modal} from 'bootstrap';

export default {
  name: "TeamDetails",
  data() {
    return {
      team: null,
      loading: true,
      availablePositions: ['GK', 'CB', 'LB', 'RB', 'CDM', 'CM', 'CAM', 'LM', 'RM', 'LW', 'RW', 'CF', 'ST'],
      formations: [],
      selectedFormation: null,
      newFormation: {
        name: "",
        elements: []
      },
      startPlayers: [],
      subPlayers: [],
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
      this.$router.push({name: "PlayerDetails", params: {playerId: playerId}});
    },
    async fetchTeamData() {
      try {
        this.team = (await axios.get(`http://localhost:8080/teams/${this.teamId}`)).data;
        this.formations = (await axios.get(`http://localhost:8080/teams/${this.teamId}/formations`)).data;

      } catch (error) {
        console.error("팀 데이터를 가져오는 중 오류 발생:", error);
      } finally {
        this.loading = false;
      }
    },
    selectFormation(formation) {
      this.selectedFormation = formation;
    },
    showAddFormationModal() {
      const modal = new Modal(document.getElementById('addFormationModal'));
      this.subPlayers = this.team.players;
      modal.show();
    },
    showEditFormationModal() {
      const modal = new Modal(document.getElementById('editFormationModal'));
      this.startPlayers = this.selectedFormation.formationElements.map(p => ({
        playerId: p.playerId,
        position: [p.position],
        name: p.playerName,
        selectedPosition: p.position
      }));
      this.subPlayers = this.team.players.filter(p => !this.startPlayers.find(sp => sp.playerId === p.playerId));
      modal.show();
    },
    async addFormation() {
      try {
        this.newFormation.elements = this.startPlayers.map(p => ({
          position: p.selectedPosition,
          playerId: p.playerId
        }))
        await axios.post(`http://localhost:8080/teams/${this.teamId}/formations`, this.newFormation);
        this.newFormation.name = "";
        this.newFormation.elements = []
        this.startPlayers = []
        const modal = Modal.getInstance(document.getElementById('addFormationModal'));
        modal.hide();
        this.refreshData()
      } catch (error) {
        console.error("포메이션을 추가하는 중 오류 발생:", error);
      }
    },
    async editFormation() {
      try {
        this.newFormation.name = this.selectedFormation.formationName
        this.newFormation.elements = this.startPlayers.map(p => ({
          position: p.selectedPosition,
          playerId: p.playerId
        }))
        await axios.put(`http://localhost:8080/teams/${this.teamId}/formations/${this.selectedFormation.formationId}`,
            this.newFormation);
        this.newFormation.name = "";
        this.newFormation.elements = []
        this.startPlayers = []
        const modal = Modal.getInstance(document.getElementById('editFormationModal'));
        modal.hide();
        this.refreshData()
      } catch (error) {
        console.error("포메이션을 수정하는 중 오류 발생:", error);
      }
    },
    async deleteFormation() {
      try {
        await axios.delete(
            `http://localhost:8080/teams/${this.teamId}/formations/${this.selectedFormation.formationId}`,
            this.newFormation);
        this.refreshData()
      } catch (error) {
        console.error("포메이션을 삭제하는 중 오류 발생:", error);
      }
    },
    moveToStarters(player) {
      this.startPlayers.push(player)
      this.subPlayers = this.subPlayers.filter(p => p.playerId !== player.playerId);
    },
    moveToReserves(player) {
      this.subPlayers.push(player)
      this.startPlayers = this.startPlayers.filter(p => p.playerId !== player.playerId)
    },
    refreshData() {
      window.location.reload()
    }
  },
};
</script>

<style scoped>
.team-info {
  margin-bottom: 20px; /* 공간을 추가합니다 */
}

.player-lists {
  margin-top: 20px; /* 필요에 따라 추가 공간을 설정합니다 */
}

/* 두 카드의 가로 길이를 동일하게 설정합니다 */
.team-info,
.player-lists {
  width: 100%;
}

.formation {
  background-color: white;
  color: black;
}

.table-bordered {
  background-color: white;
  color: black;
}

.btn-arrow {
  background: none;
  border: none;
  font-size: 2rem;
  cursor: pointer;
  margin: 0 10px;
}

.red-arrow {
  color: red;
}

.blue-arrow {
  color: blue;
}
</style>
