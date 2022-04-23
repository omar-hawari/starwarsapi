package com.omarhawari.starwarstrivia.presentation.films.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.omarhawari.starwarstrivia.domain.models.Film

@Composable
fun FilmItem(
    film: Film, navController: NavController
) {
    Box(modifier = Modifier) {

        Text(text = film.title)

    }
}