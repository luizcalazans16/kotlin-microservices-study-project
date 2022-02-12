package com.microservices.demo.twitter.to.kafka.service.listener

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import twitter4j.Status
import twitter4j.StatusAdapter

@Component
class TwitterKafkaStatusListener : StatusAdapter() {

    val LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener::class.java)

    override fun onStatus(status: Status?) {
        LOG.info("Twitter status with text: ${status!!.text}")
    }
}