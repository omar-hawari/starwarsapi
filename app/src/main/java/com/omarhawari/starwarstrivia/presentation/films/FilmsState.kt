package com.omarhawari.starwarstrivia.presentation.films

import com.omarhawari.starwarstrivia.domain.models.Film

class FilmsState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList(),
    val error: String = ""
) {
    override fun toString(): String {
        return "FilmsState(isLoading=$isLoading, films=$films, error='$error')"
    }
}