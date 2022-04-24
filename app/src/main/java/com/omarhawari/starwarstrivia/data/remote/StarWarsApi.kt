package com.omarhawari.starwarstrivia.data.remote

import com.omarhawari.starwarstrivia.data.remote.dto.FilmDto
import com.omarhawari.starwarstrivia.data.remote.dto.GenericResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {

    @GET("films/")
    suspend fun getFilms(): GenericResponse<List<FilmDto>>

    @GET("films/{filmIndex}")
    suspend fun getFilms(@Path("filmIndex") filmIndex: Int): FilmDto

}