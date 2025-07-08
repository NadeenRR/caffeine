package com.nadeenrr.caffeine.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun AnimatedFadeImage(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(alpha) {
        while (true) {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1000)
            )

            alpha.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

    Box(modifier = modifier.alpha(alpha.value)) {
        content()
    }
}