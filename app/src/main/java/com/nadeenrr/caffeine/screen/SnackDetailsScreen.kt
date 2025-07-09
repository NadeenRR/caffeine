package com.nadeenrr.caffeine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nadeenrr.caffeine.navigation.Screen

@Composable
fun SnackDetailsScreen(navController: NavController) {
    SnackDetailsScreenContent() {
        navController.navigate(Screen.HomeScreen.route)
    }
}

@Composable
private fun SnackDetailsScreenContent(onClickNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }

}