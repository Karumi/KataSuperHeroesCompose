package com.karumi.domain.usecase

import com.karumi.domain.model.SuperHero
import com.karumi.repository.SuperHeroRepository
import javax.inject.Inject

class GetSuperHeroByName @Inject constructor(private val superHeroesRepository: SuperHeroRepository) {

    operator fun invoke(name: String): SuperHero = superHeroesRepository.getByName(name)
}
