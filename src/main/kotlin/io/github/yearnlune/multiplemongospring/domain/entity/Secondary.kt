package io.github.yearnlune.multiplemongospring.domain.entity

import io.github.yearnlune.multiplemongospring.domain.enum.MemberType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "secondaries")
data class Secondary(
    @Id
    val id: String,
    val name: String,
    val member: MemberType = MemberType.GUEST,
    val createdAt: Date = Date()
)