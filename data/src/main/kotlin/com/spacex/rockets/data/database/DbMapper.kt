package com.spacex.rockets.data.database

import com.spacex.rockets.data.entity.LaunchResponse
import com.spacex.rockets.data.entity.RocketResponse


internal fun LaunchResponse.mapToDatabase(rocketId: String): Launch =
    Launch(
        id = 0L,
        rocketId = rocketId,
        missionName = mission_name,
        imageUrl = links?.mission_patch_small,
        launchYear = Integer(launch_year),
        launchSuccess = launch_success,
        launchDateUtc = launch_date_utc
    )

internal fun RocketResponse.mapToDatabase(): Rocket =
    Rocket(
        rocketId = rocket_id ?: throw IllegalAccessException("RocketResponse.mapToDatabase rocket_id null"),
        country = country,
        enginesCount = Integer(engines?.number ?: 0),
        active = active,
        description = description,
        rocketName = rocket_name
    )



