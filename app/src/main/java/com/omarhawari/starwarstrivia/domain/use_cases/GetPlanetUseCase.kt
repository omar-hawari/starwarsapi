package com.omarhawari.starwarstrivia.domain.use_cases

import com.omarhawari.starwarstrivia.data.remote.dto.toCharacter
import com.omarhawari.starwarstrivia.data.remote.dto.toPlanet
import com.omarhawari.starwarstrivia.data.remote.dto.toSpaceShip
import com.omarhawari.starwarstrivia.domain.models.Character
import com.omarhawari.starwarstrivia.domain.models.Planet
import com.omarhawari.starwarstrivia.domain.models.SpaceShip
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import com.plcoding.cryptocurrencyappyt.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: StarWarsRepository
) {

    operator fun invoke(spaceShipPath: String): Flow<Resource<Planet>> = flow {
        try {
            emit(Resource.Loading<Planet>())
            val films = repository.getPlanet(spaceShipPath).toPlanet()
            emit(Resource.Success<Planet>(films))
        } catch (e: HttpException) {
            emit(Resource.Error<Planet>(e.localizedMessage ?: "Unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error<Planet>("You're offline."))
        }

    }


}