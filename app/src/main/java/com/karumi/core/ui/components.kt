package com.karumi.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karumi.MainActivity
import com.karumi.ui.theme.KataSuperHeroesComposeTheme

@Composable
fun <C, E> LinkViewModelLifecycle(viewModel: ViewModel<C, E>) {
    val activity = LocalContext.current as MainActivity
    val lifecycle = activity.lifecycle
    DisposableEffect(Unit) {
        lifecycle.addObserver(viewModel)
        onDispose {
            lifecycle.removeObserver(viewModel)
        }
    }
}

@Composable
fun SuperHeroTopBar(title: String) {
    TopAppBar {
        Spacer(Modifier.size(20.dp))
        Text(title, style = MaterialTheme.typography.h1)
    }
}

@Preview
@Composable
private fun SuperHeroTopBarPreview() = KataSuperHeroesComposeTheme {
    SuperHeroTopBar(title = "Kata Super Heroes 🚀")
}
