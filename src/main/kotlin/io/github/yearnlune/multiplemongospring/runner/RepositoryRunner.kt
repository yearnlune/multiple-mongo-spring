package io.github.yearnlune.multiplemongospring.runner

import io.github.yearnlune.multiplemongospring.domain.entity.Primary
import io.github.yearnlune.multiplemongospring.domain.entity.Secondary
import io.github.yearnlune.multiplemongospring.domain.repository.primary.PrimaryRepository
import io.github.yearnlune.multiplemongospring.domain.repository.secondary.SecondaryRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class RepositoryRunner(
    private val primaryRepository: PrimaryRepository,
    private val secondaryRepository: SecondaryRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        println("==INSERT START==")
        val primary = primaryRepository.insert(Primary(UUID.randomUUID().toString(), "primary"))
        val secondary = secondaryRepository.insert(Secondary(UUID.randomUUID().toString(), "secondary"))
        println("primary: $primary\nsecondary: $secondary")
    }
}