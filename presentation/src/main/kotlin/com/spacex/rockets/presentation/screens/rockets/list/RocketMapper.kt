package com.spacex.rockets.presentation.screens.rockets.list

import com.spacex.rockets.domain.entity.RocketEntity


internal fun RocketEntity.mapToAdapter(): RocketItem {
    return let {
        RocketItem(
                id = it.id,
                name = it.name,
                country = it.country,
                enginesCount = it.enginesCount,
                active = it.active,
                description = it.description
        )
    }
}
