package io.github.yearnlune.multiplemongospring.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "primaries")
data class Primary(
    @Id
    val id: String,
    val name: String,
    val createdAt: Date = Date()
)