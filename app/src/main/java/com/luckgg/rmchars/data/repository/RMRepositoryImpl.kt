package com.luckgg.rmchars.data.repository

import com.luckgg.rmchars.data.mapper.CharacterMapper
import com.luckgg.rmchars.data.remote.api.RMApi
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.domain.repository.RMRepository
import javax.inject.Inject

class RMRepositoryImpl
    @Inject
    constructor(
        private val api: RMApi,
    ) : RMRepository {
        private val characterMapper = CharacterMapper()

        override suspend fun getCharacters(page: Int?): List<CharacterRM> {
            val result = api.getCharacters(page)
            return characterMapper.mapToDomainCharacters(result)
        }
    }
