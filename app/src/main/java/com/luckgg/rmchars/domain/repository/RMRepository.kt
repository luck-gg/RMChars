package com.luckgg.rmchars.domain.repository

import androidx.paging.PagingData
import com.luckgg.rmchars.domain.model.CharacterRM
import kotlinx.coroutines.flow.Flow

fun interface RMRepository {
    fun getCharacters(characterName: String): Flow<PagingData<CharacterRM>>
}
