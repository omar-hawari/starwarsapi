package com.omarhawari.starwarstrivia.di

import com.omarhawari.starwarstrivia.common.Constants
import com.omarhawari.starwarstrivia.data.remote.StarWarsApi
import com.omarhawari.starwarstrivia.data.repository.StarWarsRepositoryImpl
import com.omarhawari.starwarstrivia.domain.repository.StarWarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStarWarsApi(): StarWarsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
            .create(StarWarsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStarWarsRepository(api: StarWarsApi): StarWarsRepository {
        return StarWarsRepositoryImpl(api)
    }

}