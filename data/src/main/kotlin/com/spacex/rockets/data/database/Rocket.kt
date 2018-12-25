package com.spacex.rockets.data.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.spacex.rockets.data.entity.DataEntity
import com.spacex.rockets.data.utils.EMPTY

@Entity
data class Rocket(

        @PrimaryKey
        var rocketId: String = EMPTY,
        var country: String? = null,
        var enginesCount: Integer? = null,
        var active: Boolean = false,
        var description: String? = null,
        var rocketName: String? = null


) : DataEntity