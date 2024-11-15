package com.luckgg.rmchars.data.remote.dto

data class CharacterDTO(
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
