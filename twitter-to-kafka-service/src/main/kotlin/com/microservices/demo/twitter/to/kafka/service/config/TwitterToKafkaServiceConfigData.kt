package com.microservices.demo.twitter.to.kafka.service.config

import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@Data
@ConfigurationProperties("twitter-to-kafka-service")
@NoArgsConstructor
class TwitterToKafkaServiceConfigData(
    var twitterKeywords: Array<String> = arrayOf(),
    var enableMockTweets: Boolean,
    var mockSleepMs: Long,
    var mockMinTweetLength: Int,
    var mockMaxTweetLength: Int
)


