package com.omarhawari.starwarstrivia.data.repository

import com.omarhawari.starwarstrivia.data.remote.StarWarsApi
import com.omarhawari.starwarstrivia.data.remote.dto.*
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val api: StarWarsApi
) : StarWarsRepository {
    override suspend fun getFilms(): GenericResponse<List<FilmDto>> = api.getFilms()

    override suspend fun getFilmDetails(filmIndex: Int): FilmDto =
        api.getFilms(filmIndex)

    override suspend fun getCharacter(characterPath: String): CharacterDto = api.getCharacter(characterPath)


    override suspend fun getSpaceShip(spaceShipPath: String): SpaceShipDto = api.getSpaceShip(spaceShipPath)

    override suspend fun getPlanet(planetPath: String): PlanetDto = api.getPlanet(planetPath)

    override suspend fun getVehicle(vehiclePath: String): VehicleDto = api.getVehicle(vehiclePath)
}