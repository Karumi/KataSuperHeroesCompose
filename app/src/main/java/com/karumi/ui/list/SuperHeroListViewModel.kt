package com.karumi.ui.list

import com.karumi.core.ui.ViewModel
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SuperHeroListState(val superHeroes: List<SuperHero>)

@HiltViewModel
class SuperHeroListViewModel @Inject constructor(private val getSuperHeroes: GetSuperHeroes) :
    ViewModel<SuperHeroListState, Nothing>() {

    override fun onCreate() {
        super.onCreate()
        fetchSuperHeroes()
    }

    private fun fetchSuperHeroes() = async {
        val superHeroes = await { getSuperHeroes() }
        updateState(ViewModelState.Loaded(SuperHeroListState(superHeroes)))
    }
}
