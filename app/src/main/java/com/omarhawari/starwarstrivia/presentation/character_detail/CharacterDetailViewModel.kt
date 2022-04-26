package com.omarhawari.starwarstrivia.presentation.character_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarhawari.starwarstrivia.common.Constants
import com.omarhawari.starwarstrivia.common.Resource
import com.omarhawari.starwarstrivia.domain.use_cases.GetCharacterUseCase
import com.omarhawari.starwarstrivia.domain.use_cases.GetFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getFilmUseCase: GetFilmUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = ObservableField(CharacterDetailState())
    val state = _state

    private var characterPath = ""

    init {
        savedStateHandle.get<String>(Constants.PARAM_CHARACTER_PATH)?.let { characterPath ->
            this.characterPath = characterPath
            getCharacter(characterPath)
        }
    }

    fun onRefresh() {
        getCharacter(path = characterPath)
    }

    private fun getCharacter(path: String) {
        getCharacterUseCase(path).onEach { result ->
            _state.set(
                when (result) {
                    is Resource.Success -> CharacterDetailState(character = result.data)
                    is Resource.Error -> CharacterDetailState(
                        error = result.message ?: "Unexpected error."
                    )
                    is Resource.Loading -> CharacterDetailState(isLoading = true)
                }
            )
            if (result is Resource.Success) {
                result.data!!.films.forEachIndexed { index, path ->
                    getFilm(path, index)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFilm(path: String, index: Int) {
        getFilmUseCase(path).onEach { result ->
            if (result is Resource.Success) {
                _state.set(result.data?.let { _state.get()!!.addFilm(it, index) }
                    ?: _state.get()
                )
                println(result.data)
            }
        }.launchIn(viewModelScope)
    }

}