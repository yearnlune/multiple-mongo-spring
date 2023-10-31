package io.github.yearnlune.multiplemongospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
	UserDetailsServiceAutoConfiguration::class,
	MongoAutoConfiguration::class,
	MongoDataAutoConfiguration::class,
])
class MultipleMongoSpringApplication

fun main(args: Array<String>) {
	runApplication<MultipleMongoSpringApplication>(*args)
}

