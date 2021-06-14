package com.karumi.ui.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karumi.core.ui.LinkViewModelLifecycle
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.ui.Routes
import com.karumi.R
import com.karumi.core.ui.SuperHeroTopBar

@Composable
fun SuperHeroListScreen(
    navController: NavController,
    viewModel: SuperHeroListViewModel = hiltViewModel()
) {
    LinkViewModelLifecycle(viewModel)
    val state by viewModel.state.collectAsState(ViewModelState.Loading())
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SuperHeroTopBar(title = stringResource(id = R.string.super_heroes_screen_title))
        },
        content = {
            when (val currentState = state) {
                is ViewModelState.Loaded ->
                    SuperHeroListLoadedScreen(
                        state = currentState.content,
                        onSuperHeroTapped = { superHero ->
                            navController.navigate(Routes.Detail.pathFor(superHero.name))
                        })
                else -> SuperHeroListLoadingScreen()
            }
        })
}

@Composable
private fun SuperHeroListLoadedScreen(
    state: SuperHeroListState,
    onSuperHeroTapped: (SuperHero) -> Unit
) {
    Text("Super heroes loaded screen")
}

@Composable
private fun SuperHeroListLoadingScreen() {
    Text("Loading")
}
