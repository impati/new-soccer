package com.example.soccerapi.config

import io.github.oshai.kotlinlogging.KLogging
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

private val log = KLogging().logger

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .filter(logRequest())
            .filter(logResponse())
            .clientConnector(ReactorClientHttpConnector(httpClient()))
            .build()
    }

    @Bean
    fun httpClient(): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
            .responseTimeout(Duration.ofMillis(3000))
            .doOnConnected {
                it.addHandlerLast(ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS))
            }
    }

    fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
            log.info("Request: {} {}\n", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(
                        Consumer<String?> { value: String? ->
                            log.info(
                                "Request Header: {}={}",
                                name,
                                value
                            )
                        })
                }
            Mono.just(clientRequest)
        }
    }

    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
            log.info("Response Status: {}\n", clientResponse.statusCode())
            clientResponse.headers()
                .asHttpHeaders()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(
                        Consumer { value: String? ->
                            log.info(
                                "Response Header {}={}",
                                name,
                                value
                            )
                        })
                }
            Mono.just(clientResponse)
        }
    }
}
