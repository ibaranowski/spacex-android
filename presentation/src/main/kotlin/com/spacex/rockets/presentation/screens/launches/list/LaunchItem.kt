package com.spacex.rockets.presentation.screens.launches.list

import java.util.*


data class LaunchItem(
        val missionName: String,
        val missionPatchImage: String,
        val launchYear: Int,
        val launchDate: Date,
        val launchSuccess: Boolean,
        val header: Boolean
)