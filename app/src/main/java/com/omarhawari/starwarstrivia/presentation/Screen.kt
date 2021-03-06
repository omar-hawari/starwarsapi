package com.omarhawari.starwarstrivia.presentation

sealed class Screen(val route: String) {

    object FilmsScreen : Screen("films_screen")
    object FilmDetailScreen : Screen("film_detail_screen")

}