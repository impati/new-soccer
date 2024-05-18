package com.example.soccerapi.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jenkins")
class JenkinsProperties(
    val token: String,
    val url: String,
    val user: String
) {
}
