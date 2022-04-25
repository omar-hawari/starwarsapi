package com.omarhawari.starwarstrivia.domain.use_cases

import com.omarhawari.starwarstrivia.data.remote.dto.toFilm
import com.omarhawari.starwarstrivia.domain.models.Film
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import com.omarhawari.starwarstrivia.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(): Flow<Resource<List<Film>>> = flow {
        try {
            emit(Resource.Loading<List<Film>>())
            val films = repository.getFilms().results.map { it.toFilm() }
            emit(Resource.Success<List<Film>>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Film>>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<List<Film>>("You're offline."))
        }

    }


}