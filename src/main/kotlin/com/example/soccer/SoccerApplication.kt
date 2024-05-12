package com.example.soccer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.example.soccer"])
class SoccerApplication

fun main(args: Array<String>) {
    runApplication<SoccerApplication>(*args)
}
