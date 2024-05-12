package com.example.soccer.batch.job.player.create

import com.example.soccer.player.domain.Player
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
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
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.PathResource
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class PlayerCreateJobConfiguration {


    @Bean
    fun playerCreateJob(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        entityManagerFactory: EntityManagerFactory
    ): Job {
        return JobBuilder("player-create-job", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(playerCreateStep(jobRepository, transactionManager, entityManagerFactory))
            .build()
    }

    @Bean
    fun playerCreateStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        entityManagerFactory: EntityManagerFactory
    ): Step {
        return StepBuilder("player-create-step", jobRepository)
            .chunk<InputPlayer, Player>(10, transactionManager)
            .reader(playerReader(null))
            .processor(playerProcessor())
            .writer(playerWriter(entityManagerFactory))
            .build()
    }


    @Bean
    @StepScope
    fun playerReader(@Value("#{jobParameters['input']}") input: String?): FlatFileItemReader<InputPlayer> {
        val fieldSetMapper = BeanWrapperFieldSetMapper<InputPlayer>().apply { setTargetType(InputPlayer::class.java) }

        return FlatFileItemReaderBuilder<InputPlayer>()
            .name("scvPlayerReader")
            .resource(PathResource(input!!))
            .delimited()
            .delimiter(",")
            .names(*arrayOf("name", "position", "trait", "mainFoot"))
            .fieldSetMapper(fieldSetMapper)
            .build()
    }

    @Bean
    @StepScope
    fun playerProcessor(): ItemProcessor<in InputPlayer, out Player> {
        return PlayerCreatorProcessor()
    }


    @Bean
    @StepScope
    fun playerWriter(entityManagerFactory: EntityManagerFactory): ItemWriter<Player> {
        val jpaItemWriter = JpaItemWriter<Player>()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return jpaItemWriter
    }
}
