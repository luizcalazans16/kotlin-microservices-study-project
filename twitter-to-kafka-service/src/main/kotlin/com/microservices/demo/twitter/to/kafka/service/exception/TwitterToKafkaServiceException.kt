package com.microservices.demo.twitter.to.kafka.service.exception

class TwitterToKafkaServiceException(
    override val message: String?
) : RuntimeException() {

}