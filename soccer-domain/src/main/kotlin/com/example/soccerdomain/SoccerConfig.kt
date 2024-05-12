package com.example.soccerdomain

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackageClasses = [SoccerBase::class])
@EntityScan(basePackageClasses = [SoccerBase::class])
@ComponentScan(basePackageClasses = [SoccerBase::class])
class SoccerConfig {
}
