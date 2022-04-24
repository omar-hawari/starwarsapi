package com.omarhawari.starwarstrivia.data.remote.dto

import com.omarhawari.starwarstrivia.domain.models.Planet

data class PlanetDto(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    val population: String,
    val residents: List<String>,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
    val url: String
)

fun PlanetDto.toPlanet() = Planet(
    climate,
    created,
    diameter,
    edited,
    films,
    gravity,
    name,
    orbital_period,
    population,
    residents,
    rotation_period,
    surface_water,
    terrain,
    url
)