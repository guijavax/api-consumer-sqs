package com.apiconsumersqs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiConsumerSqsApplication

fun main(args: Array<String>) {
	runApplication<ApiConsumerSqsApplication>(*args)
}
