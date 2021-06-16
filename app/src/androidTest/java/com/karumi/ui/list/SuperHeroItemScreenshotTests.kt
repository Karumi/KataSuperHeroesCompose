package com.karumi.ui.list

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.karumi.domain.model.SuperHero
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import org.junit.Rule
import org.junit.Test

class SuperHeroItemScreenshotTests : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsAnySuperHero() {
        val superHero = givenASuperHero()

        renderComponent(superHero)

        compareScreenshot(composeTestRule)
    }

    private fun tapItem(superHero: SuperHero) {
        composeTestRule.onNode(hasText(superHero.name)).performClick()
    }

    private fun renderComponent(
        superHero: SuperHero,
        onSuperHeroTapped: (SuperHero) -> Unit = {}
    ) {
        composeTestRule.setContent {
            KataSuperHeroesComposeTheme {
                SuperHeroItem(superHero, onSuperHeroTapped)
            }
        }
    }

    private fun givenASuperHero(
        superHeroName: String = "Super Hero Name",
        superHeroDescription: String = "Super Hero Description",
        isAvenger: Boolean = false
    ): SuperHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription)
}
