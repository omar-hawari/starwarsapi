package com.omarhawari.starwarstrivia.presentation.films

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarhawari.starwarstrivia.common.Resource
import com.omarhawari.starwarstrivia.domain.use_cases.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(FilmsState())
    val state = _state

    init {
        getFilms()
    }

    fun refresh() {
        getFilms()
    }

    private fun getFilms() {
        getFilmsUseCase().onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> FilmsState(films = result.data ?: emptyList())
                is Resource.Error -> FilmsState(error = result.message ?: "Unexpected error.")
                is Resource.Loading -> FilmsState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }


}