package com.karumi.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.coil.rememberCoilPainter
import com.karumi.R
import com.karumi.core.ui.LinkViewModelLifecycle
import com.karumi.core.ui.SuperHeroTopBar
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero

@Composable
fun SuperHeroListScreen(
    viewModel: SuperHeroListViewModel,
    onSuperHeroTapped: (SuperHero) -> Unit
) {
    LinkViewModelLifecycle(viewModel)
    val state by viewModel.state.collectAsState(ViewModelState.Loading())
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SuperHeroTopBar(
                title = stringResource(id = R.string.super_heroes_screen_title)
            )
        },
        content = {
            when (val currentState = state) {
                is ViewModelState.Loaded ->
                    SuperHeroListLoadedScreen(
                        state = currentState.content,
                        onSuperHeroTapped = onSuperHeroTapped
                    )
                else -> SuperHeroListLoadingScreen()
            }
        }
    )
}

@Composable
private fun SuperHeroListLoadedScreen(
    state: SuperHeroListState,
    onSuperHeroTapped: (SuperHero) -> Unit
) = when {
    state.superHeroes.isEmpty() -> SuperHeroEmptyCase()
    else -> SuperHeroesList(state.superHeroes, onSuperHeroTapped)
}

@Composable
private fun SuperHeroesList(superHeroes: List<SuperHero>, onSuperHeroTapped: (SuperHero) -> Unit) {

}

@Composable
fun SuperHeroItem(superHero: SuperHero, onSuperHeroTapped: (SuperHero) -> Unit) {

}

@Composable
fun AvengerBadge(modifier: Modifier = Modifier, superHero: SuperHero) {

}

@Composable
private fun SuperHeroEmptyCase() {

}

@Composable
private fun SuperHeroListLoadingScreen() {

}

