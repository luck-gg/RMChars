package com.luckgg.rmchars.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("created") val created: String?,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("gender") val gender: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val locationOrigin: CharacterLocationDto,
    @SerializedName("location") val locationCurrent: CharacterLocationDto,
    @SerializedName("species") val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String?,
)
