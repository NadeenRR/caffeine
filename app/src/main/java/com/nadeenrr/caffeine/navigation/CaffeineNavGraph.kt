package com.nadeenrr.caffeine.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nadeenrr.caffeine.screen.CompletedOrderScreen
import com.nadeenrr.caffeine.screen.DetailsOrderScreen
import com.nadeenrr.caffeine.screen.HomeScreen
import com.nadeenrr.caffeine.screen.OrderScreen

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
//        detailsOrderRoute(navController)
//        composable(Screen.CompletedOrderScreen.route) {
//            CompletedOrderScreen(navController)
//        }
    }

}