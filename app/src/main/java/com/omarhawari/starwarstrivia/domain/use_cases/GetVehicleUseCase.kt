package com.omarhawari.starwarstrivia.domain.use_cases

import com.omarhawari.starwarstrivia.data.remote.dto.toVehicle
import com.omarhawari.starwarstrivia.domain.models.Vehicle
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import com.omarhawari.starwarstrivia.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVehicleUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(spaceShipPath: String): Flow<Resource<Vehicle>> = flow {
        try {
            emit(Resource.Loading<Vehicle>())
            val films = repository.getVehicle(spaceShipPath).toVehicle()
            emit(Resource.Success<Vehicle>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<Vehicle>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<Vehicle>("You're offline."))
        }

    }


}