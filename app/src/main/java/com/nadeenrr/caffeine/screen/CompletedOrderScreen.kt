package com.nadeenrr.caffeine.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.AnimatedWaveLine
import com.nadeenrr.caffeine.navigation.CompletedOrderArgs
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
        onClickNext = { navController.navigateUp() }
    )
}

@Composable
fun CompletedOrderScreenContent(
    capSize: Dp,
    volume: String,
    onClickNext: () -> Unit
) {
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
                        .size(capSize)
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
            modifier = Modifier.padding(bottom = 18.dp)
        )


        Text(
            "Almost Done",
            fontSize = 22.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
        )

        Text(
            "Your coffee will be finish in",
            fontSize = 16.sp,
            fontFamily = Urbanist,
            color = Color(0x991F1F1F),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp, end = 12.dp)
        )

        Text(
            "CO : FF : EE",
            fontSize = 32.sp,
            fontFamily = Sniglet,
            color = Color(0xFF7C351B),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp, end = 18.dp)
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun OrderDoneContentPreview() {
    // CompletedOrderScreenContent(onClickNext = {})
}