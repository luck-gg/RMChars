package com.luckgg.rmchars.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.domain.usecase.FetchCharactersUseCase
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
        private val fetchCharactersUseCase: FetchCharactersUseCase,
    ) : ViewModel() {
        private val _characterUiState: MutableStateFlow<PagingData<CharacterRM>> =
            MutableStateFlow(PagingData.empty())
        val characterUiState: StateFlow<PagingData<CharacterRM>> = _characterUiState.asStateFlow()

        private val _query = MutableStateFlow("")
        val query: StateFlow<String> = _query.asStateFlow()

        init {
            fetchCharacters()
        }

        fun fetchCharacters() {
            viewModelScope.launch {
                fetchCharactersUseCase(query.value)
                    .cachedIn(viewModelScope)
                    .collect {
                        _characterUiState.value = it
                    }
            }
        }

        fun onQueryChange(newQuery: String) {
            _query.value = newQuery
        }
    }
