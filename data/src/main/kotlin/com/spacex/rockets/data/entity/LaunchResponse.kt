package com.spacex.rockets.data.entity

import java.util.*


data class LaunchResponse(var mission_name: String? = null,
                          var links: LinksResponse? = null,
                          var launch_year: Int,
                          var launch_success: Boolean,
                          var launch_date_utc: Date
) : DataEntity