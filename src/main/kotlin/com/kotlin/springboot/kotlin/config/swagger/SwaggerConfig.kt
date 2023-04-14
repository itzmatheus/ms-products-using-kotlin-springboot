package com.kotlin.springboot.kotlin.config.swagger

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Value("#{ @environment['swagger.server-url'] }")
    private val serverUrl: String = "/"

    @Bean
    fun springOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title("MS Product")
                    .description("Simple Product API with CRUD of Products and Categories.")
                    .version("v1.0.0")
                    .license(License().name("Apache 2.0").url("https://springdoc.org"))
            )
            .addServersItem(Server().url(serverUrl))
            .externalDocs(
                ExternalDocumentation()
                    .description("Spring Wiki Documentation")
                    .url("https://spring.wiki.github.org/docs")
            )
    }


}