package com.microservices.demo.twitter.to.kafka.service.runner.impl

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import twitter4j.FilterQuery
import twitter4j.TwitterStream
import twitter4j.TwitterStreamFactory
import javax.annotation.PreDestroy

@Component
@ConditionalOnProperty(name = ["twitter-to-kafka-service.enable-mock-tweets"], havingValue = "false", matchIfMissing = true)
class TwitterKafkaStreamRunner(
    private val twitterToKafkaServiceConfigData: TwitterToKafkaServiceConfigData,
    private val twitterKafkaStatusListener: TwitterKafkaStatusListener,
    private var twitterStream: TwitterStream
) : StreamRunner {

    val LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner::class.java)

    override fun start() {
        val twitterStream = TwitterStreamFactory().instance
        twitterStream.addListener(twitterKafkaStatusListener)
        addFilter(twitterStream)

    }

    @PreDestroy
    fun shutdown() {
        LOG.info("Shutting twitter stream down")
        twitterStream.shutdown()
    }

    private fun addFilter(twitterStream: TwitterStream) {
        val keywords = twitterToKafkaServiceConfigData.twitterKeywords.contentToString()
        val filterQuery = FilterQuery(keywords)
        twitterStream.filter(filterQuery)

        LOG.info("Started filtering twitter stream for keywords: [$keywords]")
    }
}