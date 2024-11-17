package com.luckgg.rmchars.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.local.RepoDatabase
import com.luckgg.rmchars.data.mapper.toCharacterEntity
import com.luckgg.rmchars.data.remote.api.RMApi

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterDb: RepoDatabase,
    private val rmApi: RMApi,
    private val characterName: String?,
) : RemoteMediator<Int, CharacterEntity>() {
    private var currentPage = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            val page =
                when (loadType) {
                    LoadType.REFRESH -> {
                        currentPage = 1
                        currentPage
                    }
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> currentPage + 1
                }

            val response = rmApi.getCharacters(page = page, characterName)
            val isEndOfPagination = response.info.next == null

            val characters = response.results

            characterDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterDb.dao.clearAll()
                }
                val characterEntities = characters.map { it.toCharacterEntity() }
                characterDb.dao.upsertAll(characterEntities)
            }
            currentPage = (if (isEndOfPagination) currentPage else page)

            return MediatorResult.Success(endOfPaginationReached = isEndOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
