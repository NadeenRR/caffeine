package com.nadeenrr.caffeine.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nadeenrr.caffeine.screen.DetailsOrderScreen
import com.nadeenrr.caffeine.screen.HomeScreen
import com.nadeenrr.caffeine.screen.OrderScreen
import com.nadeenrr.caffeine.screen.ReadyCoffeeScreen
import com.nadeenrr.caffeine.screen.SnackDetailsScreen
import com.nadeenrr.caffeine.screen.SnackScreen

@Composable
fun CaffeineNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                navController
            )
        }
        composable(Screen.OrderScreen.route) {
            OrderScreen(navController)
        }

        composable(Screen.DetailsOrderScreen.route) {
            DetailsOrderScreen(navController)
        }

        completedOrderRoute(navController)

        composable(Screen.ReadyCoffeeScreen.route) {
            ReadyCoffeeScreen(navController)
        }

        composable(Screen.SnackScreen.route) {
            SnackScreen(navController)
        }

        composable(Screen.SnackDetailsScreen.route) {
            SnackDetailsScreen(navController)
        }
//        detailsOrderRoute(navController)
//        composable(Screen.CompletedOrderScreen.route) {
//            CompletedOrderScreen(navController)
//        }
    }

}