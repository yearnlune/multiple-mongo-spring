package io.github.yearnlune.multiplemongospring.config

import org.springframework.beans.BeanUtils
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.data.mapping.model.FieldNamingStrategy
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.*
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

abstract class MultipleMongoConfigurer(
    private val appProperties: MongodbProperties
) {

    constructor(mongoProperties: MongoProperties) : this(MongodbProperties(mongoProperties))

    fun mongoFactory(): SimpleMongoClientDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(
            "mongodb://${appProperties.username}" +
                    ":${URLEncoder.encode(appProperties.password, StandardCharsets.UTF_8)}" +
                    "@${appProperties.host}" +
                    ":${appProperties.port}" +
                    "/${appProperties.database}" +
                    "?authSource=${appProperties.authenticationDatabase}"
        )
    }

    fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions(emptyList<Any>())
    }

    fun mongoConverter(
        mongoFactory: MongoDatabaseFactory,
        mongoCustomConversions: MongoCustomConversions = mongoCustomConversions(),
        fieldNamingStrategy: Class<*>? = null
    ): MongoConverter {
        val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoFactory)

        val mappingContext = MongoMappingContext()
        mappingContext.setSimpleTypeHolder(mongoCustomConversions.simpleTypeHolder)
        fieldNamingStrategy?.let {
            mappingContext.setFieldNamingStrategy(BeanUtils.instantiateClass(fieldNamingStrategy) as FieldNamingStrategy)
        }
        mappingContext.afterPropertiesSet()

        val converter = MappingMongoConverter(dbRefResolver, mappingContext)
        converter.setCustomConversions(mongoCustomConversions)
        converter.setCodecRegistryProvider(mongoFactory)
        converter.afterPropertiesSet()

        return converter
    }

    data class MongodbProperties(
        var host: String? = null,
        var port: Int? = 27017,
        var database: String? = null,
        var username: String? = null,
        var password: String? = null,
        var authenticationDatabase: String = "admin",
        var fieldNamingStrategy: Class<*>? = null,
    ) {
        constructor(mongoProperties: MongoProperties) : this(
            mongoProperties.host,
            mongoProperties.port,
            mongoProperties.database,
            mongoProperties.username,
            String(mongoProperties.password),
            mongoProperties.authenticationDatabase,
            mongoProperties.fieldNamingStrategy
        )
    }
}