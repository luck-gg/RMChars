package com.luckgg.rmchars.domain.repository

import com.luckgg.rmchars.domain.model.CharacterRM

interface RMRepository {
    suspend fun getCharacters(page: Int?): List<CharacterRM>
}
