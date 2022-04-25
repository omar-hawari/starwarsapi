package com.omarhawari.starwarstrivia.presentation.character_detail

import com.omarhawari.starwarstrivia.domain.models.Character

class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String = ""
) {
    override fun toString(): String {
        return "CharacterDetailState(isLoading=$isLoading, character=$character, error='$error')"
    }
}