package com.luckgg.rmchars.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.mapper.toCharacter
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.domain.repository.RMRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RMRepositoryImpl
    @Inject
    constructor(
        private val pager: Pager<Int, CharacterEntity>,
    ) : RMRepository {
        override fun getCharacters(): Flow<PagingData<CharacterRM>> =
            pager.flow
                .map { pagingData ->
                    pagingData.map { it.toCharacter() }
                }
    }
