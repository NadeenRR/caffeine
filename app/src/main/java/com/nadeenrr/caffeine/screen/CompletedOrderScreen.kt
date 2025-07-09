package com.nadeenrr.caffeine.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.AnimatedWaveLine
import com.nadeenrr.caffeine.navigation.Screen
import com.nadeenrr.caffeine.ui.theme.Sniglet
import com.nadeenrr.caffeine.ui.theme.Urbanist

@Composable
fun CompletedOrderScreen(
    navController: NavController,
    capSize: Dp,
    volume: String
) {
    CompletedOrderScreenContent(
        capSize = capSize,
        volume = volume,
        onClickNext = { navController.navigate(Screen.ReadyCoffeeScreen.route) }
    )
}

@Composable
fun CompletedOrderScreenContent(
    capSize: Dp,
    volume: String,
    onClickNext: () -> Unit
) {

    var showFadeIn by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (showFadeIn) 0f else 1f,
        animationSpec = tween(1000),
        label = "FadeInAlpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(top = 12.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Box(
            modifier = Modifier
                .alpha(alpha)
                .height(400.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                volume,
                fontSize = 14.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 45.dp)
                    .padding(end = 18.dp)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.cap),
                    contentDescription = "",
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(R.drawable.thechance),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(Modifier.weight(1f))

        AnimatedWaveLine(
            modifier = Modifier.padding(bottom = 18.dp),
            onAnimationEnd = {
                showFadeIn = true
                onClickNext()
            }
        )

        Text(
            "Almost Done",
            fontSize = 22.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .alpha(alpha)
        )

        Text(
            "Your coffee will be finish in",
            fontSize = 16.sp,
            fontFamily = Urbanist,
            color = Color(0x991F1F1F),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .alpha(alpha)
                .padding(top = 8.dp, end = 12.dp)

        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .alpha(alpha)
                .padding(top = 8.dp, end = 18.dp)
        ) {
            listOf("CO", ":", "FF", ":", "EE").forEachIndexed { index, text ->
                Text(
                    text = text,
                    fontSize = 32.sp,
                    fontFamily = Sniglet,
                    color = if (index % 2 == 0) Color(0xFF7C351B) else Color(0x1F1F1F1F),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun OrderDoneContentPreview() {
    CompletedOrderScreenContent(onClickNext = {}, capSize = 300.dp, volume = "200l")
}