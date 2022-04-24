package com.omarhawari.starwarstrivia.data.repository

import com.omarhawari.starwarstrivia.data.remote.StarWarsApi
import com.omarhawari.starwarstrivia.data.remote.dto.FilmDto
import com.omarhawari.starwarstrivia.data.remote.dto.GenericResponse
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val api: StarWarsApi
) : StarWarsRepository {
    override suspend fun getFilms(): GenericResponse<List<FilmDto>> = api.getFilms()

    override suspend fun getFilmDetails(filmIndex: Int): FilmDto =
        api.getFilms(filmIndex)
}