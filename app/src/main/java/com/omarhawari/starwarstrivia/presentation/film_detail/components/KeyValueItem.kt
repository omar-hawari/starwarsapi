package com.omarhawari.starwarstrivia.presentation.film_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyValueItem(key: String, value: String, modifier: Modifier) {

    Column(verticalArrangement = Arrangement.Center) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "$key:", modifier = Modifier.weight(1f), fontSize = 20.sp)
            Text(
                text = value,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(1.dp)
                .alpha(0.5f)
                .background(MaterialTheme.colors.onSurface)
        )
    }

}