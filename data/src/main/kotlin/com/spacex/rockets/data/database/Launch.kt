package com.spacex.rockets.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.spacex.rockets.data.entity.DataEntity
import java.util.*

@Entity
data class Launch(


        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var rocketId: String? = null,
        var missionName: String? = null,
        var imageUrl: String? = null,
        var launchYear: Integer? = null,
        var launchSuccess: Boolean? = null,
        var launchDateUtc: Date? = null
) : DataEntity