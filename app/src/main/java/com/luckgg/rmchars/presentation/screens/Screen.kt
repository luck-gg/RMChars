package com.luckgg.rmchars.presentation.screens

sealed class Screen(
    val route: String,
) {
    data object CharacterListScreen : Screen("character_list_screen")

    data object CharacterDetailScreen : Screen("character_detail_screen")
}
