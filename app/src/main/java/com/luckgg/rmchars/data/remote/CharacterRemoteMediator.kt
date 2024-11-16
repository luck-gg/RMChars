package com.luckgg.rmchars.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.luckgg.rmchars.data.local.CharacterDatabase
import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.mapper.toCharacterEntity
import com.luckgg.rmchars.data.remote.api.RMApi
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterDb: CharacterDatabase,
    private val rmApi: RMApi,
    private val characterName: String?,
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            var response = rmApi.getCharacters(page = 1, name = characterName)

            val loadKey =
                when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(
                        endOfPaginationReached = true,
                    )
                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                        lastItem?.let {
                            if (lastItem.id == response.info.count) {
                                return MediatorResult.Success(
                                    endOfPaginationReached = true,
                                )
                            } else {
                                (lastItem.id / state.config.pageSize) + 1
                            }
                        }
                    }
                }

            response = rmApi.getCharacters(page = loadKey)
            val characters = response.results

            characterDb.withTransaction {
                val characterEntities = characters.map { it.toCharacterEntity() }
                characterDb.dao.upsertAll(characterEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = characters.isEmpty(),
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
