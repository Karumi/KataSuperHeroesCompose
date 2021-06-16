package com.karumi.ui.detail

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.karumi.core.ui.SuperHeroesTestTags
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroByName
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import com.karumi.utils.MockedTest
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class SuperHeroDetailScreenScreenshotTests : ScreenshotTest, MockedTest {

    @Mock
    private lateinit var getSuperHero: GetSuperHeroByName

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsAvengersBadgeIfSuperHeroIsPartOfTheAvengersTeam() {
        val superHero = givenThereIsASuperHero(isAvenger = true)

        renderScreen(superHero.name)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun doesNotShowAvengersBadgeIfSuperHeroIsNotPartOfTheAvengersTeam() {
        val superHero = givenThereIsASuperHero(isAvenger = false)

        renderScreen(superHero.name)

        compareScreenshot(composeTestRule)
    }

    @Test
    fun notifiesTapOnBacButton() {
        val superHero = givenThereIsASuperHero(isAvenger = false)

        var backButtonTapped = false
        renderScreen(superHero.name) {
            backButtonTapped = true
        }
        tapBackButton()

        assertTrue(backButtonTapped)
    }

    private fun tapBackButton() {
        composeTestRule.onNode(hasTestTag(SuperHeroesTestTags.backButton)).performClick()
    }

    private fun renderScreen(superHeroName: String, onBackButtonTapped: () -> Unit = {}) {
        val viewModel = SuperHeroDetailViewModel(getSuperHero)
        viewModel.superHeroName = superHeroName
        composeTestRule.setContent {
            KataSuperHeroesComposeTheme {
                SuperHeroDetailScreen(viewModel, onBackButtonTapped)
            }
        }
    }

    private fun givenThereIsASuperHero(isAvenger: Boolean): SuperHero {
        val superHeroName = "SuperHero"
        val superHeroDescription = "Super Hero Description"
        val superHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription)
        whenever(getSuperHero(superHeroName)).thenReturn(superHero)
        return superHero
    }
}
