package com.example.soccerbatch.job.player.create

import com.example.soccerdomain.player.domain.PlayerRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.batch.job.names=player_create"])
@SpringBatchTest
class PlayerCreateJobConfigurationTest @Autowired constructor(
    val jobLauncherTestUtils: JobLauncherTestUtils,
    val playerRepository: PlayerRepository,
) {

    @Test
    fun playerCreateJob() {
        val path = "src/test/resources/player.csv"
        val jobParameters = JobParametersBuilder()
            .addString("input", path)
            .toJobParameters()

        val jobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)
        assertThat(playerRepository.count()).isEqualTo(13)
    }
}
