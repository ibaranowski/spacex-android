package com.spacex.rockets.data.entity

import com.spacex.rockets.data.database.Launch
import com.spacex.rockets.data.database.Rocket
import com.spacex.rockets.data.utils.EMPTY
import com.spacex.rockets.domain.entity.LaunchEntity
import com.spacex.rockets.domain.entity.RocketEntity
import java.util.*

internal fun RocketResponse.mapToDomain(): RocketEntity {
    return let {
        RocketEntity(
            id = it.rocket_id ?: EMPTY,
            active = it.active,
            country = it.country ?: EMPTY,
            enginesCount = it.engines?.number ?: 0,
            name = it.rocket_name ?: EMPTY,
            description = it.description ?: EMPTY
        )
    }
}

internal fun Launch.mapToDomain(): LaunchEntity {
    return let {
        LaunchEntity(
            missionName = it.missionName ?: EMPTY,
            missionPatchImage = it.imageUrl ?: EMPTY,
            launchYear = it.launchYear?.toInt() ?: 0,
            launchSuccess = it.launchSuccess ?: false,
            launchDate = it.launchDateUtc ?: Date()
        )
    }
}


internal fun Rocket.mapToDomain(): RocketEntity {
    return let {
        RocketEntity(
            id = it.rocketId,
            active = it.active,
            country = it.country ?: EMPTY,
            enginesCount = it.enginesCount?.toInt() ?: 0,
            name = it.rocketName ?: EMPTY,
            description = it.description ?: EMPTY
        )
    }
}

internal fun LaunchResponse.mapToDomain(): LaunchEntity {
    return let {
        LaunchEntity(
            missionName = it.mission_name ?: EMPTY,
            missionPatchImage = it.links?.mission_patch_small ?: EMPTY,
            launchYear = it.launch_year,
            launchSuccess = it.launch_success,
            launchDate = it.launch_date_utc
        )
    }

}

