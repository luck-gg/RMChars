package com.luckgg.rmchars.domain.model

data class CharacterRM(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val created: String,
    val url: String,
    val locationOrigin: String,
    val locationCurrent: String,
)
