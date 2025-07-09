package com.nadeenrr.caffeine.screen

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.CaffeineButton
import com.nadeenrr.caffeine.extension.dropShadow
import com.nadeenrr.caffeine.navigation.Screen
import com.nadeenrr.caffeine.navigation.navigateToCompletedOrder
import com.nadeenrr.caffeine.ui.theme.LightBrown
import com.nadeenrr.caffeine.ui.theme.OffWhite
import com.nadeenrr.caffeine.ui.theme.Urbanist


@Composable
fun ReadyCoffeeScreen(
    navController: NavController
) {
    ReadyCoffeeScreenContent() {
        navController.navigate(Screen.SnackScreen.route)
    }
}

@Composable
private fun ReadyCoffeeScreenContent(onClickNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, bottom = 16.dp)
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

        Box(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(56.dp)
                .dropShadow(blur = 16.dp, color = Color(0x80B94B23))
                .background(Color(0xFF7C351B), CircleShape)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.tick),
                contentDescription = "Profile image",
            )
        }

        Text(
            "Your coffee is ready\nEnjoy",
            fontSize = 22.sp,
            fontFamily = Urbanist,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )


        var dropLid by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) { dropLid = true }

        val lidOffsetY by animateDpAsState(
            targetValue = if (dropLid) 0.dp else (-2000).dp,
            animationSpec = keyframes {
                durationMillis = 1200
                (-20).dp at 1000
                0.dp at 1200
            },
            label = "LidDropOffset"
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .height(340.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center,
        ) {

            Image(
                painter = painterResource(R.drawable.cover),
                contentDescription = "",
                modifier = Modifier
                    .width(260.dp)
                    .offset(y = lidOffsetY)
                    .align(Alignment.TopCenter)
                    .zIndex(1f),
            )
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

            var isCoffeeOn by remember { mutableStateOf(false) }

            CoffeeSwitch(
                isOn = isCoffeeOn,
                onToggle = { isCoffeeOn = it },
            )

            Text(
                "Take Away",
                fontSize = 14.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xB21F1F1F)
            )
        }

        Spacer(Modifier.weight(1f))

        CaffeineButton(
            onClickNext = onClickNext,
            text = "Take snack",
            image = painterResource(R.drawable.arrow_right),
            contentDescription = "Caffeine image",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {}
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ReadyCoffeeScreenPreview() {
//    ReadyCoffeeScreenContent({})
}

@Composable
fun CoffeeSwitch(
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = isOn, label = "CoffeeSwitch")

    val thumbOffset by transition.animateDp(
        label = "ThumbOffset",
        transitionSpec = { tween(durationMillis = 400, easing = FastOutSlowInEasing) }
    ) { state -> if (state) 0.dp else 33.dp }

    val backgroundColor by transition.animateColor(label = "BackgroundColor") { state ->
        if (state) Color(0xFF7C351B) else Color(0xFFFFEEE7)
    }

    val labelColor by transition.animateColor(label = "LabelColor") { state ->
        if (state) LightBrown else Color(0x1F1F1F1F)
    }

    Box(
        modifier = modifier
            .width(80.dp)
            .height(45.dp)
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .clickable { onToggle(!isOn) }
            .padding(start = 6.dp, end = 6.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = if (isOn) "ON" else "OFF",
            color = labelColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(if (isOn) Alignment.CenterEnd else Alignment.CenterStart)
        )

        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(40.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.caffee_switch),
                contentDescription = "Coffee Cup",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
