package com.nadeenrr.caffeine.navigation


sealed class Screen(val route: String){

    object HomeScreen: Screen("homeScreen")
    object OrderScreen: Screen("orderScreen")
    object DetailsOrderScreen: Screen("detailsOrderScreen")
    object CompletedOrderScreen: Screen("completedOrderScreen")

}