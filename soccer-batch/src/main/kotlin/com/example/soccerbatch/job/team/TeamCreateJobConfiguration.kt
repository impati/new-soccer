package com.example.soccerbatch.job.team

import com.example.soccerdomain.common.FileService
import com.example.soccerdomain.team.domain.League
import com.example.soccerdomain.team.domain.Team
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ByteArrayResource
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@ConditionalOnProperty(value = ["spring.batch.job.names"], havingValue = "team_create")
class TeamCreateJobConfiguration(
    val fileService: FileService
) {

    @Bean
    fun teamCreateJob(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        entityManagerFactory: EntityManagerFactory
    ): Job {
        return JobBuilder("team-create-job", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(teamCreateStep(jobRepository, transactionManager, entityManagerFactory))
            .build()
    }

    @Bean
    @JobScope
    fun teamCreateStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        entityManagerFactory: EntityManagerFactory
    ): Step {
        return StepBuilder("team-create-step", jobRepository)
            .chunk<InputTeam, Team>(10, transactionManager)
            .reader(teamReader(null))
            .processor(teamProcessor())
            .writer(teamWriter(entityManagerFactory))
            .build()
    }

    @Bean
    @StepScope
    fun teamReader(@Value("#{jobParameters['input']}") input: String?): FlatFileItemReader<InputTeam> {
        val fieldSetMapper = BeanWrapperFieldSetMapper<InputTeam>().apply { setTargetType(InputTeam::class.java) }
        val downloadByteArray = fileService.downloadFile(input!!);

        return FlatFileItemReaderBuilder<InputTeam>()
            .name("scvTeamReader")
            .resource(ByteArrayResource(downloadByteArray))
            .strict(false)
            .delimited()
            .delimiter(",")
            .names(*arrayOf("name", "league"))
            .fieldSetMapper(fieldSetMapper)
            .build()
    }

    @Bean
    @StepScope
    fun teamProcessor(): ItemProcessor<in InputTeam, out Team> {
        return ItemProcessor<InputTeam, Team> {
            Team(league = League.valueOf(it.league), name = it.name)
        }
    }


    @Bean
    @StepScope
    fun teamWriter(entityManagerFactory: EntityManagerFactory): ItemWriter<Team> {
        val jpaItemWriter = JpaItemWriter<Team>()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return jpaItemWriter
    }
}
