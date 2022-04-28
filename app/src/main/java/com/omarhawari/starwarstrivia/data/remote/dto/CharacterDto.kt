package com.omarhawari.starwarstrivia.data.remote.dto

import com.omarhawari.starwarstrivia.domain.models.Character

data class CharacterDto(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)

fun CharacterDto.toCharacter() = Character(
    birth_year,
    eye_color,
    films,
    gender,
    hair_color,
    name,
    url
)