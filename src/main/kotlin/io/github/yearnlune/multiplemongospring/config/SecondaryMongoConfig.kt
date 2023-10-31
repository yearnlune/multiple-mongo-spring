package io.github.yearnlune.multiplemongospring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(
    basePackages = ["io.github.yearnlune.multiplemongospring.domain.repository.secondary"],
    mongoTemplateRef = "secondaryMongoTemplate"
)
class SecondaryMongoConfig(
    appProperties: AppProperties
) : MultipleMongoConfigurer(appProperties.secondary) {
    @Bean
    fun secondaryMongoTemplate(
        secondaryMongoFactory: MongoDatabaseFactory,
        secondaryMongoConverter: MongoConverter
    ): MongoTemplate = MongoTemplate(secondaryMongoFactory, secondaryMongoConverter)

    @Bean
    fun secondaryMongoFactory(): SimpleMongoClientDatabaseFactory {
        return super.mongoFactory()
    }

    @Bean
    fun secondaryMongoConverter(
        appProperties: AppProperties,
        secondaryMongoFactory: MongoDatabaseFactory,
        secondaryMongoCustomConversions: MongoCustomConversions
    ): MongoConverter {
        return super.mongoConverter(
            mongoFactory = secondaryMongoFactory,
            mongoCustomConversions = secondaryMongoCustomConversions,
            fieldNamingStrategy = appProperties.secondary.fieldNamingStrategy
        )
    }

    @Bean
    fun secondaryMongoCustomConversions(): MongoCustomConversions = MongoCustomConversions(
        listOf(
            EnumConverter.memberTypeConverter.readConverter,
            EnumConverter.memberTypeConverter.writeConverter
        )
    )
}