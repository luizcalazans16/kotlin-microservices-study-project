plugins {
    id("org.springframework.boot")
    application
    kotlin("jvm")
    kotlin("plugin.spring")
}


application {
    mainClass.set("com.microservices.demo.twitter.to.kafka.service.TwitterToKafkaServiceApplication")
}