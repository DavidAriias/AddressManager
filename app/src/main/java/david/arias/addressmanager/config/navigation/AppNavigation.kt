package david.arias.addressmanager.config.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import david.arias.addressmanager.presentation.screens.AddressDetailScreen
import david.arias.addressmanager.presentation.screens.AddressListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.AddressList.route
    ) {

        composable(
            route = AppScreens.AddressList.route
        ) {
            AddressListScreen(navController)
        }

        composable(
            route = "${AppScreens.AddressDetail.route}/{addressId}",
            arguments = listOf(
                navArgument("addressId") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            AddressDetailScreen(
                navController = navController,
            )
        }
    }
}