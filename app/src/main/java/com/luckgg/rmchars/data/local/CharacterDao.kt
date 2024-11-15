package com.luckgg.rmchars.data.local

import com.luckgg.rmchars.data.remote.dto.Location
import com.luckgg.rmchars.data.remote.dto.Origin

data class CharacterDao(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: String?,
    val image: String?,
    val location: Location?,
    val name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?,
)
