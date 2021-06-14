package com.karumi.domain.usecase

import com.karumi.domain.model.SuperHero
import com.karumi.repository.SuperHeroRepository
import javax.inject.Inject

class GetSuperHeroes @Inject constructor(private val superHeroesRepository: SuperHeroRepository) {

    operator fun invoke(): List<SuperHero> = superHeroesRepository.getAllSuperHeroes()
}
