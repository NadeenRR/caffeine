package com.nadeenrr.caffeine.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.extension.dropShadow
import com.nadeenrr.caffeine.navigation.Screen
import com.nadeenrr.caffeine.ui.theme.OffWhite
import com.nadeenrr.caffeine.ui.theme.Urbanist


@Composable
fun SnackScreen(
    navController: NavController
) {
    SnackScreenContent() {
        navController.navigate(Screen.HomeScreen.route)
    }

}

@Composable
private fun SnackScreenContent(onClickNext: () -> Unit) {
    val foodImages = listOf(
        R.drawable.sweet2,
        R.drawable.sweet3,
        R.drawable.sweet4,
        R.drawable.sweet6,
        R.drawable.sweet5,
        R.drawable.sweet1,
    )
    val listState = rememberLazyListState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, bottom = 24.dp)
                .size(48.dp)
                .background(OffWhite, shape = CircleShape)
                .align(Alignment.Start),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.cancel),
                contentDescription = "Profile image",
            )
        }


        Text(
            "Take your snack",
            fontSize = 22.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 24.dp)
        )

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy((-90).dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(foodImages) { index, imageRes ->

                val isCurrent =
                    listState.firstVisibleItemIndex == index ||
                            listState.firstVisibleItemIndex + 1 == index

                val rotation = if (index % 2 == 0) -6f else 6f

                val zIndex = if (isCurrent) 999f else index.toFloat()

                val verticalOffset = if (isCurrent) 0.dp else 30.dp

                Box(
                    modifier = Modifier
                        .height(275.dp)
                        .width(260.dp)
                        .offset(y = verticalOffset)
                        .absoluteOffset(x = (-30).dp)
                        .padding(horizontal = 8.dp)
                        .graphicsLayer {
                            rotationZ = rotation
                        }
                        .dropShadow(
                            blur = 20.dp,
                            color = Color(0x1F000000)
                        )
                        .background(
                            Color(0xFFF5F5F5),
                            RoundedCornerShape(bottomEnd = 32.dp, topEnd = 32.dp)
                        )
                        .zIndex(zIndex)
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(140.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyScreen() {
    SnackScreenContent({})

}