package com.nadeenrr.caffeine.screen

import android.graphics.BlurMaskFilter
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.AnimatedFadeImage
import com.nadeenrr.caffeine.composable.AnimatedUpBottomImage
import com.nadeenrr.caffeine.composable.CaffeineAppBar
import com.nadeenrr.caffeine.composable.CaffeineButton
import com.nadeenrr.caffeine.extension.dropShadow
import com.nadeenrr.caffeine.navigation.Screen
import com.nadeenrr.caffeine.ui.theme.Sniglet


@Composable
fun HomeScreen(
    navController: NavController,
) {
    HomeScreenContent(
        onClickNext = {
            navController.navigate(Screen.OrderScreen.route)
        }
    )
}


@Composable
private fun HomeScreenContent(
    onClickNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CaffeineAppBar(modifier = Modifier.padding(bottom = 24.dp))

        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
        ) {

            AnimatedFadeImage(modifier = Modifier.align(Alignment.TopEnd)) {
                Image(
                    painter = painterResource(R.drawable.start),
                    contentDescription = "star 1",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                )
            }

            Text(
                "Hocus\n" +
                        "Pocus\n" +
                        "I Need Coffee\n" +
                        "to Focus",
                fontSize = 32.sp,
                fontFamily = Sniglet,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color(0xDE1F1F1F),
                lineHeight = 50.sp
            )

            AnimatedFadeImage(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(y = -(25).dp, x = 8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.start),
                    contentDescription = "start 2",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }

            AnimatedFadeImage(modifier = Modifier.align(Alignment.BottomEnd)) {
                Image(
                    painter = painterResource(R.drawable.start),
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                )
            }
        }

        AnimatedUpBottomImage(
            initialValue = 0f,
            targetValue = 20f
        ) {
            Image(
                painter = painterResource(R.drawable.caffeine),
                contentDescription = "Caffeine image",
                modifier = Modifier
                    .size(250.dp)
            )
        }


        AnimatedUpBottomImage(
            initialValue = 40f,
            targetValue = 30f
        ) {
            Canvas(
                modifier = Modifier
                    .size(width = 177.dp, height = 27.dp)
                    .dropShadow(
                        blur = 20.dp,
                        color = Color(0x331F1F1F),
                    )
            ) {
                drawOval(
                    color = Color.Transparent,
                    size = size
                )
            }
//            Image(
//                painter = painterResource(R.drawable.ellipse),
//                "ellipse",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .size(width = 177.dp, height = 27.dp)
//                    .dropShadow(
//                        blur = 20.dp,
//                        color = Color(0x371F1F1F),
//                    )
//            )
//            Box(
//                modifier = Modifier
//                    .size(width = 177.dp, height = 27.dp)
//                    .dropShadow(
//                        blur = 20.dp,
//                        color = Color(0x371F1F1F),
//                    )
//            )
        }

        Spacer(modifier = Modifier.weight(1f))

        CaffeineButton(
            onClickNext = onClickNext,
            text = "bring my coffee",
            image = painterResource(R.drawable.coffee),
            contentDescription = "Caffeine image",
            onClick = {}
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(onClickNext = {})
}

