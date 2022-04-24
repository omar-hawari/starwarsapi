package com.omarhawari.starwarstrivia.data.remote

import com.omarhawari.starwarstrivia.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface StarWarsApi {

    @GET("films/")
    suspend fun getFilms(): GenericResponse<List<FilmDto>>

    @GET("films/{filmIndex}")
    suspend fun getFilms(@Path("filmIndex") filmIndex: Int): FilmDto

    @GET
    suspend fun getCharacter(@Url characterPath: String): CharacterDto

    @GET
    suspend fun getVehicle(@Url vehiclePath: String): VehicleDto

    @GET
    suspend fun getPlanet(@Url planetPath: String): PlanetDto

    @GET
    suspend fun getSpaceShip(@Url spaceShipPath: String): SpaceShipDto

}