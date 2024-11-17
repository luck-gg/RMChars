package com.luckgg.rmchars.data.remote.dto

import com.google.gson.annotations.SerializedName

data class KeysDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("pages") val pages: Int,
    @SerializedName("prev") val prev: String?,
)
