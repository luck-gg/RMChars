package com.luckgg.rmchars.data.mapper

import com.luckgg.rmchars.data.local.CharacterEntity
import com.luckgg.rmchars.data.remote.dto.CharacterDto
import com.luckgg.rmchars.domain.model.CharacterRM

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
