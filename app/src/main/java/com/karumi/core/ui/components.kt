package com.karumi.core.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.karumi.R

@Composable
fun <C, E> LinkViewModelLifecycle(viewModel: ViewModel<C, E>) {
    val activity = LocalContext.current as ComponentActivity
    val lifecycle = activity.lifecycle
    DisposableEffect(Unit) {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }
}

@Composable
fun SuperHeroTopBar(title: String, onBackButtonTapped: (() -> Unit)? = null) {

}
