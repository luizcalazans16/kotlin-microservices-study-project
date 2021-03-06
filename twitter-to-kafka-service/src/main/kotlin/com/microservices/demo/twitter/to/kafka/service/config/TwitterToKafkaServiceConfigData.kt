package com.microservices.demo.twitter.to.kafka.service.config

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@Data
@ConfigurationProperties("twitter-to-kafka-service")
class TwitterToKafkaServiceConfigData(
    var twitterKeywords: Array<String> = arrayOf()
)


