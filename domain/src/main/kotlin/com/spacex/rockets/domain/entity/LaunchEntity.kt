package com.spacex.rockets.domain.entity

import java.util.*


data class LaunchEntity(
        val missionName: String,
        val missionPatchImage: String,
        val launchYear: Int,
        val launchDate: Date,
        val launchSuccess: Boolean
) : DomainEntity