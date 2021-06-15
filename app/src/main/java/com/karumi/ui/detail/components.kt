package com.karumi.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import com.karumi.core.ui.LinkViewModelLifecycle
import com.karumi.core.ui.SuperHeroTopBar
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.ui.list.AvengerBadge

@Composable
fun SuperHeroDetailScreen(
    viewModel: SuperHeroDetailViewModel,
    navHostController: NavHostController
) {
    LinkViewModelLifecycle(viewModel)
    val state by viewModel.state.collectAsState(ViewModelState.Loading())
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            SuperHeroTopBar(title = viewModel.superHeroName)
        },
        content = {
            when (val currentState = state) {
                is ViewModelState.Loaded -> SuperHeroLoadedScreen(currentState.content.superHero)
                else -> SuperHeroLoadingScreen()
            }
        }
    )
}

@Composable
private fun SuperHeroLoadedScreen(superHero: SuperHero) = Column(Modifier.fillMaxSize()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = rememberCoilPainter(superHero.photo),
            contentDescription = superHero.name
        )
        AvengerBadge(
            superHero = superHero, modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomEnd)
        )
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(Modifier.size(10.dp))
        Text(
            text = superHero.name,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(Modifier.size(10.dp))
        Text(
            text = superHero.description.repeat(10),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun SuperHeroLoadingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MaterialTheme.colors.secondary)
    }
}
