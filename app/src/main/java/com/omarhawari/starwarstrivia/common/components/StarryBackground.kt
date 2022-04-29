package com.omarhawari.starwarstrivia.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val starOffsets = arrayListOf<Pair<Dp, Dp>>()

@Composable
fun StarryBackground(modifier: Modifier = Modifier) {

    val starsColor = if (MaterialTheme.colors.isLight) Color.Black else Color.White

    if (starOffsets.isEmpty()) {
        (0..500).onEach {
            starOffsets.add(
                Pair(
                    (0..1000).random().dp,
                    (0..1000).random().dp
                )
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize(), onDraw = {
        drawPoints(
            starOffsets.map { Offset(it.first.value * 3, it.second.value * 3) },
            pointMode = PointMode.Points,
            color = starsColor,
            strokeWidth = 2f
        )
    })

//    Box(
//        modifier = modifier
//            .fillMaxSize()
//    ) {
//        repeat(2000) {
//            if (starOffsets.size < it + 1)
//                starOffsets.add(
//                    Pair(
//                        (0..1000).random().dp,
//                        (0..1000).random().dp
//                    )
//                )
//            Box(
//                modifier = Modifier
//                    .offset(
//                        x = starOffsets[it].first,
//                        y = starOffsets[it].second
//                    )
//                    .height(1.dp)
//                    .width(1.dp)
//                    .background(if (MaterialTheme.colors.isLight) Color.Black else Color.White)
//                    .alpha(0.1f)
//            )
//        }
//    }

}