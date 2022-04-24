package com.omarhawari.starwarstrivia.presentation.films.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.presentation.Screen

@Composable
fun FilmItem(
    film: Film, navController: NavController, onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick() },
        backgroundColor = Color.Gray
    ) {
        Text(text = film.title, modifier = Modifier.padding(12.dp))

    }
}