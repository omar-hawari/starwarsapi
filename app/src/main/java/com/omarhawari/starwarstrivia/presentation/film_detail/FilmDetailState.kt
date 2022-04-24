package com.omarhawari.starwarstrivia.presentation.film_detail

import com.omarhawari.starwarstrivia.domain.models.*
import com.omarhawari.starwarstrivia.presentation.films.FilmsState

class FilmDetailState(
    val characters: ArrayList<Pair<Int, Character>> = arrayListOf(),
    val planets: ArrayList<Pair<Int, Planet>> = arrayListOf(),
    val spaceShips: ArrayList<Pair<Int, SpaceShip>> = arrayListOf(),
    val vehicles: ArrayList<Pair<Int, Vehicle>> = arrayListOf(),
    val isLoading: Boolean = false,
    val film: Film? = null,
    val error: String = ""
) {

    fun addCharacter(character: Character, index: Int): FilmDetailState {
        characters.add(Pair(index, character))
        characters.sortBy { it.first }
        return copyWith(characters = characters)
    }

    fun addPlanet(planet: Planet, index: Int): FilmDetailState {
        planets.add(Pair(index, planet))
        planets.sortBy { it.first }
        return copyWith(planets = planets)
    }

    fun addSpaceShip(spaceShip: SpaceShip, index: Int): FilmDetailState {
        spaceShips.add(Pair(index, spaceShip))
        spaceShips.sortBy { it.first }
        return copyWith(spaceShips = spaceShips)
    }


    fun addVehicle(vehicle: Vehicle, index: Int): FilmDetailState {
        vehicles.add(Pair(index, vehicle))
        vehicles.sortBy { it.first }
        return copyWith(vehicles = vehicles)
    }

    fun copyWith(
        characters: ArrayList<Pair<Int, Character>> = this.characters,
        planets: ArrayList<Pair<Int, Planet>> = this.planets,
        spaceShips: ArrayList<Pair<Int, SpaceShip>> = this.spaceShips,
        vehicles: ArrayList<Pair<Int, Vehicle>> = this.vehicles,
        isLoading: Boolean = this.isLoading,
        film: Film? = this.film,
        error: String = this.error
    ): FilmDetailState {
        return FilmDetailState(
            characters = characters,
            planets = planets,
            spaceShips = spaceShips,
            vehicles = vehicles,
            isLoading = isLoading,
            film = film,
            error = error,
        )
    }

    override fun toString(): String {
        return "FilmDetailState(isLoading=$isLoading, characters=$characters, film=$film, error='$error')"
    }
}
