package com.luckgg.rmchars.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val created: String,
    val gender: String,
    val image: String,
    val locationOrigin: String,
    val locationCurrent: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
)
