package com.example.soccerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.example.soccerdomain", "com.example.soccerapi"]
)
class SoccerApiApplication

fun main(args: Array<String>) {
    runApplication<SoccerApiApplication>(*args)
}
