package com.omarhawari.starwarstrivia.presentation.film_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    value: String,
    backgroundId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 0.dp,
        modifier = Modifier
            .background(Color.Transparent)
            .padding(12.dp)
            .width(150.dp)
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        backgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.4f),
    ) {

        Image(
            painter = painterResource(backgroundId),
            contentDescription = "Item",
            modifier = modifier
        )

        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.background.copy(alpha = 0.8f),
                        )
                    )
                )
        ) {
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}