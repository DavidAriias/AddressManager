package david.arias.catgallery.config.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import david.arias.catgallery.presentation.screens.BreedSelectionScreen
import david.arias.catgallery.presentation.screens.CatGalleryScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.BreedSelection.route
    ) {

        composable(
            route = AppScreens.BreedSelection.route
        ) {

            BreedSelectionScreen(navController)
        }

        composable(
            route = "${AppScreens.CatGallery.route}/{breedId}/{limit}",
            arguments = listOf(
                navArgument("breedId") {
                    type = NavType.StringType
                },
                navArgument("limit") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            CatGalleryScreen(navController)
        }
    }
}