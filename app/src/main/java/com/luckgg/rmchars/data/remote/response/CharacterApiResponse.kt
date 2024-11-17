package com.luckgg.rmchars.data.remote.response

import com.luckgg.rmchars.data.remote.dto.CharacterDto
import com.luckgg.rmchars.data.remote.dto.KeysDto

data class CharacterApiResponse(
    val info: KeysDto,
    val results: List<CharacterDto>,
)
