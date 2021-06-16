package com.karumi.ui.list

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import com.karumi.utils.MockedTest
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class SuperHeroListScreenScreenshotTests : ScreenshotTest, MockedTest {

    companion object {
        const val ANY_NUMBER_OF_SUPER_HEROES = 100
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Mock
    lateinit var getSuperHeroes: GetSuperHeroes

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    private fun tapSuperHeroItem(first: SuperHero) {
        composeTestRule.onNode(hasText(first.name)).performClick()
    }

    private fun renderScreen(onSuperHeroTapped: (SuperHero) -> Unit = {}) {
        val viewModel = SuperHeroListViewModel(getSuperHeroes)
        composeTestRule.setContent {
            KataSuperHeroesComposeTheme {
                SuperHeroListScreen(viewModel, onSuperHeroTapped)
            }
        }
    }

    private fun givenThereAreNoSuperHeroes() {
        whenever(getSuperHeroes()).thenReturn(emptyList())
    }
}
