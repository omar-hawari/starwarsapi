package com.omarhawari.starwarstrivia.presentation.film_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KeyValueItem(key: String, value: String, modifier: Modifier) {

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = key, modifier = Modifier.weight(1f))
        Text(text = value)
    }

}