package com.luckgg.rmchars.di

import android.content.Context
import androidx.room.Room
import com.luckgg.rmchars.data.local.RepoDatabase
import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.data.repository.RMRepositoryImpl
import com.luckgg.rmchars.domain.repository.RMRepository
import com.luckgg.rmchars.domain.usecase.FetchCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient: OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
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
    fun provideCharacterDatabase(
        @ApplicationContext context: Context,
    ): RepoDatabase =
        Room
            .databaseBuilder(
                context,
                RepoDatabase::class.java,
                "beers.db",
            ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideRMRepository(
        database: RepoDatabase,
        api: RMApi,
    ): RMRepository = RMRepositoryImpl(database, api)

    @Provides
    @Singleton
    fun provideRMCharacterUseCase(rmRepository: RMRepository) = FetchCharactersUseCase(rmRepository)
}
