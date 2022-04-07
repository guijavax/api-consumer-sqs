package com.apiconsumersqs.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import javax.jms.TextMessage

@Service
class SQSConsumerService {

    @JmsListener(destination = "cliente")
    fun receiveEvent(message : String) {
        try {
            print(message)
        } catch (e : Exception) {
            println(e.message)
        }
    }
}