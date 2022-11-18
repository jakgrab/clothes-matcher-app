package com.example.clothesmatcher.di

import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.data.repository.ClothesRepositoryImpl
import com.example.clothesmatcher.domain.repository.ClothesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFileApi(): ClothesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClothesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesFileRepository(api: ClothesApi): ClothesRepository {
        return ClothesRepositoryImpl(api)
    }
}