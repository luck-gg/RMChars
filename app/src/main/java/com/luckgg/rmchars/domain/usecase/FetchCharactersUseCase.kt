package com.luckgg.rmchars.domain.usecase

import com.luckgg.rmchars.domain.repository.RMRepository
import javax.inject.Inject

class FetchCharactersUseCase
    @Inject
    constructor(
        private val rmRepository: RMRepository,
    ) {
        operator fun invoke(characterName: String) = rmRepository.getCharacters(characterName)
    }
