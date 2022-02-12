package com.microservices.demo.twitter.to.kafka.service.runner

import twitter4j.TwitterException
import kotlin.jvm.Throws

interface StreamRunner {

    @Throws(TwitterException::class)
    fun start()
}

