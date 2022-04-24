package com.omarhawari.starwarstrivia.presentation.film_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarhawari.starwarstrivia.common.Constants
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.domain.use_cases.*
import com.omarhawari.starwarstrivia.presentation.films.FilmsState
import com.plcoding.cryptocurrencyappyt.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmDetailUseCase: GetFilmDetailUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getSpaceShipUseCase: GetSpaceShipUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getVehicleUseCase: GetVehicleUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(FilmDetailState())
    private var _filmIndex: Int = 0
    val state = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_FILM_INDEX)?.let { filmIndex ->
            _filmIndex = filmIndex.toInt()
            getFilmDetail(filmIndex.toInt())
        }
    }

    fun refresh() {
        getFilmDetail(_filmIndex)
    }

    private fun getFilmDetail(filmIndex: Int) {
        getFilmDetailUseCase(filmIndex).onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> FilmDetailState(film = result.data)
                is Resource.Error -> FilmDetailState(error = result.message ?: "Unexpected error.")
                is Resource.Loading -> FilmDetailState(isLoading = true)
            }
            if (result is Resource.Success) {
                _state.value.film!!.characters.forEachIndexed { index, it ->
                    getCharacter(it, index = index)
                }
                _state.value.film!!.starships.forEachIndexed { index, it ->
                    getSpaceShip(it, index = index)
                }
                _state.value.film!!.planets.forEachIndexed { index, it ->
                    getPlanet(it, index = index)
                }
                _state.value.film!!.vehicles.forEachIndexed { index, it ->
                    getVehicle(it, index = index)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCharacter(path: String, index: Int) {
        getCharacterUseCase(path).onEach { result ->
            if (result is Resource.Success) {
                _state.value =
                    result.data?.let { _state.value.addCharacter(it, index) }
                        ?: _state.value
            }
        }.launchIn(viewModelScope)
    }

    private fun getSpaceShip(path: String, index: Int) {
        getSpaceShipUseCase(path).onEach { result ->
            if (result is Resource.Success) {
                _state.value =
                    result.data?.let { _state.value.addSpaceShip(it, index) }
                        ?: _state.value
            }
        }.launchIn(viewModelScope)
    }

    private fun getVehicle(path: String, index: Int) {
        getVehicleUseCase(path).onEach { result ->
            if (result is Resource.Success) {
                _state.value =
                    result.data?.let { _state.value.addVehicle(it, index) }
                        ?: _state.value
            }
        }.launchIn(viewModelScope)
    }

    private fun getPlanet(path: String, index: Int) {
        getPlanetUseCase(path).onEach { result ->
            if (result is Resource.Success) {
                _state.value =
                    result.data?.let { _state.value.addPlanet(it, index) }
                        ?: _state.value
            }
        }.launchIn(viewModelScope)
    }

}