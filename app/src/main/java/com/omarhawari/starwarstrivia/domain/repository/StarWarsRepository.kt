package com.omarhawari.starwarstrivia.domain.repository

import com.omarhawari.starwarstrivia.data.remote.dto.*

interface StarWarsRepository {

    suspend fun getFilms(): GenericResponse<List<FilmDto>>

    suspend fun getFilmByIndex(filmIndex: Int): FilmDto

    suspend fun getFilm(filmPath: String): FilmDto

    suspend fun getCharacter(characterPath: String): CharacterDto

    suspend fun getSpaceShip(spaceShipPath: String): SpaceShipDto

    suspend fun getPlanet(planetPath: String): PlanetDto

    suspend fun getVehicle(vehiclePath: String): VehicleDto


}