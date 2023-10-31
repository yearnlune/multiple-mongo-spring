package io.github.yearnlune.multiplemongospring.domain.repository.secondary

import io.github.yearnlune.multiplemongospring.domain.entity.Secondary
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SecondaryRepository : MongoRepository<Secondary, String> {
    fun findByName(name: String): Secondary
}