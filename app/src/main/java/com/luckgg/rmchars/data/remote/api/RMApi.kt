package com.luckgg.rmchars.data.remote.api

import com.luckgg.rmchars.data.remote.response.CharacterApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RMApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int?,
        @Query("name") name: String? = null,
    ): CharacterApiResponse
}
