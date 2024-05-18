package com.example.soccerapi.batch

import com.example.soccerapi.config.JenkinsProperties
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import java.net.URI
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class JenkinsTrigger(
    val jenkinsProperties: JenkinsProperties,
    val webClient: WebClient
) {

    @OptIn(ExperimentalEncodingApi::class)
    fun runBatch(jobName: String, jobParameter: Map<String, String>) {
        // curl -X POST "http://your-jenkins-url/job/your-job-name/buildWithParameters?token=my-token&PARAM1=value1&PARAM2=value2" --user your-username:your-api-token
        val auth = jenkinsProperties.user + ":" + jenkinsProperties.token
        val queryParameters = LinkedMultiValueMap<String, String>()
        for ((key, value) in jobParameter.entries) {
            queryParameters[key] = listOf(value)
        }

        webClient.post()
            .uri(uriBuild(jenkinsProperties.url, "/job/${jobName}/buildWithParameters", queryParameters))
            .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encode(auth.toByteArray()))
            .retrieve()
            .toBodilessEntity()
            .subscribe()
    }

    private fun uriBuild(uri: String, path: String, queryParameters: MultiValueMap<String, String>): URI {
        return DefaultUriBuilderFactory().uriString(uri)
            .path(path)
            .queryParams(queryParameters)
            .build()
    }
}
