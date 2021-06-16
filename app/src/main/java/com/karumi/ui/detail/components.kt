package com.karumi.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.karumi.core.ui.LinkViewModelLifecycle
import com.karumi.core.ui.SuperHeroTopBar
import com.karumi.core.ui.ViewModelState
import com.karumi.domain.model.SuperHero
import com.karumi.ui.list.AvengerBadge

@Composable
fun SuperHeroDetailScreen(
    viewModel: SuperHeroDetailViewModel,
    onBackButtonTapped: () -> Unit
) {
    LinkViewModelLifecycle(viewModel)
    val state by viewModel.state.collectAsState(ViewModelState.Loading())

}
