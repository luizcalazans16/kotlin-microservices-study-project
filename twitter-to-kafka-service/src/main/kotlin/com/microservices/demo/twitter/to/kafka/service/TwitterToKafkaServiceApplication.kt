package com.microservices.demo.twitter.to.kafka.service

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.microservices.demo.twitter.to.kafka.service")
class TwitterToKafkaServiceApplication(
    val twitterToKafkaServiceConfigData: TwitterToKafkaServiceConfigData
) : CommandLineRunner {
    val LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication::class.java)


    override fun run(vararg args: String?) {
        LOG.info("App starts...")
        LOG.info(twitterToKafkaServiceConfigData.twitterKeywords.contentToString())
    }
}

fun main(args: Array<String>) {
    runApplication<TwitterToKafkaServiceApplication>(*args)
}



