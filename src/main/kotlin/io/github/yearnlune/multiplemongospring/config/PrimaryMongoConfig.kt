package io.github.yearnlune.multiplemongospring.config

import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.*
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(
    basePackages = ["io.github.yearnlune.multiplemongospring.domain.repository.primary"],
    mongoTemplateRef = "primaryMongoTemplate"
)
@EnableConfigurationProperties(MongoProperties::class)
class PrimaryMongoConfig(
    mongoProperties: MongoProperties
) : MultipleMongoConfigurer(mongoProperties) {
    @Bean("mongoTemplate", "primaryMongoTemplate")
    fun primaryMongoTemplate(
        primaryMongoFactory: MongoDatabaseFactory,
        primaryMongoConverter: MongoConverter
    ): MongoTemplate = MongoTemplate(primaryMongoFactory, primaryMongoConverter)

    @Bean
    fun primaryMongoFactory(): SimpleMongoClientDatabaseFactory = super.mongoFactory()

    @Bean
    fun primaryMongoConverter(
        mongoProperties: MongoProperties,
        primaryMongoFactory: MongoDatabaseFactory,
        primaryMongoCustomConversions: MongoCustomConversions
    ): MongoConverter {
        return super.mongoConverter(
            mongoFactory = primaryMongoFactory,
            mongoCustomConversions = primaryMongoCustomConversions,
            fieldNamingStrategy = mongoProperties.fieldNamingStrategy
        )
    }

    @Bean
    fun primaryMongoCustomConversions(): MongoCustomConversions = super.mongoCustomConversions()
}