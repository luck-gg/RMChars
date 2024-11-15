package com.luckgg.rmchars.di

import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.data.repository.RMRepositoryImpl
import com.luckgg.rmchars.domain.repository.RMRepository
import com.luckgg.rmchars.domain.usecase.CharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val httpClient: OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun provideRMCharacterAPI(): RMApi =
        Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RMApi::class.java)

    @Provides
    @Singleton
    fun provideRMRepository(api: RMApi): RMRepository = RMRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideRMCharacterUseCase(rmRepository: RMRepository): CharacterUseCase = CharacterUseCase(rmRepository)
}
