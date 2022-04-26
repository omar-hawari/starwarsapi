package com.omarhawari.starwarstrivia.presentation.character_detail

import com.omarhawari.starwarstrivia.domain.models.*

class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val films: ArrayList<Pair<Int, Film>> = arrayListOf(),
    val error: String = ""
) {

    val filmsParsed = films.map { it.second }

    fun addFilm(film: Film, index: Int): CharacterDetailState {
        films.add(Pair(index, film))
        films.sortBy { it.first }
        return copyWith(films = films)
    }

    private fun copyWith(
        isLoading: Boolean = this.isLoading,
        character: Character? = this.character,
        films: ArrayList<Pair<Int, Film>> = this.films,
        error: String = this.error
    ): CharacterDetailState {
        return CharacterDetailState(
            isLoading = isLoading,
            character = character,
            films = films,
            error = error,
        )
    }


    override fun toString(): String {
        return "CharacterDetailState(isLoading=$isLoading, character=$character, error='$error')"
    }
}