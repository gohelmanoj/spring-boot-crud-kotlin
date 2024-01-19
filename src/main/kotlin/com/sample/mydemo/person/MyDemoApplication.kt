package com.sample.mydemo.person

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyDemoApplication

	fun main(args: Array<String>) {
		runApplication<MyDemoApplication>(*args)
	}
