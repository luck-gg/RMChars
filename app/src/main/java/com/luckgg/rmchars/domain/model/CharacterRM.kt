package com.luckgg.rmchars.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterRM(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val gender: String,
    val locationOrigin: String,
    val locationCurrent: String,
) : Parcelable
