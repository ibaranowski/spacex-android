package com.spacex.rockets.data.entity


data class RocketResponse(var country: String? = null,
                          var engines: EnginesResponse? = null,
                          var active: Boolean = false,
                          var description: String? = null,
                          var rocket_name: String? = null,
                          var rocket_id: String? = null) : DataEntity