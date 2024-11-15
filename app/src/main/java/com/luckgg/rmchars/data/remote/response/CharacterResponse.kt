package com.luckgg.rmchars.data.remote.response

import com.luckgg.rmchars.data.remote.dto.CharacterDTO

data class CharacterResponse(
    val info: PageInfo,
    val results: List<CharacterDTO>,
)
