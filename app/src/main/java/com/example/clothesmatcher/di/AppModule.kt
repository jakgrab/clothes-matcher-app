package com.example.clothesmatcher.di

import android.content.Context
import androidx.room.Room
import com.example.clothesmatcher.constants.Constants.BASE_URL
import com.example.clothesmatcher.data.remote.ClothesApi
import com.example.clothesmatcher.data.repository.ClothesRepositoryImpl
import com.example.clothesmatcher.data.repository.UrlRepositoryImpl
import com.example.clothesmatcher.repository.ClothesRepository
import com.example.clothesmatcher.repository.UrlRepository
import com.example.clothesmatcher.room.ServerDAO
import com.example.clothesmatcher.room.ServerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    @Singleton
//    fun provideFileApi(): ClothesApi {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ClothesApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun providesFileRepository(api: ClothesApi): ClothesRepository {
//        return ClothesRepositoryImpl(api)
//    }

    @Provides
    @Singleton
    fun providesUrlRepository(serverDao: ServerDAO): UrlRepository {
        return UrlRepositoryImpl(serverDao)
    }

    @Provides
    @Singleton
    fun provideServerDAO(serverDatabase: ServerDatabase): ServerDAO = serverDatabase.serverDao()

    @Provides
    @Singleton
    fun provideServerDatabase(@ApplicationContext context: Context): ServerDatabase =
        Room.databaseBuilder(
            context,
            ServerDatabase::class.java,
            "url_table"
        ).fallbackToDestructiveMigration().build()

}