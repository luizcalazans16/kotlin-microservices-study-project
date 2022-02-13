package com.microservices.demo.twitter.to.kafka.service.runner.impl

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData
import com.microservices.demo.twitter.to.kafka.service.exception.TwitterToKafkaServiceException
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import twitter4j.TwitterException
import twitter4j.TwitterObjectFactory
import twitter4j.TwitterStream
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadLocalRandom

@Component
@ConditionalOnProperty(name = ["twitter-to-kafka-service.enable-mock-tweets"], havingValue = "true")
class MockKafkaStreamRunner(
    @Autowired private val twitterToKafkaServiceConfigData: TwitterToKafkaServiceConfigData,
    private val twitterKafkaStatusListener: TwitterKafkaStatusListener,
    private var twitterStream: TwitterStream
) : StreamRunner {

    val LOG = LoggerFactory.getLogger(MockKafkaStreamRunner::class.java)

    companion object {
        @JvmStatic
        val random = Random()

        @JvmStatic
        val words = arrayOf(
            "Lorem",
            "ipsum",
            "dolor",
            "sit",
            "amet",
            "consectetur",
            "adipiscing",
            "elit",
            "Proin ut",
            "porttitor",
            "mi",
            "Proin suscipit",
            "felis"
        )

        @JvmStatic
        val tweetAsJson = "{" +
                "\"created_at\":\"{0}\"," +
                "\"id\":\"{1}\"," +
                "\"text\":\"{2}\"," +
                "\"user\":{\"id\": \"{3}\"" +
                "}"

        @JvmStatic
        val twitterStatusDateFormat = "EEE MMM dd HH:mm:ss zzz yyyy"
    }

    override fun start() {
        val keywords = twitterToKafkaServiceConfigData.twitterKeywords.contentToString()
        val mockMinTweetLength = twitterToKafkaServiceConfigData.mockMinTweetLength
        val mockMaxTweetLength = twitterToKafkaServiceConfigData.mockMaxTweetLength
        val mockSleepMs = twitterToKafkaServiceConfigData.mockSleepMs

        LOG.info("Started filtering twitter stream for keywords: [$keywords]")
        simulateTwitterStream(keywords, mockMinTweetLength, mockMaxTweetLength, mockSleepMs)
    }

    private fun simulateTwitterStream(
        keywords: String,
        mockMinTweetLength: Int,
        mockMaxTweetLength: Int,
        mockSleepMs: Long
    ) {
        Executors.newSingleThreadExecutor().submit {
            try {
                while (true) {
                    val formattedTweetAsJson = getFormattedTweet(keywords, mockMinTweetLength, mockMaxTweetLength)
                    val status = TwitterObjectFactory.createStatus(formattedTweetAsJson)
                    twitterKafkaStatusListener.onStatus(status)
                    sleep(mockSleepMs)
                }
            } catch (exception: TwitterException) {
                LOG.error("Error creating twitter status! $exception")
            }
        }
    }

    private fun sleep(mockSleepMs: Long) {
        try {
            Thread.sleep(mockSleepMs)
        } catch (ex: InterruptedException) {
            throw TwitterToKafkaServiceException("Error while sleeping for waiting new status to create!!")
        }
    }

    private fun getFormattedTweet(
        keywords: String,
        mockMinTweetLength: Int,
        mockMaxTweetLength: Int
    ): String {
        val params = arrayOf(
            ZonedDateTime.now().format(DateTimeFormatter.ofPattern(twitterStatusDateFormat, Locale.ENGLISH)),
            ThreadLocalRandom.current().nextLong(Long.MAX_VALUE).toString(),
            getRandomTweetContent(keywords, mockMinTweetLength, mockMaxTweetLength),
            ThreadLocalRandom.current().nextLong(Long.MAX_VALUE).toString()
        )
        return formatTweetAsJsonWithParameters(params)
    }

    private fun formatTweetAsJsonWithParameters(params: Array<String?>): String {
        var tweet = tweetAsJson

        params.forEach {
            tweet = tweet.replace("{$it}", it.toString())
        }
        return tweet
    }

    private fun getRandomTweetContent(keywords: String, mockMinTweetLength: Int, mockMaxTweetLength: Int): String {
        val tweet = StringBuilder()
        val tweetLength = random.nextInt(mockMaxTweetLength - mockMinTweetLength + 1) + mockMinTweetLength

        return constructRandomTweet(tweetLength, tweet, keywords)
    }

    private fun constructRandomTweet(
        tweetLength: Int,
        tweet: StringBuilder,
        keywords: String
    ): String {
        for (i in 0..tweetLength) {
            tweet.append(words[random.nextInt(words.size)]).append(" ")
            if (i == tweetLength / 2) {
                tweet.append(keywords[random.nextInt(keywords.length)]).append(" ")
            }
        }
        return tweet.toString().trim()
    }
}