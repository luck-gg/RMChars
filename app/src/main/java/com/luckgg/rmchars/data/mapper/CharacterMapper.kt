package com.luckgg.rmchars.data.mapper

import com.luckgg.rmchars.data.remote.dto.CharacterDTO
import com.luckgg.rmchars.data.remote.response.CharacterResponse
import com.luckgg.rmchars.domain.model.CharacterRM

class CharacterMapper {
    private fun mapToDomainCharacter(characterDTO: CharacterDTO) =
        CharacterRM(
            id = characterDTO.id?.toIntOrNull() ?: 0,
            name = characterDTO.name.orEmpty(),
            status = characterDTO.status.orEmpty(),
            species = characterDTO.species.orEmpty(),
            image = characterDTO.image.orEmpty(),
        )

    fun mapToDomainCharacters(characterRes: CharacterResponse) = characterRes.results.map { mapToDomainCharacter(it) }
}
