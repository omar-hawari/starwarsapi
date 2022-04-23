package com.omarhawari.starwarstrivia.data.remote

import com.omarhawari.starwarstrivia.data.remote.dto.FilmDto
import com.omarhawari.starwarstrivia.data.remote.dto.GenericResponse
import retrofit2.http.GET

interface StarWarsApi {

    @GET("films/")
    suspend fun getFilms(): GenericResponse<List<FilmDto>>

}