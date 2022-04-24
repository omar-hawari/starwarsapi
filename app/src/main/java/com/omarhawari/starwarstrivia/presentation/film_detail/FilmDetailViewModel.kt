package com.omarhawari.starwarstrivia.presentation.film_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarhawari.starwarstrivia.common.Constants
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.domain.use_cases.GetFilmDetailUseCase
import com.omarhawari.starwarstrivia.domain.use_cases.GetFilmsUseCase
import com.omarhawari.starwarstrivia.presentation.films.FilmsState
import com.plcoding.cryptocurrencyappyt.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getFilmDetailUseCase: GetFilmDetailUseCase,
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
            println(_state.value)
        }.launchIn(viewModelScope)
    }

}