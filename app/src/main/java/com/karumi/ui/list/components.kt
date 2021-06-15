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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.karumi.R
import com.karumi.core.ui.LinkViewModelLifecycle
import com.karumi.core.ui.SuperHeroTopBar
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.ui.Routes

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
                        }
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
fun SuperHeroesList(superHeroes: List<SuperHero>, onSuperHeroTapped: (SuperHero) -> Unit) =
    LazyColumn(Modifier.fillMaxSize()) {
        items(superHeroes) { superHero ->
            SuperHeroItem(superHero, onSuperHeroTapped)
        }
    }

@Composable
private fun SuperHeroItem(superHero: SuperHero, onSuperHeroTapped: (SuperHero) -> Unit) =
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                onSuperHeroTapped(superHero)
            }
    ) {
        val (background, backgroundGradient, name, badge) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(background) {

                },
            painter = rememberCoilPainter(superHero.photo),
            contentDescription = superHero.name,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .constrainAs(backgroundGradient) {
                    bottom.linkTo(parent.bottom)
                    linkTo(start = parent.start, end = parent.end)
                }
        )
        Text(
            modifier = Modifier.constrainAs(name) {
                linkTo(
                    start = parent.start,
                    end = badge.start,
                    bias = 0f,
                    startMargin = 20.dp,
                    endMargin = 10.dp
                )
                bottom.linkTo(parent.bottom, 10.dp)
                width = Dimension.preferredWrapContent
            },
            text = superHero.name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        AvengerBadge(
            modifier = Modifier
                .constrainAs(badge) {
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                }, superHero = superHero
        )
    }

@Composable
fun AvengerBadge(modifier: Modifier = Modifier, superHero: SuperHero) {
    if (superHero.isAvenger) {
        Icon(
            modifier = modifier.size(70.dp),
            tint = Color.Unspecified,
            painter = painterResource(id = R.mipmap.ic_avengers),
            contentDescription = stringResource(R.string.avengers_badge_content_description)
        )
    }
}

@Composable
private fun SuperHeroEmptyCase() =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.super_heroes_screen_empty_case_text))
    }

@Composable
private fun SuperHeroListLoadingScreen() =
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
    }
