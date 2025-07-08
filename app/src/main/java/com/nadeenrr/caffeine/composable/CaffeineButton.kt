package com.nadeenrr.caffeine.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadeenrr.caffeine.extension.dropShadow

@Composable
fun CaffeineButton(
    onClickNext: () -> Unit,
    onClick: () -> Unit,
    text: String,
    contentDescription: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(bottom = 18.dp)
            .background(
                Color(0xFF1F1F1F),
                shape = RoundedCornerShape(100.dp)
            )
            .dropShadow(
                blur = 12.dp,
                offsetY = 6.dp,
                color = Color(0x3D000000)
            )
            .clickable {
                onClickNext()
                onClick()
            }
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xDEFFFFFF),
        )
        Image(
            painter = image,
            contentDescription = contentDescription
        )
    }
}