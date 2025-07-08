package com.nadeenrr.caffeine.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.CaffeineAppBar
import com.nadeenrr.caffeine.composable.CaffeineButton
import com.nadeenrr.caffeine.model.CoffeeItem
import com.nadeenrr.caffeine.navigation.Screen
import com.nadeenrr.caffeine.ui.theme.Urbanist
import kotlin.math.absoluteValue

@Composable
fun OrderScreen(
    navController: NavController,
) {
    OrderScreenContent(
        onClickNext = { navController.navigate(Screen.DetailsOrderScreen.route) }
    )
}

@Composable
private fun OrderScreenContent(onClickNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(top = 12.dp),
    ) {
        CaffeineAppBar(modifier = Modifier.padding(bottom = 16.dp), isAnimated = true)

        Text(
            "Good Morning",
            fontSize = 36.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB3B3B3),
            modifier = Modifier.padding(start = 16.dp)

        )

        Text(
            "Nadeen ☀",
            fontSize = 36.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3B3B3B),
            modifier = Modifier.padding(start = 16.dp)

        )

        Text(
            "What would you like to drink today?",
            fontSize = 16.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            color = Color(0xCC1F1F1F),
            modifier = Modifier.padding(top = 4.dp, bottom = 56.dp, start = 16.dp)
        )


        val coffeeItems = listOf(
            CoffeeItem(painterResource(R.drawable.black), "Black"),
            CoffeeItem(painterResource(R.drawable.latte), "Latte"),
            CoffeeItem(painterResource(R.drawable.macchiato), "Macchiato"),
            CoffeeItem(painterResource(R.drawable.espresso), "Espresso")
        )

        val pagerState = rememberPagerState(pageCount = { coffeeItems.size })

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 68.dp),
            pageSpacing = 16.dp,
            reverseLayout = true,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val item = coffeeItems[page]
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

            val scale = lerp(start = 0.99f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))

            val verticalOffset = lerp(150f, 0f, 1f - pageOffset.coerceIn(0f, 1f))

            Column(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationY = verticalOffset
                    }
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = item.image,
                    contentDescription = "coffee",
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                )
                Text(
                    item.title,
                    fontSize = 32.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
        Spacer(Modifier.weight(1f))

        var visible by remember { mutableStateOf(true) }

        AnimatedVisibility(
            visible = visible,
            exit = slideOutHorizontally(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            CaffeineButton(
                onClick = { visible = !visible },
                onClickNext = onClickNext,
                text = "Continue",
                image = painterResource(R.drawable.arrow_right),
                contentDescription = "Caffeine image",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}

@Preview(
    name = "Nadeen",
    showSystemUi = true,
    showBackground = true,
//    widthDp = 480,
//    heightDp = 1040,
)
@Composable
private fun OrderScreenContentPreview() {
    OrderScreenContent(onClickNext = {})
}