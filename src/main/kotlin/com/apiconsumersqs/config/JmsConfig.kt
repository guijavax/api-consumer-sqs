package com.apiconsumersqs.config

import com.amazon.sqs.javamessaging.ProviderConfiguration
import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.support.destination.DynamicDestinationResolver
import javax.jms.Session

@Configuration
@EnableJms
class JmsConfig {

    @Value("\${aws.region}")
    lateinit var region : String

    @Bean
    fun jmsListenerContainerFactory() : DefaultJmsListenerContainerFactory {
//        var sqsConnectionFactory = SQSConnectionFactory(ProviderConfiguration(), AmazonSQSClientBuilder.standard().withRegion(region).withCredentials(DefaultAWSCredentialsProviderChain()).build())
        var sqs = AmazonSQSClientBuilder.standard().withRegion(region).build()
        var sqsConnectionFactory = SQSConnectionFactory(ProviderConfiguration(), sqs)

        return DefaultJmsListenerContainerFactory().apply {
            setConnectionFactory(sqsConnectionFactory)
            setDestinationResolver(DynamicDestinationResolver())
            setConcurrency("2")
            setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE)
        }
    }
}