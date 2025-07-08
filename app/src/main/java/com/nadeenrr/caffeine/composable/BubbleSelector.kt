package com.nadeenrr.caffeine.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nadeenrr.caffeine.extension.dropShadow

@Composable
fun <T> BubbleSelector(
    items: List<T>,
    selectedIndex: Int,
    itemWidth: Dp = 40.dp,
    spacing: Dp = 8.dp,
    bubbleSize: Dp = 40.dp,
    modifier: Modifier = Modifier,
    bubbleColor: Color = Color(0xFF7C351B),
    shadowColor: Color = Color(0x80B94B23),
    backgroundColor: Color = Color(0xFFF5F5F5),
    onItemSelected: (T, Int) -> Unit,
    itemContent: @Composable (item: T, isSelected: Boolean) -> Unit,
) {
    val animatedOffsetX by animateDpAsState(
        targetValue = if (selectedIndex >= 0) (itemWidth + spacing) * selectedIndex else 0.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "Offset Animation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(backgroundColor)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            Box {
                if (selectedIndex >= 0) {
                    Box(
                        modifier = Modifier
                            .offset(x = animatedOffsetX)
                            .size(bubbleSize)
                            .dropShadow(blur = 12.dp, color = shadowColor)
                            .background(bubbleColor, CircleShape)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .size(itemWidth)
                            .clickable { onItemSelected(item, index) },
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(item, index == selectedIndex)
                    }
                }
            }

        }
    }
}