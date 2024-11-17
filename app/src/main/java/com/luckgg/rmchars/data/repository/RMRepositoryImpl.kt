package com.luckgg.rmchars.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.luckgg.rmchars.data.local.RepoDatabase
import com.luckgg.rmchars.data.mapper.toCharacter
import com.luckgg.rmchars.data.remote.CharacterRemoteMediator
import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.domain.repository.RMRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RMRepositoryImpl
    @Inject
    constructor(
        private val database: RepoDatabase,
        private val api: RMApi,
    ) : RMRepository {
        @OptIn(ExperimentalPagingApi::class)
        override fun getCharacters(characterName: String): Flow<PagingData<CharacterRM>> {
            val pager =
                Pager(
                    config =
                        PagingConfig(
                            prefetchDistance = 50,
                            pageSize = 20,
                            initialLoadSize = 20,
                            enablePlaceholders = true,
                        ),
                    remoteMediator =
                        CharacterRemoteMediator(
                            characterDb = database,
                            rmApi = api,
                            characterName = characterName,
                        ),
                    pagingSourceFactory = {
                        if (characterName == "") {
                            database.dao.pagingSource()
                        } else {
                            database.dao.getCharacterByName(characterName)
                        }
                    },
                )
            return pager.flow
                .map { pagingData ->
                    pagingData.map { it.toCharacter() }
                }
        }
    }
