package com.luckgg.rmchars.presentation

import com.luckgg.rmchars.domain.usecase.FetchCharactersUseCase
import com.luckgg.rmchars.presentation.screens.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fetchCharactersUseCase: FetchCharactersUseCase
    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fetchCharactersUseCase = FetchCharactersUseCase(mock())
        viewModel = CharacterViewModel(fetchCharactersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onQueryChange_updatesQueryState() {
        val newQuery = "Morty"

        viewModel.onQueryChange(newQuery)

        assertEquals(newQuery, viewModel.query.value)
    }
}
