package com.luckgg.rmchars.data.remote.response

import com.luckgg.rmchars.data.remote.dto.CharacterDto

data class CharacterApiResponse(
    val info: PageInfo,
    val results: List<CharacterDto>,
)
