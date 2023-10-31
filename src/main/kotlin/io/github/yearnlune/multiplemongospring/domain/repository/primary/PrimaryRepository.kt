package io.github.yearnlune.multiplemongospring.domain.repository.primary

import io.github.yearnlune.multiplemongospring.domain.entity.Primary
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PrimaryRepository : MongoRepository<Primary, String>