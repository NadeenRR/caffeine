package com.nadeenrr.caffeine.navigation

import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nadeenrr.caffeine.screen.CompletedOrderScreen


private const val ROUTE = "completedOrderScreen"

fun NavController.navigateToCompletedOrder(size: Int, volume: String) {
    navigate("$ROUTE/$size/$volume")

}

fun NavGraphBuilder.completedOrderRoute(navController: NavController) {
    composable(
        "$ROUTE/{${CompletedOrderArgs.CAP_SIZE_ARG}}/{${CompletedOrderArgs.VOLUME_ARG}}",
        arguments = listOf(
            navArgument(CompletedOrderArgs.CAP_SIZE_ARG) { NavType.IntType },
            navArgument(CompletedOrderArgs.VOLUME_ARG) { NavType.StringType },
        )
    ) { backArgs ->
        val capSizeInt = backArgs.arguments?.getInt(CompletedOrderArgs.CAP_SIZE_ARG) ?: 0
        val volume = backArgs.arguments?.getString(CompletedOrderArgs.VOLUME_ARG) ?: ""
        val capSize = capSizeInt.dp

        CompletedOrderScreen(
            navController = navController,
            capSize = capSize,
            volume = volume
        )

    }
}

class CompletedOrderArgs(savedStateHandle: SavedStateHandle) {
    companion object {
        const val CAP_SIZE_ARG = "capSize"
        const val VOLUME_ARG = "volume"
    }
}