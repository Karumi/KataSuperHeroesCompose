package com.karumi.ui.detail

import com.karumi.core.ui.ViewModel
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroByName
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SuperHeroDetailState(val superHero: SuperHero)

@HiltViewModel
class SuperHeroDetailViewModel @Inject constructor(private val getSuperHeroByName: GetSuperHeroByName) :
    ViewModel<SuperHeroDetailState, Nothing>() {

    lateinit var superHeroName: String

    override fun onCreate() {
        super.onCreate()
        fetchSuperHero()
    }

    private fun fetchSuperHero() = async {
        val superHero = await { getSuperHeroByName(superHeroName) }
        updateState(ViewModelState.Loaded(SuperHeroDetailState(superHero)))
    }
}
