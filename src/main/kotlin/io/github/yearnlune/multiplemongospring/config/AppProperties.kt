package io.github.yearnlune.multiplemongospring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app")
object AppProperties {
    var secondary = MultipleMongoConfigurer.MongodbProperties()
}