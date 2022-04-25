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

class GetFilmDetailUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(filmIndex: Int): Flow<Resource<Film>> = flow {
        try {
            emit(Resource.Loading<Film>())
            val films = repository.getFilmDetails(filmIndex).also {
                print(it)
            }.toFilm()
            emit(Resource.Success<Film>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<Film>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<Film>("You're offline."))
        }

    }


}