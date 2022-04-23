package com.omarhawari.starwarstrivia.data.remote.dto

import com.omarhawari.starwarstrivia.domain.models.Film

data class FilmDto(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episode_id: Int,
    val opening_crawl: String,
    val planets: List<String>,
    val producer: String,
    val release_date: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val url: String,
    val vehicles: List<String>
)

fun FilmDto.toFilm() = Film(
    characters,
    created,
    director,
    edited,
    episode_id,
    opening_crawl,
    planets,
    producer,
    release_date,
    species,
    starships,
    title,
    vehicles
)