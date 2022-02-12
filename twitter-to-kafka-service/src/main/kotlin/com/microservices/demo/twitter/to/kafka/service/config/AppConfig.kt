package com.microservices.demo.twitter.to.kafka.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AppConfig {

    @Primary
    @Bean
    fun twitterStreamBean() : TwitterStreamConfig {
        return TwitterStreamConfig()
    }
}