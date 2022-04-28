package com.omarhawari.starwarstrivia.domain.models

data class Character(
    val birthYear: String,
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val name: String,
    val url: String,
)