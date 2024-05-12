package com.example.soccerbatch.job.team

import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.batch.job.names=team_create"])
@SpringBatchTest
class TeamCreateJobConfigurationTest @Autowired constructor(
    val jobLauncherTestUtils: JobLauncherTestUtils,
    val teamRepository: TeamRepository
) {

    @Test
    fun teamCreateJob() {
        val path = "src/test/resources/team.csv"
        val jobParameters = JobParametersBuilder()
            .addString("input", path)
            .toJobParameters()

        val jobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)
        assertThat(teamRepository.count()).isEqualTo(90)

        League.entries
            .map { teamRepository.findTeamByLeague(it) }
            .forEach { assertThat(it.size).isEqualTo(18) }
    }
}
