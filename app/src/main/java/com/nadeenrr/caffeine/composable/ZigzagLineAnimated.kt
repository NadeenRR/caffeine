package com.nadeenrr.caffeine.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedWaveLine(
    modifier: Modifier = Modifier,
    onAnimationEnd: () -> Unit
) {
    val animationProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            1f,
            animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
        )
        animationProgress.animateTo(
            0f,
            animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
        )
        onAnimationEnd()
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val width = size.width
        val height = size.height

        val waveLength = width / 5
        val amplitude = height / 4
        val path = Path()

        path.moveTo(0f, height / 2)

        var x = 0f
        while (x < width) {
            path.relativeQuadraticBezierTo(
                waveLength / 4, -amplitude,
                waveLength / 2, 0f
            )
            path.relativeQuadraticBezierTo(
                waveLength / 4, amplitude,
                waveLength / 2, 0f
            )
            x += waveLength
        }

        val measure = PathMeasure()
        measure.setPath(path, false)

        val clippedPath = Path()
        measure.getSegment(0f, measure.length * animationProgress.value, clippedPath, true)

        drawPath(
            path = clippedPath,
            color = Color.Black,
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun ZigzagLineAnimatedPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AnimatedWaveLine(
            modifier = Modifier
                .align(Alignment.Center),
            onAnimationEnd = {

            }
        )
    }
}