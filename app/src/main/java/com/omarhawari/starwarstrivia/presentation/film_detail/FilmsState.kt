package com.omarhawari.starwarstrivia.presentation.film_detail

import com.omarhawari.starwarstrivia.domain.models.Film

class FilmDetailState(
    val isLoading: Boolean = false,
    val film: Film? = null,
    val error: String = ""
) {
    override fun toString(): String {
        return "FilmDetailState(isLoading=$isLoading, film=$film, error='$error')"
    }
}