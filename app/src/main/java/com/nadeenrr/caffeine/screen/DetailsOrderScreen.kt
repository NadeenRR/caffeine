package com.nadeenrr.caffeine.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.composable.BubbleSelector
import com.nadeenrr.caffeine.composable.CaffeineButton
import com.nadeenrr.caffeine.navigation.navigateToCompletedOrder
import com.nadeenrr.caffeine.ui.theme.OffWhite
import com.nadeenrr.caffeine.ui.theme.Urbanist
import kotlinx.coroutines.launch

@Composable
fun DetailsOrderScreen(
    navController: NavController
) {
    DetailsOrderScreenContent(
        onClickNext = { size, volume ->
            navController.navigateToCompletedOrder(size, volume)
        }
    )
}

@Composable
fun DetailsOrderScreenContent(onClickNext: (Int, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .padding(top = 12.dp),
    ) {
        var visible by remember { mutableStateOf(true) }

        AnimatedVisibility(
            visible = visible,
            exit = shrinkVertically(),
            modifier = Modifier.align(Alignment.Start)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(OffWhite, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Profile image",
                    )
                }
                Text(
                    "Macchiato",
                    fontSize = 24.sp,
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

        val sizes = listOf("S", "M", "L")
        val volumes = listOf("150 ML", "200 ML", "400 ML")
        val imageSizes = listOf(190.dp, 245.dp, 300.dp)
        val logoSizes = listOf(40.dp, 66.dp, 66.dp)

        val beansSize = listOf(100.dp, 150.dp, 180.dp)
        var selectedSizeIndex by remember { mutableIntStateOf(1) }

        val animatedSize by animateDpAsState(
            targetValue = imageSizes[selectedSizeIndex],
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            label = "Image Size Animation"
        )

        val animatedLogoSize by animateDpAsState(
            targetValue = logoSizes[selectedSizeIndex],
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            label = "logo Size Animation"
        )

        var selectedStrengthIndex by remember { mutableIntStateOf(0) }
        var beansVisible by remember { mutableStateOf(false) }

        val startY = -2000f
        val endY = 50f
        val beanOffsetY = remember { Animatable(startY) }
        val beanAlpha = remember { Animatable(0f) }
        val animatedBeansSize by animateDpAsState(
            targetValue = beansSize[selectedSizeIndex],
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            label = "beans Size Animation"
        )

        LaunchedEffect(selectedStrengthIndex, beansVisible) {
            if (beansVisible) {
                beanOffsetY.snapTo(startY)
                beanAlpha.snapTo(1f)

                launch {
                    beanOffsetY.animateTo(
                        endY,
                        animationSpec = tween(700, easing = LinearOutSlowInEasing)
                    )
                }
                launch {
                    beanAlpha.animateTo(
                        0.5f,
                        animationSpec = tween(700, easing = LinearOutSlowInEasing)
                    )
                }
            } else {
                beanOffsetY.snapTo(0f)
                beanAlpha.snapTo(0.5f)
                launch {
                    beanOffsetY.animateTo(
                        startY,
                        animationSpec = tween(700, easing = LinearOutSlowInEasing)
                    )
                }
                launch {
                    beanAlpha.animateTo(
                        1f,
                        animationSpec = tween(700, easing = LinearOutSlowInEasing)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 16.dp),
        ) {
            Text(
                volumes[selectedSizeIndex],
                fontSize = 14.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(y = 80.dp)
                    .padding(end = 18.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.bon_beans),
                        contentDescription = "Coffee Beans",
                        modifier = Modifier
                            .offset { IntOffset(0, beanOffsetY.value.toInt()) }
                            .alpha(beanAlpha.value)
                            .size(animatedBeansSize)
                            .align(Alignment.TopCenter)
                    )
                    Image(
                        painter = painterResource(R.drawable.cap),
                        contentDescription = "",
                        modifier = Modifier
                            .size(animatedSize)
                            .align(Alignment.Center)
                    )
                    Image(
                        painter = painterResource(R.drawable.thechance),
                        contentDescription = "",
                        modifier = Modifier
                            .size(animatedLogoSize)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        BubbleSelector(
            items = sizes,
            selectedIndex = selectedSizeIndex,
            onItemSelected = { _, index -> selectedSizeIndex = index },
        ) { size, isSelected ->
            Text(
                text = size,
                fontSize = 20.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else Color(0x991F1F1F)
            )
        }


        val strengths = listOf("Low", "Medium", "High")

        BubbleSelector(
            items = strengths,
            selectedIndex = selectedStrengthIndex,
            onItemSelected = { _, index ->
                if (selectedStrengthIndex == index) {
                    beansVisible = !beansVisible
                } else {
                    selectedStrengthIndex = index
                    beansVisible = true
                }
            }
        ) { _, isSelected ->
            if (isSelected) {
                Icon(
                    painter = painterResource(R.drawable.coffee_beans),
                    contentDescription = null,
                    tint = if (isSelected) Color.White else Color(0x991F1F1F),
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                38.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Text(
                text = "Low",
                fontSize = 10.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                color = Color(0x991F1F1F)
            )

            Text(
                text = "Medium",
                fontSize = 10.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                color = Color(0x991F1F1F)
            )

            Text(
                text = "High",
                fontSize = 10.sp,
                fontFamily = Urbanist,
                fontWeight = FontWeight.Medium,
                color = Color(0x991F1F1F)
            )
        }

        Spacer(Modifier.weight(1f))

        AnimatedVisibility(
            visible = visible,
            exit = slideOutVertically(),
            enter = fadeIn(),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            CaffeineButton(
                onClickNext = {
                    val selectedSize = imageSizes[selectedSizeIndex].value.toInt()
                    val selectedVolume = volumes[selectedSizeIndex]
                    Log.d("SelectedSize", "size = $selectedSize, volume = $selectedVolume")
                    onClickNext(selectedSize, selectedVolume)
                },
                text = "Continue",
                image = painterResource(R.drawable.arrow_right),
                contentDescription = "Caffeine image",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { visible = !visible }
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DetailsOrderScreenContentPreview() {
    DetailsOrderScreenContent(onClickNext = { size, v -> })
}
