package com.example.soccerapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Coupon API")
                .description("API to manage coupons")
                .version("1.0")
        )
    }
}
