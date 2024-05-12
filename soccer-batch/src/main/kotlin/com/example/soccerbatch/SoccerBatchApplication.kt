package com.example.soccerbatch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.example.soccerdomain", "com.example.soccerbatch"]
)
class SoccerBatchApplication

fun main(args: Array<String>) {
    runApplication<SoccerBatchApplication>(*args)
}
