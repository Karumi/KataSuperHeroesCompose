package com.karumi.ui.list

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.karumi.domain.model.SuperHero
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import junit.framework.TestCase.assertTrue
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

    @Test
    fun showsSuperHeroesWithLongNames() {
        val superHero = givenASuperHeroWithALongName()

        renderComponent(superHero)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsAvengersWithLongNames() {
        val superHero = givenASuperHeroWithALongName(isAvenger = true)

        renderComponent(superHero)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsSuperHeroesWithLongDescriptions() {
        val superHero = givenASuperHeroWithALongDescription()

        renderComponent(superHero)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsAvengersBadge() {
        val superHero = givenASuperHero(isAvenger = true)

        renderComponent(superHero)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun notifiesTapOnItemUsingTheLambdaPassedAsArgument() {
        val superHero = givenASuperHero(isAvenger = true)

        var itemTapped = false
        renderComponent(superHero) {
            itemTapped = true
        }
        tapItem(superHero)

        assertTrue(itemTapped)
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

    private fun givenASuperHeroWithALongDescription(): SuperHero {
        val superHeroName = "Super Hero Name"
        val superHeroDescription = """
            |Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            |incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
            |ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            |voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
            |proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            |""".trimMargin()
        val isAvenger = false
        return givenASuperHero(superHeroName, superHeroDescription, isAvenger)
    }

    private fun givenASuperHeroWithALongName(isAvenger: Boolean = false): SuperHero {
        val superHeroName = """
            |Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            |incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
            |ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            |voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
            |proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            |""".trimMargin()
        val superHeroDescription = "Description Super Hero"
        return givenASuperHero(superHeroName, superHeroDescription, isAvenger)
    }

    private fun givenASuperHero(
        superHeroName: String = "Super Hero Name",
        superHeroDescription: String = "Super Hero Description",
        isAvenger: Boolean = false
    ): SuperHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription)
}
