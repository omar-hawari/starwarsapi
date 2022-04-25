package com.omarhawari.starwarstrivia.domain.use_cases

import com.omarhawari.starwarstrivia.data.remote.dto.toSpaceShip
import com.omarhawari.starwarstrivia.domain.models.SpaceShip
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import com.omarhawari.starwarstrivia.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSpaceShipUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(spaceShipPath: String): Flow<Resource<SpaceShip>> = flow {
        try {
            emit(Resource.Loading<SpaceShip>())
            val films = repository.getSpaceShip(spaceShipPath).toSpaceShip()
            emit(Resource.Success<SpaceShip>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<SpaceShip>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<SpaceShip>("You're offline."))
        }

    }


}