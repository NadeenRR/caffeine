package com.nadeenrr.caffeine.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nadeenrr.caffeine.R
import com.nadeenrr.caffeine.ui.theme.OffWhite

@Composable
fun CaffeineAppBar(
    modifier: Modifier = Modifier,
    isAnimated: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.profile_image),
            contentDescription = "Profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        if (isAnimated) {
            val visible by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = visible,
                exit = shrinkHorizontally(),
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(OffWhite, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.add),
                        contentDescription = "Profile image",
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(OffWhite, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.add),
                    contentDescription = "Profile image",
                )
            }
        }
    }
}