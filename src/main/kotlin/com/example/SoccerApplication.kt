package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoccerApplication

fun main(args: Array<String>) {
    runApplication<SoccerApplication>(*args)
}
