package com.omarhawari.starwarstrivia.domain.models

data class Character(
    val birthYear: String,
    val created: String,
    val edited: String,
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skinColor: String,
    val species: List<Any>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
)