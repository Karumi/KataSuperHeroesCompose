package com.karumi.ui.list

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import com.karumi.domain.model.SuperHero
import com.karumi.domain.usecase.GetSuperHeroes
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.theme.KataSuperHeroesComposeTheme
import com.karumi.utils.MockedTest
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class SuperHeroListScreenScreenshotTests : ScreenshotTest, MockedTest {

    companion object {
        const val ANY_NUMBER_OF_SUPER_HEROES = 100
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Mock
    lateinit var getSuperHeroes: GetSuperHeroes

    private lateinit var viewModel: SuperHeroListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = SuperHeroListViewModel(getSuperHeroes)
    }

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsJustOneSuperHero() {
        givenThereAreSomeSuperHeroes(1)

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsSuperHeroesIfThereAreSomeSuperHeroes() {
        givenThereAreSomeSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES)

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    @Test
    fun showsAvengersBadgeIfASuperHeroIsPartOfTheAvengersTeam() {
        givenThereAreSomeAvengers(ANY_NUMBER_OF_SUPER_HEROES)

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    @Test
    fun doesNotShowAvengersBadgeIfASuperHeroIsNotPartOfTheAvengersTeam() {
        givenThereAreSomeSuperHeroes(ANY_NUMBER_OF_SUPER_HEROES)

        renderScreen()

        compareScreenshot(composeTestRule)
    }

    @Test
    fun notifiesTapOnSuperHero() {
        val superHeroes = givenThereAreSomeSuperHeroes()
        val superHeroToTap = superHeroes.first()

        var superHeroTapped: SuperHero? = null
        renderScreen {
            superHeroTapped = it
        }
        tapSuperHeroItem(superHeroes.first())

        assertEquals(superHeroToTap, superHeroTapped)
    }

    private fun tapSuperHeroItem(first: SuperHero) {
        composeTestRule.onNode(hasText(first.name)).performClick()
    }

    private fun renderScreen(onSuperHeroTapped: (SuperHero) -> Unit = {}) {
        composeTestRule.setContent {
            KataSuperHeroesComposeTheme {
                SuperHeroListScreen(viewModel, onSuperHeroTapped)
            }
        }
    }

    private fun givenThereAreSomeAvengers(numberOfAvengers: Int): List<SuperHero> =
        givenThereAreSomeSuperHeroes(numberOfAvengers, avengers = true)

    private fun givenThereAreSomeSuperHeroes(
        numberOfSuperHeroes: Int = 1,
        avengers: Boolean = false
    ): List<SuperHero> {
        val superHeroes = IntRange(0, numberOfSuperHeroes - 1).map { id ->
            val superHeroName = "SuperHero - $id"
            val superHeroDescription = "Description Super Hero - $id"
            SuperHero(
                superHeroName, null, avengers,
                superHeroDescription
            )
        }
        whenever(getSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreNoSuperHeroes() {
        whenever(getSuperHeroes()).thenReturn(emptyList())
    }
}
