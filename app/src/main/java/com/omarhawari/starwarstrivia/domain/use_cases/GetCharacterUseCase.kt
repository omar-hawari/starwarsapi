package com.omarhawari.starwarstrivia.domain.use_cases

import com.omarhawari.starwarstrivia.data.remote.dto.toCharacter
import com.omarhawari.starwarstrivia.domain.models.Character
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import com.omarhawari.starwarstrivia.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(characterPath: String): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading<Character>())
            val films = repository.getCharacter(characterPath).toCharacter()
            emit(Resource.Success<Character>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<Character>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<Character>("You're offline."))
        }

    }


}