package com.luckgg.rmchars.data.remote.response

data class PageInfo(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String,
)
