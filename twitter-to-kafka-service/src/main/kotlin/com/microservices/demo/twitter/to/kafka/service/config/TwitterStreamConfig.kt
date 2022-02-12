package com.microservices.demo.twitter.to.kafka.service.config

import org.springframework.stereotype.Component
import twitter4j.*
import twitter4j.auth.AccessToken
import twitter4j.auth.Authorization
import twitter4j.auth.RequestToken
import twitter4j.conf.Configuration
import twitter4j.util.function.Consumer
import java.lang.Exception

@Component
class TwitterStreamConfig : TwitterStream {

    override fun setOAuthConsumer(consumerKey: String?, consumerSecret: String?) {
        TODO("Not yet implemented")
    }

    override fun getOAuthRequestToken(): RequestToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthRequestToken(callbackURL: String?): RequestToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthRequestToken(callbackURL: String?, xAuthAccessType: String?): RequestToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthRequestToken(
        callbackURL: String?,
        xAuthAccessType: String?,
        xAuthMode: String?
    ): RequestToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthAccessToken(): AccessToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthAccessToken(oauthVerifier: String?): AccessToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthAccessToken(requestToken: RequestToken?): AccessToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthAccessToken(requestToken: RequestToken?, oauthVerifier: String?): AccessToken {
        TODO("Not yet implemented")
    }

    override fun getOAuthAccessToken(screenName: String?, password: String?): AccessToken {
        TODO("Not yet implemented")
    }

    override fun setOAuthAccessToken(accessToken: AccessToken?) {
        TODO("Not yet implemented")
    }

    override fun getScreenName(): String {
        TODO("Not yet implemented")
    }

    override fun getId(): Long {
        TODO("Not yet implemented")
    }

    override fun addRateLimitStatusListener(listener: RateLimitStatusListener?) {
        TODO("Not yet implemented")
    }

    override fun onRateLimitStatus(action: Consumer<RateLimitStatusEvent>?) {
        TODO("Not yet implemented")
    }

    override fun onRateLimitReached(action: Consumer<RateLimitStatusEvent>?) {
        TODO("Not yet implemented")
    }

    override fun getAuthorization(): Authorization {
        TODO("Not yet implemented")
    }

    override fun getConfiguration(): Configuration {
        TODO("Not yet implemented")
    }

    override fun addConnectionLifeCycleListener(listener: ConnectionLifeCycleListener?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun addListener(listener: StreamListener?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun onStatus(action: Consumer<Status>?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun onException(action: Consumer<Exception>?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun removeListener(listener: StreamListener?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun clearListeners(): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun replaceListener(toBeRemoved: StreamListener?, toBeAdded: StreamListener?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun firehose(count: Int): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun links(count: Int): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun retweet(): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun sample(): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun sample(language: String?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun user(): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun user(vararg track: String?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun site(withFollowings: Boolean, vararg follow: Long): StreamController {
        TODO("Not yet implemented")
    }

    override fun filter(query: FilterQuery?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun filter(vararg track: String?): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun cleanUp(): TwitterStream {
        TODO("Not yet implemented")
    }

    override fun shutdown(): TwitterStream {
        TODO("Not yet implemented")
    }
}