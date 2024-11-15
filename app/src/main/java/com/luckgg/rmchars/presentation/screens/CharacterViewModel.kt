package com.luckgg.rmchars.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel
    @Inject
    constructor(
        characterUseCase: CharacterUseCase,
    ) : ViewModel() {
        private val charUseCase = characterUseCase

        private val _query = MutableStateFlow("")
        val query: StateFlow<String> = _query.asStateFlow()

        private val _characters = MutableStateFlow<List<CharacterRM>>(emptyList())
        val characters: StateFlow<List<CharacterRM>> = _characters.asStateFlow()

        init {
            loadCharacters()
        }

        fun onQueryChange(newQuery: String) {
            _query.value = newQuery
            loadCharacters()
        }

        private fun loadCharacters() {
            viewModelScope.launch {
                val response = charUseCase.invoke()
                _characters.value = response //
            }
        }
    }
