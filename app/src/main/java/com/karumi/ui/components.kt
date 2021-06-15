package com.karumi.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.karumi.ui.detail.SuperHeroDetailScreen
import com.karumi.ui.list.SuperHeroListScreen

sealed class Routes(val path: String) {
    object List : Routes("list")
    object Detail : Routes("detail/{superHeroId}") {
        const val superHeroIdArgName: String = "superHeroId"
        fun pathFor(superHeroId: String) = "detail/$superHeroId"
    }
}

@Composable
fun SuperHeroApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.List.path) {
        SuperHeroListNavRoute(navController)
        SuperHeroDetailNavRoute(navController)
    }
}

private fun NavGraphBuilder.SuperHeroListNavRoute(navController: NavHostController) {
    composable(Routes.List.path) {
        SuperHeroListScreen(navController)
    }
}

private fun NavGraphBuilder.SuperHeroDetailNavRoute(navController: NavHostController) {
    composable(
        Routes.Detail.path,
        arguments = listOf(
            navArgument(Routes.Detail.superHeroIdArgName) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val superHeroId = backStackEntry.arguments?.getString(Routes.Detail.superHeroIdArgName) ?: ""
        SuperHeroDetailScreen(
            superHeroId,
            navController
        )
    }
}
