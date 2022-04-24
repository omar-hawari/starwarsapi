package com.omarhawari.starwarstrivia.domain.repository

import com.omarhawari.starwarstrivia.data.remote.dto.FilmDto
import com.omarhawari.starwarstrivia.data.remote.dto.GenericResponse

interface StarWarsRepository {

    suspend fun getFilms(): GenericResponse<List<FilmDto>>

    suspend fun getFilmDetails(filmIndex: Int): FilmDto

}