package com.luckgg.rmchars.data.mapper

import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.remote.dto.CharacterDto
import com.luckgg.rmchars.data.remote.response.CharacterApiResponse
import com.luckgg.rmchars.domain.model.CharacterRM

class CharacterMapper {
    private fun mapToDomainCharacter(characterDTO: CharacterDto) =
        CharacterRM(
            id = characterDTO.id ?: 0,
            name = characterDTO.name,
            status = characterDTO.status,
            species = characterDTO.species,
            image = characterDTO.image.orEmpty(),
            gender = characterDTO.gender.orEmpty(),
            created = characterDTO.created.orEmpty(),
            url = characterDTO.url.orEmpty(),
            locationOrigin = characterDTO.locationOrigin.name,
            locationCurrent = characterDTO.locationCurrent.name,
        )

    fun mapToDomainCharacter(characterRes: CharacterApiResponse) = characterRes.results.map { mapToDomainCharacter(it) }
}

fun CharacterDto.toCharacterEntity() =
    CharacterEntity(
        id = id ?: 0,
        created = created.orEmpty(),
        gender = gender.orEmpty(),
        image = image.orEmpty(),
        name = name,
        species = species,
        status = status,
        type = type,
        url = url.orEmpty(),
        locationOrigin = locationOrigin.name,
        locationCurrent = locationCurrent.name,
    )

fun CharacterEntity.toCharacter() =
    CharacterRM(
        id = id,
        name = name,
        status = status,
        species = species,
        image = image,
        gender = gender,
        created = created,
        url = url,
        locationOrigin = locationOrigin,
        locationCurrent = locationCurrent,
    )
