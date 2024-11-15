package com.luckgg.rmchars.domain.usecase

import com.luckgg.rmchars.domain.repository.RMRepository
import javax.inject.Inject

class CharacterUseCase
    @Inject
    constructor(
        private val rmRepository: RMRepository,
    ) {
        suspend operator fun invoke() = rmRepository.getCharacters(page = 1)
    }
