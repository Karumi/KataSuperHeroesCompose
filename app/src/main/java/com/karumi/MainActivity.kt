package com.karumi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.karumi.ui.SuperHeroApp
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KataSuperHeroesComposeTheme {
                SuperHeroApp()
            }
        }
    }
}
