package com.spacex.rockets.presentation.screens.launches.list

import com.spacex.rockets.domain.entity.LaunchEntity


internal fun LaunchEntity.mapToAdapter(index: Int): LaunchItem {
    return let {
        LaunchItem(
                missionName = missionName,
            missionPatchImage = missionPatchImage,
            launchSuccess = launchSuccess,
            launchYear = launchYear,
            launchDate = it.launchDate,
            header = index == 0
        )
    }
}
