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
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {
        return try {
            val loadKey =
                when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND -> return MediatorResult.Success(
                        endOfPaginationReached = true,
                    )
                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()

                        if (lastItem == null || lastItem.id == 826) {
                            0
                        } else {
                            (lastItem.id / state.config.pageSize) + 1
                        }
                    }
                }

            val characters =
                rmApi.getCharacters(page = loadKey).results

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
