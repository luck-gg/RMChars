package com.luckgg.rmchars.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.luckgg.rmchars.data.local.CharacterDatabase
import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.remote.CharacterRemoteMediator
import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.data.repository.RMRepositoryImpl
import com.luckgg.rmchars.domain.repository.RMRepository
import com.luckgg.rmchars.domain.usecase.CharacterUseCase
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
    ): CharacterDatabase =
        Room
            .databaseBuilder(
                context,
                CharacterDatabase::class.java,
                "beers.db",
            ).build()

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideCharacterPager(
        api: RMApi,
        database: CharacterDatabase,
    ): Pager<Int, CharacterEntity> =
        Pager(
            config =
                PagingConfig(
                    prefetchDistance = 10,
                    pageSize = 20,
                    initialLoadSize = 20,
                ),
            remoteMediator =
                CharacterRemoteMediator(
                    characterDb = database,
                    rmApi = api,
                ),
            pagingSourceFactory = {
                database.dao.pagingSource()
            },
        )

    @Provides
    @Singleton
    fun provideRMRepository(pager: Pager<Int, CharacterEntity>): RMRepository = RMRepositoryImpl(pager)

    @Provides
    @Singleton
    fun provideRMCharacterUseCase(rmRepository: RMRepository): CharacterUseCase = CharacterUseCase(rmRepository)
}
